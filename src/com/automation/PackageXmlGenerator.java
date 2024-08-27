package com.automation;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackageXmlGenerator {

    public static void main(String[] args) {
        String csvFile = "/Users/mp1863/Documents/compsforexport/comps.csv";

        // Determine the directory of the CSV file
        File csv = new File(csvFile);
        String outputXmlFile = csv.getParent() + File.separator + "package.xml";

        Map<String, List<String>> metadataMap = new HashMap<>();

        // Read the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length != 2) {
                    continue;  // Skip lines that don't have exactly two values
                }

                String metadataName = values[1].trim();
                String metadataType = values[0].trim();

                metadataMap.computeIfAbsent(metadataType, k -> new ArrayList<>()).add(metadataName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Generate the package.xml
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputXmlFile))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<Package xmlns=\"http://soap.sforce.com/2006/04/metadata\">\n");

            for (Map.Entry<String, List<String>> entry : metadataMap.entrySet()) {
                writer.write("    <types>\n");

                for (String name : entry.getValue()) {
                    writer.write("        <members>" + name + "</members>\n");
                }

                writer.write("        <name>" + entry.getKey() + "</name>\n");
                writer.write("    </types>\n");
            }

            writer.write("    <version>55.0</version>\n");  // Adjust the version as needed
            writer.write("</Package>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("package.xml generated successfully in the same directory as the CSV file!");
    }
}

