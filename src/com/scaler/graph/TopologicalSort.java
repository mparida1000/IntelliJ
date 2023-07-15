package com.scaler.graph;
/*
 * Problem Description
Given an directed acyclic graph having A nodes. A matrix B of size M x 2 is given which represents the M edges such that there is a edge 
directed from node B[i][0] to node B[i][1].

Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge uv, vertex u comes 
before v in the ordering. Topological Sorting for a graph is not possible if the graph is not a DAG.

Return the topological ordering of the graph and if it doesn't exist then return an empty array.

If there is a solution return the correct ordering. If there are multiple solutions print the lexographically smallest one.

Ordering (a, b, c) is said to be lexographically smaller than ordering (e, f, g) if a < e or if(a==e) then b < f and so on.

NOTE:

There are no self-loops in the graph.
The graph may or may not be connected.
Nodes are numbered from 1 to A.
Your solution will run on multiple test cases. If you are using global variables make sure to clear them.
 */
import java.util.ArrayList;
import java.util.PriorityQueue;

public class TopologicalSort {
	public int[] solve(int A, int[][] B) {
		int [] indgree = new int[A+1];
		for(int i = 0 ; i < B.length ; i++) {
			indgree[B[i][1]]++;
		}
		PriorityQueue<Integer> q = new PriorityQueue<>();
		for(int i = 1 ; i < indgree.length ; i++) {
			if(indgree[i] == 0)
				q.offer(i);
		}
		ArrayList<Integer> result = new ArrayList<>();
		while(!q.isEmpty()) {
			int node = q.poll();
			result.add(node);
			for(int [] edge : B) {
				if(edge[0] == node) {
					indgree[edge[1]]--;
					if(indgree[edge[1]] == 0) {
						q.add(edge[1]);
					}
				}
			}
		}
		if(result.size() < A) {
			return new int[] {};
		}
		
		int[] ans = new int[A];
        for (int i = 0; i < A; i++) {
            ans[i] = result.get(i);
        }
        return ans;		
    }
	
	public static void main(String[] args) {
		int [][] B = {  
		{6, 3} ,
        {6, 1} ,
        {5, 1} ,
        {5, 2} ,
        {3, 4} ,
        {4, 2} 
        };
		int [] op = new TopologicalSort().solve(6, B);
		for(int i : op) {
			System.out.print(i + " ");
		}
	}
	
}
