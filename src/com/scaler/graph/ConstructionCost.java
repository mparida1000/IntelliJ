package com.scaler.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
 * Problem Description
Given a graph with A nodes and C weighted edges. Cost of constructing the graph is
* the sum of weights of all the edges in the graph.

Find the minimum cost of constructing the graph by selecting
* some given edges such that we can reach every other node from the 1st node.

NOTE: Return the answer modulo 109+7 as the answer can be large.
 */
public class ConstructionCost {

	public int solve(int A, int[][] B) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= A; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : B) {
            int source = edge[0];
            int destination = edge[1];
            int weight = edge[2];
            graph.get(source).add(new Edge(destination, weight));
            graph.get(destination).add(new Edge(source, weight));
        }

        int totalCost = 0;
        boolean[] visited = new boolean[A + 1];
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Edge(1, 0));

        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            int node = edge.destination;
            int weight = edge.weight;

            if (!visited[node]) {
                visited[node] = true;
                totalCost = (totalCost + weight) % 1000000007;

                for (Edge neighbor : graph.get(node)) {
                    if (!visited[neighbor.destination]) {
                        priorityQueue.add(neighbor);
                    }
                }
            }
        }

        return totalCost;
    }
	public static void main(String[] args) {
		ConstructionCost solution = new ConstructionCost();

	    int A = 5;
	    int[][] B = {
	        {1, 2, 1},
	        {1, 3, 3},
	        {2, 3, 4},
	        {2, 4, 6},
	        {3, 4, 2},
	        {3, 5, 5},
	        {4, 5, 7}
	    };

	    int minimumCost = solution.solve(A, B);
	    System.out.println("Minimum Cost: " + minimumCost);
	}

}
class Edge implements Comparable<Edge> {
    int destination;
    int weight;

    public Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}
