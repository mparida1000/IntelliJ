package practice;

import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class UserStoryTimeCalculatorV2 {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    public static void main(String[] args) {
        try {
            String inputPath = System.getProperty("user.home") + "/Documents/devdata/User Story Data.csv";
            String outputPath = System.getProperty("user.home") + "/Documents/devdata/User_Story_Time_Analysis.csv";

            System.out.println("Processing data from: " + inputPath);

// Read and process data
            Map<String, UserStoryData> storyMap = processCSV(inputPath);

// Write results
            writeResults(outputPath, storyMap);

            System.out.println("Analysis completed!");
            System.out.println("Output written to: " + outputPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class UserStoryData {
        String id;
        String name;
        Map<String, List<Transition>> transitions = new HashMap<>();
        Map<String, Double> timeInEnv = new HashMap<>();

        UserStoryData(String id, String name) {
            this.id = id;
            this.name = name;
// Initialize time tracking for all environments
            String[] envs = {"SalesDev", "ServiceDev", "DEVINT", "ReleaseQA", "PREINT", "ACCRELEASE", "ACCSTAGE"};
            for (String env : envs) {
                timeInEnv.put(env, 0.0);
            }
        }

        void addTransition(String fromEnv, String toEnv, LocalDateTime time) {
            transitions.computeIfAbsent(fromEnv, k -> new ArrayList<>())
                    .add(new Transition(toEnv, time));
        }

        void calculateTimes() {
// For each environment, find all entry and exit times
            for (String env : timeInEnv.keySet()) {
                List<LocalDateTime> entryTimes = new ArrayList<>();
                List<LocalDateTime> exitTimes = new ArrayList<>();

// Find when we entered this environment
                for (Map.Entry<String, List<Transition>> entry : transitions.entrySet()) {
                    for (Transition trans : entry.getValue()) {
                        if (trans.toEnv.equals(env)) {
                            entryTimes.add(trans.time);
                        }
                        if (entry.getKey().equals(env)) {
                            exitTimes.add(trans.time);
                        }
                    }
                }

// Sort times
                entryTimes.sort(LocalDateTime::compareTo);
                exitTimes.sort(LocalDateTime::compareTo);

// Calculate total time
                double totalHours = 0.0;
                for (int i = 0; i < Math.min(entryTimes.size(), exitTimes.size()); i++) {
                    long minutes = ChronoUnit.MINUTES.between(entryTimes.get(i), exitTimes.get(i));
                    totalHours += minutes / 60.0;
                }

                timeInEnv.put(env, totalHours);
            }
        }

        double getTotalTimeToProd() {
            return timeInEnv.values().stream().mapToDouble(Double::doubleValue).sum();
        }
    }

    static class Transition {
        String toEnv;
        LocalDateTime time;

        Transition(String toEnv, LocalDateTime time) {
            this.toEnv = toEnv;
            this.time = time;
        }
    }

    private static Map<String, UserStoryData> processCSV(String filePath) throws IOException {
        Map<String, UserStoryData> storyMap = new HashMap<>();
        List<String> lines = Files.readAllLines(Paths.get(filePath));

// Skip header
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.trim().isEmpty()) continue;

            String[] parts = parseLine(line);
            if (parts.length < 11) continue;

            String userStoryId = parts[1];
            String userStoryName = parts[2];
            String oldValue = parts[7];
            String newValue = parts[8];
            String createdDate = parts[10];

            if (oldValue.isEmpty() || newValue.isEmpty()) {
                continue;
            }

            UserStoryData data = storyMap.computeIfAbsent(userStoryId,
                    k -> new UserStoryData(userStoryId, userStoryName));

            try {
                LocalDateTime time = LocalDateTime.parse(createdDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                data.addTransition(oldValue, newValue, time);
            } catch (Exception e) {
// Try alternative format
                try {
                    LocalDateTime time = LocalDateTime.parse(createdDate, DATE_FORMATTER);
                    data.addTransition(oldValue, newValue, time);
                } catch (Exception e2) {
                    System.err.println("Failed to parse date: " + createdDate);
                }
            }
        }

// Calculate times for all stories
        for (UserStoryData data : storyMap.values()) {
            data.calculateTimes();
        }

        return storyMap;
    }

    private static String[] parseLine(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(current.toString());
                current = new StringBuilder();
            } else {
                current.append(c);
            }
        }
        result.add(current.toString());
        return result.toArray(new String[0]);
    }

    private static void writeResults(String filePath, Map<String, UserStoryData> storyMap) throws IOException {
        List<String> lines = new ArrayList<>();

// Header
        lines.add("User Story ID,User Story Name,Time in SalesDev (hours),Time in ServiceDev (hours)," +
                "Time in DEVINT (hours),Time in ReleaseQA (hours),Time in PREINT (hours)," +
                "Time in ACCRELEASE (hours),Time in ACCSTAGE (hours),Time to Prod (hours)");

// Data
        for (UserStoryData data : storyMap.values()) {
            StringBuilder sb = new StringBuilder();
            sb.append(data.id).append(",");
            sb.append(data.name).append(",");
            sb.append(String.format("%.2f", data.timeInEnv.get("SalesDev"))).append(",");
            sb.append(String.format("%.2f", data.timeInEnv.get("ServiceDev"))).append(",");
            sb.append(String.format("%.2f", data.timeInEnv.get("DEVINT"))).append(",");
            sb.append(String.format("%.2f", data.timeInEnv.get("ReleaseQA"))).append(",");
            sb.append(String.format("%.2f", data.timeInEnv.get("PREINT"))).append(",");
            sb.append(String.format("%.2f", data.timeInEnv.get("ACCRELEASE"))).append(",");
            sb.append(String.format("%.2f", data.timeInEnv.get("ACCSTAGE"))).append(",");
            sb.append(String.format("%.2f", data.getTotalTimeToProd()));

            lines.add(sb.toString());
        }

        Files.write(Paths.get(filePath), lines);
        System.out.println("Processed " + storyMap.size() + " user stories");
    }
}