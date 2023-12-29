package leetcode.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class ShortestPathVisitingAllNodes {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int allVisited = (1 << n) - 1;
        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < n; i++) {
            queue.offer(new int[]{1 << i, i, 0});
            visited.add((1 << i) * 16 + i);
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == allVisited) {
                return cur[2];
            }

            for (int neighbor : graph[cur[1]]) {
                int newMask = cur[0] | (1 << neighbor);
                int hashValue = newMask * 16 + neighbor;

                if (!visited.contains(hashValue)) {
                    visited.add(hashValue);
                    queue.offer(new int[]{newMask, neighbor, cur[2] + 1});
                }
            }
        }

        return -1;
    }
    public static void main(String[] args) {
        ShortestPathVisitingAllNodes solution = new ShortestPathVisitingAllNodes();

        int[][] testCase1 = {{1,2,3}, {0}, {0}, {0}};
        System.out.println("Test Case 1: " + (solution.shortestPathLength(testCase1) == 4));

        int[][] testCase2 = {{1}, {0, 2}, {1, 3}, {2}};
        System.out.println("Test Case 2: " + (solution.shortestPathLength(testCase2) == 4));

        int[][] testCase3 = {{1, 2}, {0, 2}, {0, 1}};
        System.out.println("Test Case 3: " + (solution.shortestPathLength(testCase3) == 2));

        int[][] testCase4 = {{1,4}, {0,3,4,2}, {1,3}, {1,2,4}, {0,1,3}};
        System.out.println("Test Case 4: " + (solution.shortestPathLength(testCase4) == 4));
    }
    static class State {
        int node, cover;
        State(int node, int cover) {
            this.node = node;
            this.cover = cover;
        }
    }
}
