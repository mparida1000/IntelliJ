package com.scaler.graph;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem Description
You are given N towns (1 to N). All towns are connected via unique directed path as mentioned in the input.
Given 2 towns find whether you can reach the first town from the second without repeating any edge.
B C : query to find whether B is reachable from C.
Input contains an integer array A of size N and 2 integers B and C ( 1 <= B, C <= N ).
There exist a directed edge from A[i] to i+1 for every 1 <= i < N. Also, it's guaranteed that A[i] <= i 
for every 1 <= i < N.
NOTE: Array A is 0-indexed. A[0] = 1 which you can ignore as it doesn't represent any edge.
 */
public class HW_FirstDepthFirstSearch {

	public int solve(int[] A, final int B, final int C) {
		// create adjacency list representation of the directed graph
        int n = A.length+1;
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            adj[A[i]].add(i + 1);
        }

        // perform depth-first search from node C to check if B is reachable
        boolean[] visited = new boolean[n];
        return dfs(adj, visited, B, C) ? 1 : 0;
    }
	private boolean dfs(List<Integer>[] adj, boolean[] visited, int dest, int curr) {
        if (curr == dest) {
            return true;
        }
        visited[curr] = true;
        for (int next : adj[curr]) {
            if (!visited[next]) {
                if (dfs(adj, visited, dest, next)) {
                    return true;
                }
            }
        }
        return false;
    }
	
	public static void main(String[] args) {
		int [] A = {1,1};
		System.out.println(new HW_FirstDepthFirstSearch().solve(A, 1, 2));
	}
}
