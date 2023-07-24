package com.scaler.graph;
/*
 * Problem Description
There are A islands and there are M bridges connecting them. Each bridge has some cost
* attached to it.
We need to find bridges with minimal cost such that all islands are connected.
It is guaranteed that input data will contain at least one possible scenario in
* which all islands are connected with each other.

Input Format
The first argument contains an integer, A, representing the number of islands.
The second argument contains an 2-d integer matrix, B, of size M x 3 where Island B[i][0] and B[i][1] 
are connected using a bridge of cost B[i][2].

 -- THIS IS PRIM'S ALGO ----
 
TC - O(NlogN) --> N is # of edges
SC - O(max(A, N))
 */
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class CommutableIslands {

	public int solve(int A, int[][] B) {
        // Create an adjacency list to store the bridges and their costs
        List<List<Bridge>> adjList = new ArrayList<>();
        for (int i = 0; i <= A; i++) {
            adjList.add(new ArrayList<>());
        }

        // Add the bridges to the adjacency list
        for (int[] bridge : B) {
            int island1 = bridge[0];
            int island2 = bridge[1];
            int cost = bridge[2];
            adjList.get(island1).add(new Bridge(island2, cost));
            adjList.get(island2).add(new Bridge(island1, cost));
        }

        // Create a visited array to keep track of visited islands
        boolean[] visited = new boolean[A + 1];

        // Create a priority queue to store the bridges in increasing order of their costs
        PriorityQueue<Bridge> pq = new PriorityQueue<>();

        // Start from island 1
        int startIsland = 1;
        visited[startIsland] = true;

        // Add all the bridges connected to the start island to the priority queue
        for (Bridge bridge : adjList.get(startIsland)) {
            pq.offer(bridge);
        }

        int minimalCost = 0;

        // Apply Prim's algorithm until all islands are visited
        while (!pq.isEmpty()) {
            Bridge bridge = pq.poll();
            int island = bridge.island;
            int cost = bridge.cost;

            // If the island is already visited, skip it
            if (visited[island]) {
                continue;
            }

            // Mark the island as visited and add the cost to the minimal cost
            visited[island] = true;
            minimalCost += cost;

            // Add all the bridges connected to the visited island to the priority queue
            for (Bridge nextBridge : adjList.get(island)) {
                pq.offer(nextBridge);
            }
        }
        return minimalCost;
    }
	
	public static void main(String[] args) {
		CommutableIslands solution = new CommutableIslands();

        // Test case 1
        int A1 = 4;
        int[][] B1 = {
            {1, 2, 1},
            {2, 3, 4},
            {1, 4, 3},
            {4, 3, 2},
            {1, 3, 10}
        };
        // The minimal cost to connect all islands is 6 (1 + 2 + 3)
        int minimalCost1 = solution.solve(A1, B1);
        System.out.println("Minimal Cost: " + minimalCost1); // Output: 6

        // Test case 2
        int A2 = 5;
        int[][] B2 = {
            {1, 2, 1},
            {2, 3, 4},
            {1, 4, 3},
            {4, 3, 2},
            {1, 3, 10},
            {5, 3, 5}
        };
        // The minimal cost to connect all islands is 6 (1 + 2 + 3)
        int minimalCost2 = solution.solve(A2, B2);
        System.out.println("Minimal Cost: " + minimalCost2); // Output: 6
    }
}
class Bridge implements Comparable<Bridge> {
    int island;
    int cost;

    public Bridge(int island, int cost) {
        this.island = island;
        this.cost = cost;
    }

    @Override
    public int compareTo(Bridge other) {
        return Integer.compare(this.cost, other.cost);
    }
}
