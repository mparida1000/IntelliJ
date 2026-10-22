package practice;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * v2: Handles missing "created in SalesDev/ServiceDev" history row.
 * If there is no row OldValue=blank and NewValue=SalesDev (or ServiceDev),
 * but the first transition shows OldValue=SalesDev (or ServiceDev), we treat
 * the User Story Created Date (col E) as the entry time into that env.
 */
public class UserStoryEnvTimeCalculator_v2 {

    // CSV columns (0-based)
    private static final int COL_USER_STORY_ID = 1;   // column B
    private static final int COL_USER_STORY_NAME = 2; // column C
    private static final int COL_RELEASE = 3;         // column D
    private static final int COL_US_CREATED_DATE = 4; // column E
    private static final int COL_EPIC = 5;            // column F
    private static final int COL_TEAM = 6;            // column G
    private static final int COL_OLD_VALUE = 7;       // column H
    private static final int COL_NEW_VALUE = 8;       // column I
    private static final int COL_CREATED_DATE = 10;   // column K

    private static final DateTimeFormatter SF_DT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    private enum Env {
        SALESDEV,
        SERVICEDEV,
        DEVINT,
        RELEASEQA,
        PREINT,
        ACCRELEASE,
        ACCSTAGE,
        ACCPROD,
        OTHER,
        UNKNOWN
    }

    private static class Event {
        final Instant time;
        final String oldValueRaw;
        final String newValueRaw;
        final Env oldEnv;
        final Env newEnv;

        Event(Instant time, String oldValueRaw, String newValueRaw) {
            this.time = time;
            this.oldValueRaw = oldValueRaw;
            this.newValueRaw = newValueRaw;
            this.oldEnv = canonicalEnv(oldValueRaw);
            this.newEnv = canonicalEnv(newValueRaw);
        }
    }

    private static class StoryAgg {
        final String storyId;
        String storyName;
        String release;
        String storyCreatedDateRaw;
        Instant storyCreatedInstant;
        String epic;
        String team;
        final List<Event> events = new ArrayList<>();
        final EnumMap<Env, Double> hours = new EnumMap<>(Env.class);

        StoryAgg(String storyId) {
            this.storyId = storyId;
            for (Env e : Env.values()) hours.put(e, 0.0);
        }

        void addHours(Env env, double h) {
            if (env == null) return;
            if (!hours.containsKey(env)) return;
            hours.put(env, hours.get(env) + h);
        }
    }

    public static void main(String[] args) throws Exception {
        Path defaultDir = Paths.get(System.getProperty("user.home"), "Documents", "devdata");
        Path input = (args.length >= 1) ? Paths.get(args[0]) : defaultDir.resolve("User Story Data.csv");
        Path output = (args.length >= 2) ? Paths.get(args[1]) : defaultDir.resolve("User Story Env Times.csv");

        if (!Files.exists(input)) {
            System.err.println("Input file not found: " + input);
            System.err.println("Tip: pass a full input path as the first argument.");
            System.exit(2);
        }

        Map<String, StoryAgg> stories = readAndGroup(input);
        for (StoryAgg s : stories.values()) {
            computeDurations(s);
        }
        writeOutput(output, stories);

        System.out.println("Done. Wrote: " + output.toAbsolutePath());
    }

    private static Map<String, StoryAgg> readAndGroup(Path input) throws IOException {
        Map<String, StoryAgg> map = new LinkedHashMap<>();

        try (BufferedReader br = Files.newBufferedReader(input, StandardCharsets.UTF_8)) {
            String header = br.readLine();
            if (header == null) return map;

            String line;
            long lineNo = 1;
            while ((line = br.readLine()) != null) {
                lineNo++;
                if (line.trim().isEmpty()) continue;

                List<String> cols = parseCsvLine(line);
                if (cols.size() <= COL_CREATED_DATE) {
                    System.err.println("Skipping line " + lineNo + " (not enough columns)");
                    continue;
                }

                String storyId = safe(cols, COL_USER_STORY_ID);
                if (storyId.isEmpty()) continue;

                StoryAgg agg = map.computeIfAbsent(storyId, StoryAgg::new);

                if (isBlank(agg.storyName)) agg.storyName = safe(cols, COL_USER_STORY_NAME);
                if (isBlank(agg.release)) agg.release = safe(cols, COL_RELEASE);
                if (isBlank(agg.storyCreatedDateRaw)) {
                    agg.storyCreatedDateRaw = safe(cols, COL_US_CREATED_DATE);
                    agg.storyCreatedInstant = parseSalesforceInstant(agg.storyCreatedDateRaw);
                }
                if (isBlank(agg.epic)) agg.epic = safe(cols, COL_EPIC);
                if (isBlank(agg.team)) agg.team = safe(cols, COL_TEAM);

                String oldVal = safe(cols, COL_OLD_VALUE);
                String newVal = safe(cols, COL_NEW_VALUE);
                String createdDate = safe(cols, COL_CREATED_DATE);

                Instant t = parseSalesforceInstant(createdDate);
                if (t == null) {
                    System.err.println("Skipping line " + lineNo + " (bad CreatedDate): " + createdDate);
                    continue;
                }

                agg.events.add(new Event(t, oldVal, newVal));
            }
        }

        for (StoryAgg s : map.values()) {
            s.events.sort(Comparator.comparing(e -> e.time));
        }

        return map;
    }

