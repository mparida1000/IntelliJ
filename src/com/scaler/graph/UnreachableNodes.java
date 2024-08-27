package com.scaler.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*You are given an integer n. There is an undirected graph with n nodes, numbered from 0 to n - 1.
You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
Return the number of pairs of different nodes that are unreachable from each other.
 */
public class UnreachableNodes {
    public long countPairs(int n, int[][] edges) {
        long reachable = 0 ;
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0 ; i < n ; i++){
            adj.add(new ArrayList<>());
        }
        for(int [] edge : edges){
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        boolean [] visited = new boolean[n];
        List<Integer> sizes = new ArrayList<>();
        for(int i = 0 ; i < n ; i++){
            int size = bfs(adj, i, visited);
            sizes.add(size);
        }

        long tolEdges = (long)n*(n-1)/2;
        for(int size : sizes){
            reachable += (long)size*(size-1)/2;
        }
        return tolEdges - reachable;
    }

    private int bfs(List<List<Integer>> adj, int start, boolean [] visited){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        int size=0;
        visited[start] = true;
        while(!queue.isEmpty()){
            int node = queue.poll();
            size++;
            for(int neigh : adj.get(node)){
                if(!visited[neigh]){
                    visited[neigh] = true;
                    queue.add(neigh);
                }
            }
        }
        return size;
    }
    public static void main(String[] args) {
        int [][] edges =  {
                {0,2},
                {2,4},
                {4,5},
                {5,0},
                {1,6}
        };
        System.out.println(new UnreachableNodes().countPairs(7, edges));
    }
}
