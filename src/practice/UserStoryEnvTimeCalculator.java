package practice;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * Reads a Copado/Salesforce field history style CSV (sorted by User Story Id then CreatedDate)
 * and produces one-row-per-user-story output with time spent (hours) in each environment.
 *
 * Default input  : ~/Documents/devdata/User Story Data.csv
 * Default output : ~/Documents/devdata/User Story Env Times.csv
 *
 * You can also pass input and output paths as args:
 *   java UserStoryEnvTimeCalculator "<input.csv>" "<output.csv>"
 */
public class UserStoryEnvTimeCalculator {

    // CSV columns (0-based) based on your uploaded file header.
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

    // Canonical environment names to report
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
        String storyCreatedDate;
        String epic;
        String team;
        final List<Event> events = new ArrayList<>();
        final EnumMap<Env, Double> hours = new EnumMap<>(Env.class);

        StoryAgg(String storyId) {
            this.storyId = storyId;
            for (Env e : Env.values()) {
                hours.put(e, 0.0);
            }
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

            List<String> headerCols = parseCsvLine(header);
            if (headerCols.size() <= COL_CREATED_DATE) {
                throw new IllegalArgumentException("CSV has fewer columns than expected. Found " + headerCols.size());
            }

            String line;
            long lineNo = 1;
            while ((line = br.readLine()) != null) {
                lineNo++;
                if (line.trim().isEmpty()) continue;

                List<String> cols = parseCsvLine(line);
                if (cols.size() <= COL_CREATED_DATE) {
                    System.err.println("Skipping line " + lineNo + " (not enough columns): " + line);
                    continue;
                }

                String storyId = safe(cols, COL_USER_STORY_ID);
                if (storyId.isEmpty()) continue;

                StoryAgg agg = map.computeIfAbsent(storyId, StoryAgg::new);

                // Fill base fields once (first seen)
                if (agg.storyName == null || agg.storyName.isEmpty()) agg.storyName = safe(cols, COL_USER_STORY_NAME);
                if (agg.release == null || agg.release.isEmpty()) agg.release = safe(cols, COL_RELEASE);
                if (agg.storyCreatedDate == null || agg.storyCreatedDate.isEmpty()) agg.storyCreatedDate = safe(cols, COL_US_CREATED_DATE);
                if (agg.epic == null || agg.epic.isEmpty()) agg.epic = safe(cols, COL_EPIC);
                if (agg.team == null || agg.team.isEmpty()) agg.team = safe(cols, COL_TEAM);

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
     * Computes hours spent in each environment by attributing time between successive transitions
     * to the environment the story was *in* during that interval (the previous new value).
     *
     * This naturally handles back-and-forth movements (e.g., DEVINT -> SALESDEV -> DEVINT).
     */
    private static void computeDurations(StoryAgg s) {
        if (s.events.size() < 2) return;

        Event first = s.events.get(0);
        Env currentEnv = first.newEnv;
        Instant prevTime = first.time;

        for (int i = 1; i < s.events.size(); i++) {
            Event e = s.events.get(i);
            Instant currTime = e.time;

            long minutes = Duration.between(prevTime, currTime).toMinutes();
            if (minutes < 0) minutes = -minutes; // defensive
            double hours = minutes / 60.0;

            if (isReportedEnv(currentEnv)) {
                s.addHours(currentEnv, hours);
            }

            currentEnv = e.newEnv;
            prevTime = currTime;
        }
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
                        nullToEmpty(s.storyId),
                        nullToEmpty(s.storyName),
                        nullToEmpty(s.release),
                        nullToEmpty(s.storyCreatedDate),
                        nullToEmpty(s.epic),
                        nullToEmpty(s.team),
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

    // -------- helpers --------

    private static String safe(List<String> cols, int idx) {
        if (idx < 0 || idx >= cols.size()) return "";
        String s = cols.get(idx);
        return s == null ? "" : s.trim();
    }

    private static String nullToEmpty(String s) {
        return s == null ? "" : s;
    }

    private static double round2(double v) {
        return Math.round(v * 100.0) / 100.0;
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

        if (u.equals("RELEASEQA") || u.equals("RELEASE QA")) return Env.RELEASEQA;
        if (u.equals("SALESDEV") || u.equals("SALES DEV")) return Env.SALESDEV;
        if (u.equals("SERVICEDEV") || u.equals("SERVICE DEV")) return Env.SERVICEDEV;
        if (u.equals("DEVINT") || u.equals("DEV INT")) return Env.DEVINT;
        if (u.equals("PREINT") || u.equals("PRE INT")) return Env.PREINT;
        if (u.equals("ACCRELEASE") || u.equals("ACC RELEASE")) return Env.ACCRELEASE;
        if (u.equals("ACCSTAGE") || u.equals("ACC STAGE")) return Env.ACCSTAGE;
        if (u.equals("ACCPROD") || u.equals("ACC PROD")) return Env.ACCPROD;

        return Env.OTHER;
    }

    /**
     * Basic CSV parser supporting quoted fields with commas and escaped quotes.
     */
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
