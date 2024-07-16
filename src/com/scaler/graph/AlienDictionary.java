package com.scaler.graph;

import java.util.*;
/*
There is a new alien language that uses the English alphabet. However, the order of the letters is unknown to you.

You are given a list of strings words from the alien language's dictionary. Now it is claimed that the strings in words are
sorted lexicographically
 by the rules of this new language.

If this claim is incorrect, and the given arrangement of string in words cannot correspond to any order of letters, return "".

Otherwise, return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there are multiple solutions, return any of them.
Example 1:
Input: words = ["wrt","wrf","er","ett","rftt"]
Output: "wertf"

Example 2:
Input: words = ["z","x"]
Output: "zx"

Example 3:
Input: words = ["z","x","z"]
Output: ""
Explanation: The order is invalid, so return "".
 */
public class AlienDictionary {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();

        // Initialize the graph
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                inDegree.putIfAbsent(c, 0);
            }
        }

        // Build the graph
        for (int i = 0; i < words.length - 1; i++) {
            String first = words[i];
            String second = words[i + 1];
            int len = Math.min(first.length(), second.length());
            boolean foundDifference = false;

            for (int j = 0; j < len; j++) {
                char c1 = first.charAt(j);
                char c2 = second.charAt(j);

                if (c1 != c2) {
                    if (!graph.get(c1).contains(c2)) {
                        graph.get(c1).add(c2);
                        inDegree.put(c2, inDegree.get(c2) + 1);
                    }
                    foundDifference = true;
                    break;
                }
            }

            if (!foundDifference && first.length() > second.length()) {
                return "";  // invalid order
            }
        }

        // Topological Sort using BFS
        Queue<Character> queue = new LinkedList<>();
        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                queue.add(c);
            }
        }

        StringBuilder order = new StringBuilder();
        while (!queue.isEmpty()) {
            char current = queue.poll();
            order.append(current);
            for (char neighbor : graph.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (order.length() != inDegree.size()) {
            return "";  // invalid order due to cycle
        }

        return order.toString();
    }

    public static void main(String[] args) {
        AlienDictionary solution = new AlienDictionary();

       // String[] words1 = {"wrt","wrf","er","ett","rftt"};
       // System.out.println(solution.alienOrder(words1));  // Output: "wertf"

        String[] words2 = {"abc","ab"};
        System.out.println(solution.alienOrder(words2));  // Output: "zx"

        //String[] words3 = {"z","x","z"};
        //System.out.println(solution.alienOrder(words3));  // Output: ""
    }
}

