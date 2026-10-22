package practice;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CopadoJobExecutionSummary {

    // ---- Patterns ----
    private static final Pattern US_PATTERN = Pattern.compile("US-\\d+");

    // "userStoryBranches": [ ... ]  (matches across newlines)
    private static final Pattern BRANCHES_ARRAY_PATTERN =
            Pattern.compile("\"userStoryBranches\"\\s*:\\s*\\[(.*?)\\]", Pattern.DOTALL);

    // ---- Job names ----
    private static final String JOB_CONFLICT = "Copado Metadata Intelligence - Copado Merge : Conflict";
    private static final String JOB_SFDX_PROMOTE = "SFDX Promote";
    private static final String JOB_SFDX_DEPLOY = "SFDX Deploy";

    public static void main(String[] args) throws Exception {

        String home = System.getProperty("user.home");
        String documentsDir = home + File.separator + "Documents";

        String defaultInput = documentsDir + File.separator + "copado__JobExecution_2.csv";
        String defaultOutput = documentsDir + File.separator + "copado__JobExecution_summary_by_userStory.csv";

        String inputPath;
        String outputPath;

        if (args.length == 0) {
            inputPath = defaultInput;
            outputPath = defaultOutput;
        } else if (args.length == 1) {
            String a0 = args[0];
            inputPath = a0.contains(File.separator) ? a0 : (documentsDir + File.separator + a0);
            outputPath = defaultOutput;
        } else {
            inputPath = args[0];
            outputPath = args[1];
        }

        File inFile = new File(inputPath);
        if (!inFile.exists()) {
            throw new FileNotFoundException(
                    "Input CSV not found at: " + inputPath + "\n" +
                            "Place the file in Documents with name: copado__JobExecution.csv\n" +
                            "Or pass input/output paths as program arguments."
            );
        }

        Map<String, Stats> statsByUserStory = new HashMap<>();

        try (
                Reader reader = new InputStreamReader(new FileInputStream(inputPath), StandardCharsets.UTF_8);
                CsvReader csv = new CsvReader(reader)
        ) {
            List<String> header = csv.readRecord();
            if (header == null) throw new IllegalArgumentException("Empty CSV: no header row found.");

            int idxJson = findColumnIndex(header, "copado__DataJson__c", 1);                 // Col B fallback
            int idxJob = findColumnIndex(header, "copado__Template__r.Name", 2);            // Col C fallback
            int idxTime = findColumnIndex(header, "Job_Execution_time__c", 3);              // Col D fallback
            int idxStatus = findColumnIndex(header, "copado__Status__c", 4);                // Col E fallback
            // int idxErr = findColumnIndex(header, "copado__ErrorMessage__c", 5);           // Col F (not needed for summary now)

            List<String> row;
            while ((row = csv.readRecord()) != null) {
                while (row.size() < header.size()) row.add("");

                String json = safeGet(row, idxJson);
                String job = safeGet(row, idxJob).trim();
                double timeMin = parseDoubleSafe(safeGet(row, idxTime));
                String statusNorm = normalizeStatus(safeGet(row, idxStatus));

                List<String> userStories = extractUserStories(json);
                if (userStories.isEmpty()) continue;

                for (String us : userStories) {
                    Stats st = statsByUserStory.computeIfAbsent(us, k -> new Stats());

                    st.totalJobs++;
                    st.totalTimeMin += timeMin;

                    if (JOB_CONFLICT.equals(job)) st.conflictCount++;

                    if (JOB_SFDX_PROMOTE.equals(job)) {
                        st.sfdxPromoteCount++;
                        st.sfdxPromoteTimeMinTotal += timeMin;
                    } else if (JOB_SFDX_DEPLOY.equals(job)) {
                        st.sfdxDeployCount++;
                        st.sfdxDeployTimeMinTotal += timeMin;
                    }

                    switch (statusNorm) {
                        case "errored": st.erroredCount++; break;
                        case "canceled": st.canceledCount++; break;
                        case "in progress": st.inProgressCount++; break;
                        case "queued": st.queuedCount++; break;
                        case "successful": st.successfulCount++; break;
                        default: /* ignore */ break;
                    }
                }
            }
        }

        // Write summary CSV
        List<String> outHeader = Arrays.asList(
                "userStory",
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
        );

        List<Map.Entry<String, Stats>> rows = new ArrayList<>(statsByUserStory.entrySet());
        rows.sort((a, b) -> {
            int cmp = Integer.compare(b.getValue().totalJobs, a.getValue().totalJobs);
            if (cmp != 0) return cmp;
            return a.getKey().compareTo(b.getKey());
        });

        try (
                Writer writer = new OutputStreamWriter(new FileOutputStream(outputPath), StandardCharsets.UTF_8);
                CsvWriter out = new CsvWriter(writer)
        ) {
            out.writeRecord(outHeader);

            for (Map.Entry<String, Stats> e : rows) {
                String us = e.getKey();
                Stats st = e.getValue();

                out.writeRecord(Arrays.asList(
                        us,
                        String.valueOf(st.conflictCount),
                        String.valueOf(st.erroredCount),
                        String.valueOf(st.canceledCount),
                        String.valueOf(st.inProgressCount),
                        String.valueOf(st.queuedCount),
                        String.valueOf(st.successfulCount),
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

    // ---------- Helpers ----------

    private static int findColumnIndex(List<String> header, String colName, int fallbackIndex) {
        for (int i = 0; i < header.size(); i++) {
            if (colName.equalsIgnoreCase(header.get(i).trim())) return i;
        }
        if (fallbackIndex >= 0 && fallbackIndex < header.size()) return fallbackIndex;
        return -1;
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

    private static String normalizeStatus(String s) {
        if (s == null) return "";
        String t = s.trim().toLowerCase(Locale.ROOT);
        if (t.contains("success")) return "successful";
        if (t.contains("error") || t.contains("fail")) return "errored";
        if (t.contains("cancel")) return "canceled";
        if (t.contains("queue")) return "queued";
        if (t.contains("progress") || t.contains("running")) return "in progress";
        return t;
    }

    private static List<String> extractUserStories(String json) {
        if (json == null || json.trim().isEmpty()) return Collections.emptyList();

        // Best-effort: pull userStoryBranches array
        Matcher arrayM = BRANCHES_ARRAY_PATTERN.matcher(json);
        String scope = json;
        if (arrayM.find()) {
            scope = arrayM.group(1);
        }

        // Extract US-#### patterns
        Matcher usM = US_PATTERN.matcher(scope);
        LinkedHashSet<String> uniq = new LinkedHashSet<>();
        while (usM.find()) uniq.add(usM.group());
        return new ArrayList<>(uniq);
    }

    private static String format2(double v) {
        return String.format(Locale.ROOT, "%.2f", v);
    }

    static class Stats {
        int conflictCount = 0;

        int erroredCount = 0;
        int canceledCount = 0;
        int inProgressCount = 0;
        int queuedCount = 0;
        int successfulCount = 0;

        int sfdxPromoteCount = 0;
        int sfdxDeployCount = 0;
        double sfdxPromoteTimeMinTotal = 0.0;
        double sfdxDeployTimeMinTotal = 0.0;

        int totalJobs = 0;
        double totalTimeMin = 0.0;
    }

    // ---------- Minimal CSV Reader/Writer (handles commas + newlines inside quotes) ----------

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
