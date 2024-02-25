package com.scaler.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CycleInGraphBFSKahn {
    public int solve(int A, int [][] B){
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i <=A ; i++){
            adj.add(new ArrayList<Integer>());
        }
        int [] indegree = new int[A+1];
        for(int i = 0 ; i < B.length ; i++){
            adj.get(B[i][0]).add(B[i][1]);
            indegree[B[i][1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= A; i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }
        int count = 0;
        while(!queue.isEmpty()){
            int node = queue.poll();
            count++;
            for(int neigh : adj.get(node)){
                indegree[neigh]--;
                if(indegree[neigh] == 0){
                    queue.offer(neigh);
                }
            }
        }
        return (count==A) ? 0 : 1;
    }

    public static void main(String[] args) {
        int [][] B = {
                {1,2},
                {2,3},
                {3,4},
                {4,5},
                {3,5},
                {3,2}

        };
        System.out.println(new CycleInGraphBFSKahn().solve(5, B));
    }
}
