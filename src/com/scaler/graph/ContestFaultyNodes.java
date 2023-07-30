package com.scaler.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
You are given a network of C nodes, labeled from 1 to C, and a 2D array A where Ali] =[u, v, w]
represents a undirected edge from node u to node v with a signal travel time of w.
However due to some fault in the network some of the nodes stopped working.

You are given array B which contains faulty nodes of the network.

You need to calculate the minimum time it takes for a signal to reach from node 1 to node C in the network,
without going through faulty nodes. If it is impossible for the signal to reach
nodes C starting from node 1, return -1.
 */
public class ContestFaultyNodes {
    public int solve(int [][] A, int[] B, int C){
        boolean [] faultArr = new boolean[C+1];
        for(int node : B){
            faultArr[node] = true;
        }
        if(faultArr[1])return -1;
        List<List<Node>> adj = buildGraph(A, faultArr, C);
        int [] dist = new int[C+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        minHeap.add(new Node(1, 0));

        while(!minHeap.isEmpty()){
            Node curr = minHeap.poll();
            if(curr.id == C) return curr.distance;

            for(Node neigh : adj.get(curr.id)){
                if(curr.distance + neigh.distance < dist[neigh.id]){
                    dist[neigh.id] = curr.distance + neigh.distance;
                    minHeap.add(new Node(neigh.id, dist[neigh.id]));
                }
            }

        }
        return -1;
    }
    public List<List<Node>> buildGraph(int [][] A, boolean [] fault, int C){
        List<List<Node>> adj = new ArrayList<>();
        for(int i = 0 ; i <= C ; i++){
            adj.add(new ArrayList<>());
        }
        for(int [] edge : A){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            if(!fault[u] && !fault[v]){
                adj.get(u).add(new Node(v,w));
                adj.get(v).add(new Node(u,w));
            }
        }
        return adj;
    }

    public static void main(String[] args) {
        int [][] A = {
                {1,2,2},
                {2,4,1},
                {1,3,3},
                {4,3,2}
        };
        int [] B ={2};
        int C = 4;
        System.out.println(new ContestFaultyNodes().solve(A,B,C));
    }
}
