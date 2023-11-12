package com.scaler.graph;
/*
 * Problem Description
Given a undirected graph having A nodes. A matrix B of size M x 2 is given which represents the edges such 
that there is an edge between B[i][0] and B[i][1].
Find whether the given graph is bipartite or not.
A graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge 
in the graph has one node in A and another node in B
Note:
There are no self-loops in the graph.
No multiple edges between two pair of vertices.
The graph may or may not be connected.
Nodes are Numbered from 0 to A-1.
Your solution will run on multiple testcases. If you are using global variables make sure to clear them.
 */
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class CheckBipartiteGraph {
	
	public int solve(int A, int[][] B) {  
		int [] color = new int[A];
		Arrays.fill(color, -1);;
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		for(int i = 0 ; i < A ; i++) {
			adj.add(new ArrayList<Integer>());
		}
		for(int i = 0 ; i < B.length ; i++) {
			int u = B[i][0];
			int v = B[i][1];
			adj.get(u).add(v);
			adj.get(v).add(u);
		}
		Queue<Integer> queue = new ArrayDeque<>();
		for(int i = 0 ; i < A ; i++) {
			if(color[i] == -1) {
				queue.offer(i);
				color[i] = 0;
			}
			while(!queue.isEmpty()){
				int u = queue.poll();
				for(int v : adj.get(u)) {
					if(color[v] == -1) {
						color[v] = 1 - color[u];
						queue.offer(v);
					}else if(color[v] == color[u]) {
						return 0;
					}
				}
			}			
		}
		return 1;
    }
	
	public static void main(String[] args) {
		int [][] B = {
				{7,8},
				{1,2},
				{0,9},
				{1,3},
				{6,7},
				{0,3},
				{2,5},
				{3,8},
		{4,8}

				};
		System.out.println(new CheckBipartiteGraph().solve(10, B));
	}

}
