package com.scaler.heap;

import java.util.PriorityQueue;

public class KthSmallestElementinaSortedMatrix {

	public int solve(int[][] A, int B) {
        int n = A.length;
        int m = A[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0 ; i < n ; i++) {
        	for(int j = 0 ; j < m ; j++) {
        		pq.offer(A[i][j]);
        	}
        }
        for(int i = 0 ; i < B - 1 ; i++) {
        	pq.poll();
        }
        return pq.peek();
    }
	
	public static void main(String[] args) {
		int [][] A = {
				{5, 9, 11},
		        {9, 11, 13},
		        {10, 12, 15},
		        {13, 14, 16},
		        {16, 20, 21}
		};
		KthSmallestElementinaSortedMatrix k = new KthSmallestElementinaSortedMatrix();
		System.out.println(k.solve(A, 12));
		System.out.println(Math.floorDiv(6, 2));
		
	}
}
