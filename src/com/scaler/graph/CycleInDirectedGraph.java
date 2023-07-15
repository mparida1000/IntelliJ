package com.scaler.graph;
/*
 * Problem Description
Given an directed graph having A nodes. A matrix B of size M x 2 is given which represents the M edges such that 
there is a edge directed from node B[i][0] to node B[i][1].
Find whether the graph contains a cycle or not, return 1 if cycle is present else return 0.
NOTE:

The cycle must contain atleast two nodes.
There are no self-loops in the graph.
There are no multiple edges between two nodes.
The graph may or may not be connected.
Nodes are numbered from 1 to A.
Your solution will run on multiple test cases. If you are using global variables make sure to clear them.
 */
import java.util.ArrayList;

public class CycleInDirectedGraph {
	public int solve(int A, int[][] B) {
        ArrayList<Integer>[] adj = new ArrayList[A+1];
        for (int i = 1; i <= A; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < B.length; i++) {
            adj[B[i][0]].add(B[i][1]);
        }
        boolean[] visited = new boolean[A+1];
        for (int i = 1; i <= A; i++) {
            if (!visited[i]) {
                if (hasCycle(adj, visited, i)) {
                    return 1;
                }
            }
        }        
        return 0;
    }
    private boolean hasCycle(ArrayList<Integer>[] adj, boolean[] visited, int node) {
        visited[node] = true;        
        for (int i = 0; i < adj[node].size(); i++) {
            int adjNode = adj[node].get(i);            
            // if the adjacent node has already been visited, there is a cycle
            if (visited[adjNode]) {
                return true;
            }            
            // otherwise, recursively visit the adjacent node
            if (hasCycle(adj, visited, adjNode)) {
                return true;
            }
        }        
        // mark current node as unvisited before returning
        visited[node] = false;
        return false;
    }
	
	public static void main(String[] args) {
		int [][] B = {
				{1,2},
				{2,4},
				{4,3},
				{3,1},
				{4,5}
				
		};
		System.out.println(new CycleInDirectedGraph().solve(5, B));
	}

}
