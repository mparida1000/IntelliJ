package leetcode.graph;

import java.util.*;

//Leet 399
public class EvaluateDivision {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for(int i = 0 ; i < equations.size() ; i++){
            String num = equations.get(i).get(0);
            String den = equations.get(i).get(1);
            double quo = values[i];

            graph.putIfAbsent(num, new HashMap<>());
            graph.putIfAbsent(den, new HashMap<>());

            graph.get(num).put(den, quo);
            graph.get(den).put(num, 1.0/quo);
        }

        double [] results = new double[queries.size()];
        for(int i = 0 ; i < queries.size() ; i++){
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);
            results[i] = dfs(graph, start, end, 1.0, new HashSet<>());
        }
        return results;
    }

    private double dfs(Map<String, Map<String, Double>> graph, String curr, String dest, double result, Set<String> visited){
        if(!graph.containsKey(curr) || visited.contains(curr)) return -1.0;
        if(curr.equals(dest)) return result;
        visited.add(curr);
        Map<String, Double> neighbors = graph.get(curr);
        for(String neigh : neighbors.keySet()) {
            double value = neighbors.get(neigh);
            double subResult = dfs(graph, neigh, dest, value * result, visited);
            if(subResult != -1){
                return  subResult;
            }
        }
        visited.remove(curr);
        return -1.0;
    }

        public static void main(String[] args) {
            EvaluateDivision solver = new EvaluateDivision();

            // Test Case 1
            List<List<String>> equations1 = Arrays.asList(
                    Arrays.asList("a", "b"),
                    Arrays.asList("b", "c")
            );
            double[] values1 = {2.0, 3.0};
            List<List<String>> queries1 = Arrays.asList(
                    Arrays.asList("a", "c"),
                    Arrays.asList("b", "a"),
                    Arrays.asList("a", "e"),
                    Arrays.asList("x", "y")
            );
            double[] results1 = solver.calcEquation(equations1, values1, queries1);
            System.out.println("Test Case 1 Results: " + Arrays.toString(results1));

            // Test Case 2
            List<List<String>> equations2 = Arrays.asList(
                    Arrays.asList("a", "b"),
                    Arrays.asList("b", "c"),
                    Arrays.asList("b", "d"),
                    Arrays.asList("c", "e"),
                    Arrays.asList("d", "f")
                    );
            double[] values2 = {2.0, 4.0, 2.0, 3.0, 1.0};
            List<List<String>> queries2 = Arrays.asList(
                    Arrays.asList("a", "f"),
                    Arrays.asList("d", "e"),
                    Arrays.asList("e", "f"),
                    Arrays.asList("a", "e")
            );
            double[] results2 = solver.calcEquation(equations2, values2, queries2);
            System.out.println("Test Case 2 Results: " + Arrays.toString(results2));

            // Test Case 3
            /*
            List<List<String>> equations3 = Arrays.asList(
                    Arrays.asList("a", "b"),
                    Arrays.asList("b", "c"),
                    Arrays.asList("c", "d")
            );
            double[] values3 = {2.0, 3.0, 4.0};
            List<List<String>> queries3 = Arrays.asList(
                    Arrays.asList("a", "d"),
                    Arrays.asList("b", "d"),
                    Arrays.asList("a", "c")
            );
            double[] results3 = solver.calcEquation(equations3, values3, queries3);
            System.out.println("Test Case 3 Results: " + Arrays.toString(results3));

             */
        }
    }

