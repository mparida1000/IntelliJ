package com.scaler.heap;

import java.util.PriorityQueue;

public class MaximumArraySumAfterBNegations {

	public int solve(int[] A, int B) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 0 ; i < A.length ; i++) {
			pq.offer(A[i]);
		}
		for(int i = 0 ; i < B ; i++) {
			int num = pq.poll();
			pq.offer(num*-1);
		}
		int sum = 0;
		System.out.println(pq.size());
		while(!pq.isEmpty()) {
			sum+=pq.poll();
		}
		return sum;
    }
	
	public static void main(String[] args) {
		int [] A = {-38, 65, 30, 19, 75, -80, -30, 94, -9, 93, 51, 49, 18, -99, -99, 7, -14};
		MaximumArraySumAfterBNegations ma = new MaximumArraySumAfterBNegations();
		int op = ma.solve(A, 4097);
		System.out.println(op);
		
	}
}