    /**
     * Core logic:
     * 1) If we have explicit creation row (blank -> SalesDev/ServiceDev), we start from that.
     * 2) If we do NOT have explicit creation row, but the earliest transition is SalesDev -> X
     *    (or ServiceDev -> X), then we count time in that env from User Story Created Date
     *    (col E) to that first transition time.
     * 3) Then we proceed with interval accumulation between successive transitions.
     */
    private static void computeDurations(StoryAgg s) {
        if (s.events.isEmpty()) return;

        // Detect whether the story has explicit "created in SalesDev/ServiceDev" rows
        boolean hasSalesDevCreate = hasExplicitCreateRow(s, Env.SALESDEV);
        boolean hasServiceDevCreate = hasExplicitCreateRow(s, Env.SERVICEDEV);

        // If missing create row, and the earliest event shows leaving SalesDev/ServiceDev,
        // assume it entered that env at User Story Created Date.
        Event first = s.events.get(0);
        if (s.storyCreatedInstant != null && first.oldEnv != Env.UNKNOWN) {
            if (!hasSalesDevCreate && first.oldEnv == Env.SALESDEV) {
                addInitialInterval(s, Env.SALESDEV, s.storyCreatedInstant, first.time);
            }
            if (!hasServiceDevCreate && first.oldEnv == Env.SERVICEDEV) {
                addInitialInterval(s, Env.SERVICEDEV, s.storyCreatedInstant, first.time);
            }
        }

        // Start normal accumulation from the first event's NEW value at its timestamp
        Env currentEnv = first.newEnv;
        Instant prevTime = first.time;

        for (int i = 1; i < s.events.size(); i++) {
            Event e = s.events.get(i);
            Instant currTime = e.time;

            double hours = positiveHoursBetween(prevTime, currTime);
            if (isReportedEnv(currentEnv)) {
                s.addHours(currentEnv, hours);
            }

            currentEnv = e.newEnv;
            prevTime = currTime;
        }
    }

    private static boolean hasExplicitCreateRow(StoryAgg s, Env env) {
        for (Event e : s.events) {
            if (e.oldEnv == Env.UNKNOWN && e.newEnv == env) return true;
        }
        return false;
    }

    private static void addInitialInterval(StoryAgg s, Env env, Instant start, Instant end) {
        if (start == null || end == null) return;
        double hours = positiveHoursBetween(start, end);
        if (isReportedEnv(env)) s.addHours(env, hours);
    }

    private static double positiveHoursBetween(Instant a, Instant b) {
        long minutes = Duration.between(a, b).toMinutes();
        if (minutes < 0) minutes = -minutes;
        return minutes / 60.0;
    }

    private static boolean isReportedEnv(Env e) {
        return e == Env.SALESDEV
                || e == Env.SERVICEDEV
                || e == Env.DEVINT
                || e == Env.RELEASEQA
                || e == Env.PREINT
                || e == Env.ACCRELEASE
                || e == Env.ACCSTAGE;
    }

