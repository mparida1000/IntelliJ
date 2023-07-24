package com.scaler.graph;
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
        return 0;
    }

    public static void main(String[] args) {
        int [][] A = {
                {1,2,2},
                {2,4,1},
                {1,3,3},
                {4,3,2}
        };
        int [] B ={2,3};
        int C = 4;
        System.out.println(new ContestFaultyNodes().solve(A,B,C));
    }
}
