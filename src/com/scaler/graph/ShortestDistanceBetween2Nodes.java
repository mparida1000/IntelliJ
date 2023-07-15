package com.scaler.graph;
/*
 * Problem Description
Given a weighted undirected graph having A nodes, a source node C and destination node D.
Find the shortest distance from C to D and if it is impossible to reach node D from C then return -1.
You are expected to do it in Time Complexity of O(A + M).
Note:
There are no self-loops in the graph.
No multiple edges between two pair of vertices.
The graph may or may not be connected.
Nodes are Numbered from 0 to A-1.
Your solution will run on multiple testcases. If you are using global variables make sure to clear them.
Problem Constraints
1 <= A <= 10 ^ 5
0 <= B[i][0], B[i][1] < A
1 <= B[i][2] <= 2
0 <= C < A
0 <= D < A
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class ShortestDistanceBetween2Nodes {
	private static final int INF = Integer.MAX_VALUE;
    public int solve(int A, int[][] B, int C, int D) {
        List<List<Node>> graph = buildGraph(A, B);
        int[] distances = new int[A];
        Arrays.fill(distances, INF);
        distances[C] = 0;

        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        minHeap.offer(new Node(C, 0));

        while (!minHeap.isEmpty()) {
            Node curr = minHeap.poll();

            if (curr.id == D)
                return curr.distance;

            for (Node neighbor : graph.get(curr.id)) {
                if (curr.distance + neighbor.distance < distances[neighbor.id]) {
                    distances[neighbor.id] = curr.distance + neighbor.distance;
                    minHeap.offer(new Node(neighbor.id, distances[neighbor.id]));
                }
            }
        }

        return -1;
    }
    private List<List<Node>> buildGraph(int A, int[][] B) {
        List<List<Node>> graph = new ArrayList<>();

        for (int i = 0; i < A; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : B) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            graph.get(u).add(new Node(v, weight));
            graph.get(v).add(new Node(u, weight));
        }

        return graph;
    }
    
    public static void main(String[] args) {
    	ShortestDistanceBetween2Nodes shortestDistanceInGraph = new ShortestDistanceBetween2Nodes();

        int A = 6;
        int[][] B = {
            {0, 1, 2},
            {0, 2, 4},
            {1, 3, 1},
            {1, 4, 7},
            {2, 3, 3},
            {3, 5, 2},
            {4, 5, 5}
        };
        int C = 0;
        int D = 5;

        int shortestDistance = shortestDistanceInGraph.solve(A, B, C, D);
        System.out.println("Shortest distance from node " + C + " to node " + D + ": " + shortestDistance);
    }

}
class Node implements Comparable<Node> {
    int id;
    int distance;

    public Node(int id, int distance) {
        this.id = id;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.distance, other.distance);
    }
}
