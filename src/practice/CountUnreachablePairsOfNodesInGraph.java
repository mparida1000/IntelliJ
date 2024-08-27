package practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/*
You are given an integer n. There is an undirected graph with n nodes, numbered from 0 to n - 1.
You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
Return the number of pairs of different nodes that are unreachable from each other.
Input: n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
Output: 14
Explanation: There are 14 pairs of nodes that are unreachable from each other:
[[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]].
Therefore, we return 14.

 */
public class CountUnreachablePairsOfNodesInGraph {

    public long countPairs(int n, int[][] edges) {
        // Create an adjacency list to represent the graph
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        // Populate the adjacency list with the given edges
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        // Array to keep track of visited nodes
        boolean[] visited = new boolean[n];
        // List to store sizes of connected components
        List<Integer> componentSizes = new ArrayList<>();

        // Perform BFS to find all connected components
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int size = bfs(i, adjList, visited);
                componentSizes.add(size);
            }
        }

        // Calculate total possible pairs of nodes
        long totalPairs = (long) n * (n - 1) / 2;
        // Calculate reachable pairs within each connected component
        long reachablePairs = 0;

        for (int size : componentSizes) {
            reachablePairs += (long) size * (size - 1) / 2;
        }

        // Unreachable pairs = total pairs - reachable pairs
        return totalPairs - reachablePairs;
    }
    private int bfs(int start, List<List<Integer>> adjList, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        int size = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            size++;

            for (int neighbor : adjList.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        return size;
    }

    public static void main(String[] args) {
        int n = 7;
        int[][] edges = {
                {0, 2},
                {0, 5},
                {2, 4},
                {1, 6},
                {5, 4}
        };
        CountUnreachablePairsOfNodesInGraph solution = new CountUnreachablePairsOfNodesInGraph();
        System.out.println(solution.countPairs(7, edges));
    }
}
