package practice;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CopadoJobExecutionPromotionSummary {

    private static final String JOB_CONFLICT = "Copado Metadata Intelligence - Copado Merge : Conflict";
    private static final String JOB_SFDX_PROMOTE = "SFDX Promote";
    private static final String JOB_SFDX_DEPLOY = "SFDX Deploy";

    private static final Pattern PROMOTION_ID_PATTERN =
            Pattern.compile("\"promotionId\"\\s*:\\s*\"(.*?)\"", Pattern.DOTALL);

    private static final Pattern PROMOTION_BRANCH_PATTERN =
            Pattern.compile("\"promotionBranchName\"\\s*:\\s*\"(.*?)\"", Pattern.DOTALL);

    private static final Pattern USER_STORY_BRANCHES_ARRAY_PATTERN =
            Pattern.compile("\"userStoryBranches\"\\s*:\\s*\\[(.*?)\\]", Pattern.DOTALL);

    // Inside userStoryBranches array: capture each "value"
    private static final Pattern QUOTED_STRING_PATTERN =
            Pattern.compile("\"(.*?)\"", Pattern.DOTALL);

    public static void main(String[] args) throws Exception {
        String home = System.getProperty("user.home");
        String documentsDir = home + File.separator + "Documents";

        String defaultInput = documentsDir + File.separator + "copado__JobExecution_2.csv";
        String defaultOutput = documentsDir + File.separator + "copado__JobExecution_summary_by_promotion.csv";

        String inputPath = (args.length >= 1) ? args[0] : defaultInput;
        String outputPath = (args.length >= 2) ? args[1] : defaultOutput;

        File inFile = new File(inputPath);
        if (!inFile.exists()) {
            throw new FileNotFoundException(
                    "Input CSV not found at: " + inputPath + "\n" +
                            "Put it in Documents as copado__JobExecution.csv or pass a full path as arg."
            );
        }

        Map<String, Stats> statsByPromotion = new HashMap<>();

        try (
                Reader reader = new InputStreamReader(new FileInputStream(inputPath), StandardCharsets.UTF_8);
                CsvReader csv = new CsvReader(reader)
        ) {
            List<String> header = csv.readRecord();
            if (header == null) throw new IllegalArgumentException("Empty CSV: no header row found.");

            int idxJson = findColumnIndex(header, "copado__DataJson__c", 1);          // Col B fallback
            int idxJob  = findColumnIndex(header, "copado__Template__r.Name", 2);     // Col C fallback
            int idxTime = findColumnIndex(header, "Job_Execution_time__c", 3);       // Col D fallback

            List<String> row;
            while ((row = csv.readRecord()) != null) {
                while (row.size() < header.size()) row.add("");

                String dataJson = safeGet(row, idxJson);
                String jobName = safeGet(row, idxJob).trim();
                double timeMin = parseDoubleSafe(safeGet(row, idxTime));

                String promotionKey = extractPromotionKey(dataJson);
                if (promotionKey.isEmpty()) {
                    // If promotionId is not present, skip (or you can bucket as "UNKNOWN")
                    continue;
                }

                Stats st = statsByPromotion.computeIfAbsent(promotionKey, k -> new Stats());
                st.promotionKey = promotionKey;

                // store branches "as-is" (first non-empty value wins)
                if (st.userStoryBranchesRaw.isEmpty()) {
                    st.userStoryBranchesRaw = extractUserStoryBranchesRaw(dataJson);
                }

                st.totalJobs++;
                st.totalTimeMin += timeMin;

                if (JOB_CONFLICT.equals(jobName)) st.conflictCount++;

                if (JOB_SFDX_PROMOTE.equals(jobName)) {
                    st.sfdxPromoteCount++;
                    st.sfdxPromoteTimeMinTotal += timeMin;
                } else if (JOB_SFDX_DEPLOY.equals(jobName)) {
                    st.sfdxDeployCount++;
                    st.sfdxDeployTimeMinTotal += timeMin;
                }
            }
        }

        // Sort by total_jobs desc
        List<Stats> outRows = new ArrayList<>(statsByPromotion.values());
        outRows.sort((a, b) -> {
            int cmp = Integer.compare(b.totalJobs, a.totalJobs);
            if (cmp != 0) return cmp;
            return a.promotionKey.compareTo(b.promotionKey);
        });

        try (
                Writer writer = new OutputStreamWriter(new FileOutputStream(outputPath), StandardCharsets.UTF_8);
                CsvWriter out = new CsvWriter(writer)
        ) {
            out.writeRecord(Arrays.asList(
                    "promotion_key",
                    "userStoryBranches_raw",
                    "conflict_count",
                    "sfdx_promote_count",
                    "sfdx_deploy_count",
                    "sfdx_promote_time_min_total",
                    "sfdx_deploy_time_min_total",
                    "total_jobs",
                    "total_time_min"
            ));

            for (Stats st : outRows) {
                out.writeRecord(Arrays.asList(
                        st.promotionKey,
                        st.userStoryBranchesRaw,
                        String.valueOf(st.conflictCount),
                        String.valueOf(st.sfdxPromoteCount),
                        String.valueOf(st.sfdxDeployCount),
                        format2(st.sfdxPromoteTimeMinTotal),
                        format2(st.sfdxDeployTimeMinTotal),
                        String.valueOf(st.totalJobs),
                        format2(st.totalTimeMin)
                ));
            }
            out.flush();
        }

        System.out.println("✅ Done.");
        System.out.println("Input : " + inputPath);
        System.out.println("Output: " + outputPath);
    }

    // ---------- JSON extraction (regex-based, safe for your use-case) ----------

    private static String extractPromotionKey(String json) {
        if (json == null) return "";
        Matcher m = PROMOTION_ID_PATTERN.matcher(json);
        if (m.find()) return m.group(1).trim();

        // fallback to promotionBranchName if promotionId isn't present
        Matcher mb = PROMOTION_BRANCH_PATTERN.matcher(json);
        if (mb.find()) return mb.group(1).trim();

        return "";
    }

    private static String extractUserStoryBranchesRaw(String json) {
        if (json == null) return "";
        Matcher m = USER_STORY_BRANCHES_ARRAY_PATTERN.matcher(json);
        if (!m.find()) return "";

        String arrayBody = m.group(1);
        Matcher s = QUOTED_STRING_PATTERN.matcher(arrayBody);

        LinkedHashSet<String> uniq = new LinkedHashSet<>();
        while (s.find()) {
            String v = s.group(1).trim();
            if (!v.isEmpty()) uniq.add(v);
        }
        return String.join(";", uniq);
    }

    // ---------- Helpers ----------

    private static int findColumnIndex(List<String> header, String colName, int fallbackIndex) {
        for (int i = 0; i < header.size(); i++) {
            if (colName.equalsIgnoreCase(header.get(i).trim())) return i;
        }
        return (fallbackIndex >= 0 && fallbackIndex < header.size()) ? fallbackIndex : -1;
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

    static class Stats {
        String promotionKey = "";
        String userStoryBranchesRaw = "";

        int conflictCount = 0;

        int sfdxPromoteCount = 0;
        int sfdxDeployCount = 0;
        double sfdxPromoteTimeMinTotal = 0.0;
        double sfdxDeployTimeMinTotal = 0.0;

        int totalJobs = 0;
        double totalTimeMin = 0.0;
    }

    // ---------- Minimal CSV reader/writer (handles commas + newlines inside quotes) ----------

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
                    } else {
                        sb.append((char) c);
                    }
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
