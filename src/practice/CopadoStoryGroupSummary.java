package practice;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CopadoStoryGroupSummary {

    // Job names
    private static final String JOB_SFDX_PROMOTE = "SFDX Promote";
    private static final String JOB_SFDX_DEPLOY  = "SFDX Deploy";

    // Conflict phrase appears in ErrorMessage
    private static final String CONFLICT_PHRASE = "Copado Metadata Intelligence - Copado Merge : Conflict";

    // JSON regex helpers (good enough for your JSON payloads)
    private static final Pattern USER_STORY_BRANCHES_ARRAY_PATTERN =
            Pattern.compile("\"userStoryBranches\"\\s*:\\s*\\[(.*?)\\]", Pattern.DOTALL);

    private static final Pattern QUOTED_STRING_PATTERN =
            Pattern.compile("\"(.*?)\"", Pattern.DOTALL);

    private static final Pattern US_PATTERN =
            Pattern.compile("US-\\d+");

    private static final Pattern PROMOTION_ID_PATTERN =
            Pattern.compile("\"promotionId\"\\s*:\\s*\"(.*?)\"", Pattern.DOTALL);

    public static void main(String[] args) throws Exception {

        // IDE-friendly defaults
        String home = System.getProperty("user.home");
        String documentsDir = home + File.separator + "Documents";
        String defaultInput = documentsDir + File.separator + "copado__JobExecution_2.csv";
        String defaultOutput = documentsDir + File.separator + "copado__JobExecution_storyGroup_summary.csv";

        String inputPath = (args.length >= 1) ? args[0] : defaultInput;
        String outputPath = (args.length >= 2) ? args[1] : defaultOutput;

        File inFile = new File(inputPath);
        if (!inFile.exists()) {
            throw new FileNotFoundException(
                    "Input CSV not found at: " + inputPath + "\n" +
                            "Place it in Documents as copado__JobExecution.csv or pass input/output args."
            );
        }

        Map<String, Stats> statsByStoryGroup = new HashMap<>();
        long rawConflictRows = 0;

        try (
                Reader reader = new InputStreamReader(new FileInputStream(inputPath), StandardCharsets.UTF_8);
                CsvReader csv = new CsvReader(reader)
        ) {
            List<String> header = csv.readRecord();
            if (header == null) throw new IllegalArgumentException("Empty CSV: no header row found.");

            // Column indices by name (with safe fallbacks)
            int idxJson = findColumnIndex(header, "copado__DataJson__c", 1);          // Col B fallback
            int idxJob  = findColumnIndex(header, "copado__Template__r.Name", 2);     // Col C fallback
            int idxTime = findColumnIndex(header, "Job_Execution_time__c", 3);       // Col D fallback
            int idxStatus = findColumnIndex(header, "copado__Status__c", 4);          // Col E fallback
            int idxErr = findColumnIndex(header, "copado__ErrorMessage__c", 5);       // Col F fallback

            List<String> row;
            while ((row = csv.readRecord()) != null) {
                while (row.size() < header.size()) row.add("");

                String dataJson = safeGet(row, idxJson);
                String jobName = safeGet(row, idxJob).trim();
                double timeMin = parseDoubleSafe(safeGet(row, idxTime));
                String statusNorm = normalizeStatus(safeGet(row, idxStatus));
                String errMsg = safeGet(row, idxErr);

                // Build story_group key: "US-...;US-...;US-..." (NOT split into separate US rows)
                String storyGroup = extractStoryGroupKey(dataJson);
                if (storyGroup.isEmpty()) continue;

                Stats st = statsByStoryGroup.computeIfAbsent(storyGroup, k -> new Stats(storyGroup));

                // promotionId for unique promotion count
                String promotionId = extractPromotionId(dataJson);
                if (!promotionId.isEmpty()) st.uniquePromotions.add(promotionId);

                // Totals
                st.totalJobs++;
                st.totalTimeMin += timeMin;

                // Conflict count from ErrorMessage
                boolean isConflict = errMsg != null && errMsg.contains(CONFLICT_PHRASE);
                if (isConflict) {
                    st.conflictCount++;
                    rawConflictRows++;
                }

                // Job counts + times
                if (JOB_SFDX_PROMOTE.equals(jobName)) {
                    st.sfdxPromoteCount++;
                    st.sfdxPromoteTimeMinTotal += timeMin;
                } else if (JOB_SFDX_DEPLOY.equals(jobName)) {
                    st.sfdxDeployCount++;
                    st.sfdxDeployTimeMinTotal += timeMin;
                }

                // Status counts
                st.statusCounts.merge(statusNorm, 1, Integer::sum);
            }
        }

        // Prepare sorted output
        List<Stats> outRows = new ArrayList<>(statsByStoryGroup.values());
        outRows.sort((a, b) -> {
            int cmp = Integer.compare(b.conflictCount, a.conflictCount);
            if (cmp != 0) return cmp;
            cmp = Integer.compare(b.getStatus("errored"), a.getStatus("errored"));
            if (cmp != 0) return cmp;
            cmp = Integer.compare(b.totalJobs, a.totalJobs);
            if (cmp != 0) return cmp;
            return a.storyGroup.compareTo(b.storyGroup);
        });

        // Write CSV
        try (
                Writer writer = new OutputStreamWriter(new FileOutputStream(outputPath), StandardCharsets.UTF_8);
                CsvWriter out = new CsvWriter(writer)
        ) {
            out.writeRecord(Arrays.asList(
                    "story_group",
                    "unique_promotions",
                    "conflict_count",
                    "errored_count",
                    "canceled_count",
                    "in_progress_count",
                    "queued_count",
                    "successful_count",
                    "sfdx_promote_count",
                    "sfdx_deploy_count",
                    "sfdx_promote_time_min_total",
                    "sfdx_deploy_time_min_total",
                    "total_jobs",
                    "total_time_min"
            ));

            long summaryConflictSum = 0;

            for (Stats st : outRows) {
                summaryConflictSum += st.conflictCount;

                out.writeRecord(Arrays.asList(
                        st.storyGroup,
                        String.valueOf(st.uniquePromotions.size()),
                        String.valueOf(st.conflictCount),

                        String.valueOf(st.getStatus("errored")),
                        String.valueOf(st.getStatus("canceled")),
                        String.valueOf(st.getStatus("in progress")),
                        String.valueOf(st.getStatus("queued")),
                        String.valueOf(st.getStatus("successful")),

                        String.valueOf(st.sfdxPromoteCount),
                        String.valueOf(st.sfdxDeployCount),
                        format2(st.sfdxPromoteTimeMinTotal),
                        format2(st.sfdxDeployTimeMinTotal),

                        String.valueOf(st.totalJobs),
                        format2(st.totalTimeMin)
                ));
            }

            out.flush();

            System.out.println("✅ Done.");
            System.out.println("Input : " + inputPath);
            System.out.println("Output: " + outputPath);
            System.out.println("Unique story-groups: " + statsByStoryGroup.size());
            System.out.println("Conflict rows (raw counted while reading): " + rawConflictRows);
            System.out.println("Conflict sum (summary): " + summaryConflictSum);
        }
    }

    // ----------------- core extraction -----------------

    // story_group = "US-0039081;US-0039093;US-0039092;US-0039107"
    // treat entire joined string as ONE key (no splitting to individual rows)
    private static String extractStoryGroupKey(String json) {
        if (json == null || json.isBlank()) return "";

        Matcher m = USER_STORY_BRANCHES_ARRAY_PATTERN.matcher(json);
        if (!m.find()) return "";

        String arrayBody = m.group(1);
        Matcher s = QUOTED_STRING_PATTERN.matcher(arrayBody);

        LinkedHashSet<String> uniq = new LinkedHashSet<>();
        while (s.find()) {
            String raw = s.group(1).trim(); // e.g., feature/US-0039081
            if (raw.isEmpty()) continue;

            Matcher us = US_PATTERN.matcher(raw);
            if (us.find()) {
                uniq.add(us.group()); // keep only US-#####
            } else {
                // fallback: last segment after slash
                String last = raw.contains("/") ? raw.substring(raw.lastIndexOf('/') + 1) : raw;
                if (!last.isBlank()) uniq.add(last.trim());
            }
        }

        return String.join(";", uniq);
    }

    private static String extractPromotionId(String json) {
        if (json == null) return "";
        Matcher m = PROMOTION_ID_PATTERN.matcher(json);
        return m.find() ? m.group(1).trim() : "";
    }

    private static String normalizeStatus(String s) {
        if (s == null) return "";
        String t = s.trim().toLowerCase(Locale.ROOT);

        if (t.contains("success")) return "successful";
        if (t.contains("cancel")) return "canceled";
        if (t.contains("queue")) return "queued";
        if (t.contains("progress") || t.contains("running")) return "in progress";
        if (t.contains("error") || t.contains("fail")) return "errored";

        // keep raw if unknown
        return t;
    }

    // ----------------- small utilities -----------------

    private static int findColumnIndex(List<String> header, String colName, int fallbackIndex) {
        for (int i = 0; i < header.size(); i++) {
            if (colName.equalsIgnoreCase(header.get(i).trim())) return i;
        }
        return fallbackIndex;
    }

    private static String safeGet(List<String> row, int idx) {
        if (idx < 0 || idx >= row.size()) return "";
        String v = row.get(idx);
        return v == null ? "" : v;
    }

    private static double parseDoubleSafe(String s) {
        if (s == null) return 0.0;
        String t = s.trim();
        if (t.isEmpty()) return 0.0;
        try { return Double.parseDouble(t); }
        catch (Exception ex) { return 0.0; }
    }

    private static String format2(double v) {
        return String.format(Locale.ROOT, "%.2f", v);
    }

    // ----------------- data model -----------------

    static class Stats {
        final String storyGroup;
        final Set<String> uniquePromotions = new HashSet<>();

        int conflictCount = 0;

        int sfdxPromoteCount = 0;
        int sfdxDeployCount = 0;
        double sfdxPromoteTimeMinTotal = 0.0;
        double sfdxDeployTimeMinTotal = 0.0;

        int totalJobs = 0;
        double totalTimeMin = 0.0;

        final Map<String, Integer> statusCounts = new HashMap<>();

        Stats(String storyGroup) { this.storyGroup = storyGroup; }

        int getStatus(String key) { return statusCounts.getOrDefault(key, 0); }
    }

    // ----------------- Minimal CSV Reader/Writer (handles commas + newlines inside quotes) -----------------

    static class CsvReader implements Closeable {
        private final Reader r;
        private int nextChar = -2;

        CsvReader(Reader r) { this.r = r; }

        List<String> readRecord() throws IOException {
            List<String> fields = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            boolean inQuotes = false;
            boolean started = false;

            while (true) {
                int c = read();
                if (c == -1) {
                    if (!started) return null;
                    fields.add(sb.toString());
                    return fields;
                }
                started = true;

                if (inQuotes) {
                    if (c == '"') {
                        int peek = read();
                        if (peek == '"') sb.append('"');
                        else { inQuotes = false; unread(peek); }
                    } else sb.append((char) c);
                } else {
                    if (c == '"') inQuotes = true;
                    else if (c == ',') { fields.add(sb.toString()); sb.setLength(0); }
                    else if (c == '\n') { fields.add(sb.toString()); return fields; }
                    else if (c == '\r') {
                        int peek = read();
                        if (peek != '\n') unread(peek);
                        fields.add(sb.toString());
                        return fields;
                    } else sb.append((char) c);
                }
            }
        }

        private int read() throws IOException {
            if (nextChar != -2) { int c = nextChar; nextChar = -2; return c; }
            return r.read();
        }

        private void unread(int c) { if (c != -1) nextChar = c; }

        @Override public void close() throws IOException { r.close(); }
    }

    static class CsvWriter implements Closeable {
        private final Writer w;
        CsvWriter(Writer w) { this.w = w; }

        void writeRecord(List<String> fields) throws IOException {
            for (int i = 0; i < fields.size(); i++) {
                if (i > 0) w.write(',');
                writeField(fields.get(i));
            }
            w.write("\n");
        }

        private void writeField(String v) throws IOException {
            if (v == null) v = "";
            boolean mustQuote = v.indexOf(',') >= 0 || v.indexOf('"') >= 0 || v.indexOf('\n') >= 0 || v.indexOf('\r') >= 0;
            if (!mustQuote) { w.write(v); return; }
            w.write('"');
            for (int i = 0; i < v.length(); i++) {
                char c = v.charAt(i);
                if (c == '"') w.write("\"\"");
                else w.write(c);
            }
            w.write('"');
        }

        void flush() throws IOException { w.flush(); }
        @Override public void close() throws IOException { w.close(); }
    }
}