    private static Instant parseSalesforceInstant(String s) {
        if (s == null) return null;
        String t = s.trim();
        if (t.isEmpty()) return null;
        try {
            OffsetDateTime odt = OffsetDateTime.parse(t, SF_DT);
            return odt.toInstant();
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    private static Env canonicalEnv(String raw) {
        if (raw == null) return Env.UNKNOWN;
        String t = raw.trim();
        if (t.isEmpty()) return Env.UNKNOWN;
        String u = t.toUpperCase(Locale.ROOT);

        // Normalize common variants
        if (u.equals("RELEASEQA") || u.equals("RELEASE QA")) return Env.RELEASEQA;
        if (u.equals("SALESDEV") || u.equals("SALES DEV")) return Env.SALESDEV;
        if (u.equals("SERVICEDEV") || u.equals("SERVICE DEV")) return Env.SERVICEDEV;
        if (u.equals("DEVINT") || u.equals("DEV INT")) return Env.DEVINT;
        if (u.equals("PREINT") || u.equals("PRE INT")) return Env.PREINT;

        // DSAT variants: sometimes ACCRELEASE appears as ACCINT
        if (u.equals("ACCRELEASE") || u.equals("ACC RELEASE") || u.equals("ACCINT") || u.equals("ACC INT")) {
            return Env.ACCRELEASE;
        }

        if (u.equals("ACCSTAGE") || u.equals("ACC STAGE")) return Env.ACCSTAGE;
        if (u.equals("ACCPROD") || u.equals("ACC PROD")) return Env.ACCPROD;

        return Env.OTHER;
    }

    private static String safe(List<String> cols, int idx) {
        if (idx < 0 || idx >= cols.size()) return "";
        String s = cols.get(idx);
        return s == null ? "" : s.trim();
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    /** Basic CSV parser supporting quoted fields with commas and escaped quotes. */
    private static List<String> parseCsvLine(String line) {
        List<String> out = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (inQuotes) {
                if (c == '"') {
                    if (i + 1 < line.length() && line.charAt(i + 1) == '"') {
                        sb.append('"');
                        i++;
                    } else {
                        inQuotes = false;
                    }
                } else {
                    sb.append(c);
                }
            } else {
                if (c == ',') {
                    out.add(sb.toString());
                    sb.setLength(0);
                } else if (c == '"') {
                    inQuotes = true;
                } else {
                    sb.append(c);
                }
            }
        }
        out.add(sb.toString());
        return out;
    }

    private static void writeOutput(Path output, Map<String, StoryAgg> stories) throws IOException {
        Files.createDirectories(output.toAbsolutePath().getParent());

        try (BufferedWriter bw = Files.newBufferedWriter(output, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {

            List<String> outHeader = Arrays.asList(
                    "User Story Id",
                    "User Story Name",
                    "Release",
                    "User Story Created Date",
                    "Epic",
                    "Team",
                    "Time in SalesDev (Hours)",
                    "Time in ServiceDev (Hours)",
                    "Time in DEVINT (Hours)",
                    "Time in ReleaseQA (Hours)",
                    "Time in PREINT (Hours)",
                    "Time in ACCRELEASE (Hours)",
                    "Time in ACCSTAGE (Hours)",
                    "Time to Prod (Hours)"
            );
            bw.write(toCsvLine(outHeader));
            bw.newLine();

            for (StoryAgg s : stories.values()) {
                double salesDev = round2(s.hours.get(Env.SALESDEV));
                double serviceDev = round2(s.hours.get(Env.SERVICEDEV));
                double devint = round2(s.hours.get(Env.DEVINT));
                double releaseQa = round2(s.hours.get(Env.RELEASEQA));
                double preint = round2(s.hours.get(Env.PREINT));
                double accrelease = round2(s.hours.get(Env.ACCRELEASE));
                double accstage = round2(s.hours.get(Env.ACCSTAGE));
                double timeToProd = round2(salesDev + serviceDev + devint + releaseQa + preint + accrelease + accstage);

                List<String> row = Arrays.asList(
                        nz(s.storyId),
                        nz(s.storyName),
                        nz(s.release),
                        nz(s.storyCreatedDateRaw),
                        nz(s.epic),
                        nz(s.team),
                        String.valueOf(salesDev),
                        String.valueOf(serviceDev),
                        String.valueOf(devint),
                        String.valueOf(releaseQa),
                        String.valueOf(preint),
                        String.valueOf(accrelease),
                        String.valueOf(accstage),
                        String.valueOf(timeToProd)
                );

                bw.write(toCsvLine(row));
                bw.newLine();
            }
        }
    }

    private static String nz(String s) {
        return s == null ? "" : s;
    }

    private static double round2(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    private static String toCsvLine(List<String> cols) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cols.size(); i++) {
            if (i > 0) sb.append(',');
            sb.append(escapeCsv(cols.get(i)));
        }
        return sb.toString();
    }

    private static String escapeCsv(String s) {
        if (s == null) return "";
        boolean needQuotes = s.contains(",") || s.contains("\n") || s.contains("\r") || s.contains("\"");
        if (!needQuotes) return s;
        return "\"" + s.replace("\"", "\"\"") + "\"";
    }
}

