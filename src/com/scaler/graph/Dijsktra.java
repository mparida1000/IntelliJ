package com.scaler.graph;
/*
 * Problem Description
Given a weighted undirected graph having A nodes and M weighted edges, and a source node C.
You have to find an integer array D of size A such that:
D[i]: Shortest distance from the C node to node i.
If node i is not reachable from C then -1.
Note:
There are no self-loops in the graph.
There are no multiple edges between two pairs of vertices.
The graph may or may not be connected.
Nodes are numbered from 0 to A-1.
Your solution will run on multiple test cases. If you are using global variables, make sure to clear them.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Dijsktra {
	static class Node implements Comparable<Node> {
	    int vertex;
	    int distance;

	    public Node(int vertex, int distance) {
	        this.vertex = vertex;
	        this.distance = distance;
	    }

	    public int compareTo(Node other) {
	        return Integer.compare(distance, other.distance);
	    }
	}
	public int[] solve(int A, int[][] B, int C) {
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < A; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < B.length; i++) {
            int source = B[i][0];
            int destination = B[i][1];
            int weight = B[i][2];
            graph.get(source).add(new Node(destination, weight));
            graph.get(destination).add(new Node(source, weight));
        }

        int[] distances = new int[A];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[C] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(C, 0));

        while (!pq.isEmpty()) {
            Node currNode = pq.poll();
            int currVertex = currNode.vertex;

            for (Node neighbor : graph.get(currVertex)) {
                int newDistance = distances[currVertex] + neighbor.distance;
                if (newDistance < distances[neighbor.vertex]) {
                    distances[neighbor.vertex] = newDistance;
                    pq.offer(new Node(neighbor.vertex, newDistance));
                }
            }
        }

        for (int i = 0; i < A; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                distances[i] = -1;
            }
        }

        return distances;
    }
	public static void main(String[] args) {
        int A = 5;
        int[][] B = {	{0, 1, 1}, 
        				{0, 2, 4}, 
        				{1, 2, 2}, 
        				{1, 3, 5}, 
        				{2, 3, 1}, 
        				{2, 4, 3}, 
        				{3, 4, 2}
        				};
        int C = 0;

        Dijsktra solution = new Dijsktra();
        int[] distances = solution.solve(A, B, C);

        System.out.println("Shortest distances from node " + C + ":");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("Node " + i + ": " + distances[i]);
        }
    }
}

