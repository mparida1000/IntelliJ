package com.scaler.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Problem Description
Given an directed graph having A nodes labelled from 1 to A containing M edges given by 
matrix B of size M x 2such that there is a edge directed from node

B[i][0] to node B[i][1].

Find whether a path exists from node 1 to node A.

Return 1 if path exists else return 0.

NOTE:
There are no self-loops in the graph.
There are no multiple edges between two nodes.
The graph may or may not be connected.
Nodes are numbered from 1 to A.
Your solution will run on multiple test cases. If you are using global variables make sure to clear them.
 */
public class PathInDirectedGraph {
	public int solve(int A, int[][] B) {
		ArrayList<ArrayList<Integer>> adj  = new ArrayList<>();
		for(int i = 0 ; i <= A ; i++ ) {
			adj.add(new ArrayList<Integer>());
		}
		for(int [] edge : B) {
			adj.get(edge[0]).add(edge[1]);
		}
		Queue<Integer> q = new LinkedList<>();
        q.add(1);
        boolean[] visited = new boolean[A+1];
        while(!q.isEmpty()) {
        	int v = q.poll();
        	visited[v] = true;
        	for(Integer e : adj.get(v)) {
            	if(e == A)
            		return 1;
            	if(!visited[e])
            		q.add(e);
            }
        }
        return 0;        
    }
	
	public static void main(String[] args) {
		int [][] B = {
				{1,2},
				{2,3},
				{4,3},
				{4,5},
				{5,1},
				{3,1}
		};
		System.out.println(new PathInDirectedGraph().solve(5, B));
	}
}
