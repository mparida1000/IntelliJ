package practice;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CopadoJobExecutionEnricher {

    // Extract US-######## patterns from inside the userStoryBranches array (e.g., "feature/US-0038774")
    private static final Pattern US_PATTERN = Pattern.compile("US-\\d+");

    // Capture the array content for userStoryBranches: "userStoryBranches" : [ ... ]
// DOTALL so it matches across newlines.
    private static final Pattern BRANCHES_ARRAY_PATTERN =
            Pattern.compile("\"userStoryBranches\"\\s*:\\s*\\[(.*?)\\]", Pattern.DOTALL);

    // Capture deploymentDryRun: "deploymentDryRun" : true/false or "true"/"false"
    private static final Pattern DRYRUN_PATTERN =
            Pattern.compile("\"deploymentDryRun\"\\s*:\\s*(true|false|\"true\"|\"false\")", Pattern.CASE_INSENSITIVE);

    public static void main(String[] args) throws Exception {

        String home = System.getProperty("user.home");
        String documentsDir = home + File.separator + "Documents";

        String defaultInput = documentsDir + File.separator + "copado__JobExecution.csv";
        String defaultOutput = documentsDir + File.separator + "copado__JobExecution_enriched.csv";

        String inputPath;
        String outputPath;

        if (args.length == 0) {
// No args → auto read from Documents
            inputPath = defaultInput;
            outputPath = defaultOutput;
        } else if (args.length == 1) {
// One arg → treat it as filename (or path). If only a filename, assume Documents.
            String a0 = args[0];
            inputPath = a0.contains(File.separator) ? a0 : (documentsDir + File.separator + a0);

// Output defaults to Documents enriched name
            outputPath = defaultOutput;
        } else {
// Two args → use exactly as provided
            inputPath = args[0];
            outputPath = args[1];
        }

        File inFile = new File(inputPath);
        if (!inFile.exists()) {
            throw new FileNotFoundException(
                    "Input CSV not found at: " + inputPath + "\n" +
                            "Place the file in Documents with name: copado__JobExecution.csv\n" +
                            "Or pass input path/filename as program argument."
            );
        }

        try (
                Reader reader = new InputStreamReader(new FileInputStream(inputPath), java.nio.charset.StandardCharsets.UTF_8);
                Writer writer = new OutputStreamWriter(new FileOutputStream(outputPath), java.nio.charset.StandardCharsets.UTF_8)
        ) {
            CsvReader csv = new CsvReader(reader);
            CsvWriter out = new CsvWriter(writer);

            List<String> header = csv.readRecord();
            if (header == null) throw new IllegalArgumentException("Empty CSV: no header row found.");

            int dataJsonIdx = findColumnIndex(header, "copado__DataJson__c", 1);

            List<String> newHeader = new ArrayList<>(header);
            newHeader.add("userStoryBranches_from_json");
            newHeader.add("deploymentDryRun_from_json");
            out.writeRecord(newHeader);

            List<String> row;
            while ((row = csv.readRecord()) != null) {
                while (row.size() < header.size()) row.add("");

                String dataJson = safeGet(row, dataJsonIdx);
                String stories = extractUserStories(dataJson);
                String dryRun = extractDryRun(dataJson);

                List<String> newRow = new ArrayList<>(row);
                newRow.add(stories);
                newRow.add(dryRun);
                out.writeRecord(newRow);
            }

            out.flush();
        }

        System.out.println("✅ Done.");
        System.out.println("Input : " + inputPath);
        System.out.println("Output: " + outputPath);
    }

    private static int findColumnIndex(List<String> header, String colName, int fallbackIndex) {
        for (int i = 0; i < header.size(); i++) {
            if (colName.equalsIgnoreCase(header.get(i).trim())) return i;
        }
// fallback for "Col B/C/D" style CSVs
        if (fallbackIndex >= 0 && fallbackIndex < header.size()) return fallbackIndex;
        return -1;
    }

    private static String safeGet(List<String> row, int idx) {
        if (idx < 0 || idx >= row.size()) return "";
        String v = row.get(idx);
        return v == null ? "" : v;
    }

    private static String extractUserStories(String json) {
        if (json == null) return "";

        Matcher arrayM = BRANCHES_ARRAY_PATTERN.matcher(json);
        if (!arrayM.find()) return "";

        String arrayContent = arrayM.group(1);
        Matcher usM = US_PATTERN.matcher(arrayContent);

        LinkedHashSet<String> uniq = new LinkedHashSet<>();
        while (usM.find()) {
            uniq.add(usM.group());
        }
        return String.join(";", uniq);
    }

    private static String extractDryRun(String json) {
        if (json == null) return "";

        Matcher m = DRYRUN_PATTERN.matcher(json);
        if (!m.find()) return "";

        String raw = m.group(1).trim().toLowerCase(Locale.ROOT);
        raw = raw.replace("\"", "");
        if ("true".equals(raw) || "false".equals(raw)) return raw;
        return "";
    }

// -------------------------
// Minimal CSV reader/writer that supports:
// - quoted fields
// - commas inside quotes
// - newlines inside quotes
// -------------------------

    static class CsvReader {
        private final Reader r;
        private int nextChar = -2; // -2 means uninitialized

        CsvReader(Reader r) { this.r = r; }

        List<String> readRecord() throws IOException {
            List<String> fields = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            boolean inQuotes = false;
            boolean started = false;

            while (true) {
                int c = read();
                if (c == -1) {
                    if (!started) return null; // EOF and nothing read
                    fields.add(sb.toString());
                    return fields;
                }
                started = true;

                if (inQuotes) {
                    if (c == '"') {
                        int peek = read();
                        if (peek == '"') {
                            sb.append('"'); // escaped quote
                        } else {
                            inQuotes = false;
                            unread(peek);
                        }
                    } else {
                        sb.append((char) c);
                    }
                } else {
                    if (c == '"') {
                        inQuotes = true;
                    } else if (c == ',') {
                        fields.add(sb.toString());
                        sb.setLength(0);
                    } else if (c == '\n') {
                        fields.add(sb.toString());
                        return fields;
                    } else if (c == '\r') {
                        int peek = read();
                        if (peek != '\n') unread(peek);
                        fields.add(sb.toString());
                        return fields;
                    } else {
                        sb.append((char) c);
                    }
                }
            }
        }

        private int read() throws IOException {
            if (nextChar != -2) {
                int c = nextChar;
                nextChar = -2;
                return c;
            }
            return r.read();
        }

        private void unread(int c) {
            if (c == -1) return;
            nextChar = c;
        }
    }

    static class CsvWriter {
        private final Writer w;

        CsvWriter(Writer w) { this.w = w; }

        void writeRecord(List<String> fields) throws IOException {
            for (int i = 0; i < fields.size(); i++) {
                if (i > 0) w.write(',');
                writeField(fields.get(i));
            }
            w.write("\n");
        }

        void writeField(String v) throws IOException {
            if (v == null) v = "";
            boolean mustQuote = v.indexOf(',') >= 0 || v.indexOf('"') >= 0 || v.indexOf('\n') >= 0 || v.indexOf('\r') >= 0;
            if (!mustQuote) {
                w.write(v);
                return;
            }
            w.write('"');
            for (int i = 0; i < v.length(); i++) {
                char c = v.charAt(i);
                if (c == '"') w.write("\"\"");
                else w.write(c);
            }
            w.write('"');
        }

        void flush() throws IOException { w.flush(); }
    }
}
