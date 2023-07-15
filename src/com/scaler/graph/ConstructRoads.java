package com.scaler.graph;
/*
 * Problem Description
A country consist of N cities connected by N - 1 roads. King of that country want to construct maximum number of 
roads such that the new country formed remains bipartite country.

Bipartite country is a country, whose cities can be partitioned into 2 sets in such a way, that for each road (u, v) 
that belongs to the country, u and v belong to different sets. Also, there should be no multiple roads between two cities and no self loops.

Return the maximum number of roads king can construct. Since the answer could be large return answer % 109 + 7.

NOTE: All cities can be visited from any city.
 */
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class ConstructRoads {

	public int solve(int A, int[][] B) {
		boolean bipartite = true;
		int [] color = new int[A+1];
		Arrays.fill(color, -1);;
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		for(int i = 0 ; i <= A ; i++) {
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
				while(!queue.isEmpty()){
					int u = queue.poll();
					for(int v : adj.get(u)) {
						if(color[v] == -1) {
							color[v] = 1 - color[u];
							queue.offer(v);
						}else if(color[v] == color[u]) {
							bipartite = false;
						}
					}
				}
			}
			
		}
		if(!bipartite) return 0;
		long cnt_1 = 0;
		int m = 1000000007;
		for(int i = 1 ; i <=  A ; i++) {
			cnt_1+=color[i] == 1 ? 1 : 0;
		}
		long res = (((A-cnt_1)*cnt_1)%m - B.length)%m;
		return (int)res;
    }
	
	public static void main(String[] args) {
		int [][] B = {
				{1,3},
				{1,4},
				{3,2},
				{3,5}
		};
		System.out.println(new ConstructRoads().solve(5, B));
	}
}
