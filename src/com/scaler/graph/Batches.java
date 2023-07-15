package com.scaler.graph;

import java.util.Arrays;

/*
 * Problem Description

A students applied for admission in IB Academy. An array of integers B is given representing the strengths of A people i.e. B[i] 
represents the strength of ith student.
Among the A students some of them knew each other. A matrix C of size M x 2 is given which represents relations where ith relations 
depicts that C[i][0] and C[i][1] knew each other.
All students who know each other are placed in one batch.
Strength of a batch is equal to sum of the strength of all the students in it.
Now the number of batches are formed are very much, it is impossible for IB to handle them. So IB set criteria for selection: 
All those batches having strength at least D are selected.
Find the number of batches selected.
NOTE: If student x and student y know each other, student y and z know each other then student x and student z will also know each other.

Problem Constraints
1 <= A <= 105
1 <= M <= 2*105
1 <= B[i] <= 104
1 <= C[i][0], C[i][1] <= A
1 <= D <= 109
 */
public class Batches {

	static class UnionFind {
        private int[] parent;
        private int[] rank;
        private int[] strength;
        private int[] batchStrength;

        public UnionFind(int n, int[] strength) {
            parent = new int[n];
            rank = new int[n];
            this.strength = strength;
            batchStrength = new int[n];
            Arrays.fill(batchStrength, -1);

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                    batchStrength[rootY] += batchStrength[rootX];
                } else if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                    batchStrength[rootX] += batchStrength[rootY];
                } else {
                    parent[rootY] = rootX;
                    batchStrength[rootX] += batchStrength[rootY];
                    rank[rootX]++;
                }
            }
        }

        public void setBatchStrength(int x) {
            int root = find(x);
            batchStrength[root] += strength[x];
        }

        public int getBatchStrength(int x) {
            int root = find(x);
            return batchStrength[root];
        }
    }

    public static int countBatches(int A, int[][] C, int[] B, int D) {
        UnionFind uf = new UnionFind(A, B);

        for (int i = 0; i < C.length; i++) {
            int x = C[i][0] - 1;  // Adjusting index to 0-based
            int y = C[i][1] - 1;  // Adjusting index to 0-based
            uf.union(x, y);
        }

        int count = 0;
        for (int i = 0; i < A; i++) {
            if (uf.getBatchStrength(i) >= D)
                count++;
        }

        return count;
    }
    
    public static void main(String[] args) {
        int A = 7;
        int[][] C = {{1, 2}, {2, 3}, {4, 5}, {5, 6}};
        int[] B = {10, 20, 30, 40, 50, 60, 70};
        int D = 100;

        int batches = countBatches(A, C, B, D);
        System.out.println("Number of batches selected: " + batches);
    }
}
