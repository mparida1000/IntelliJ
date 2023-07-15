package com.scaler.heap;

import java.util.PriorityQueue;

/*
 * Problem Description
Given an integer array B of size N.

You need to find the Ath largest element in the subarray [1 to i], where i varies from 1 to N. 
In other words, find the Ath largest element in the sub-arrays [1 : 1], [1 : 2], [1 : 3], ...., [1 : N].

NOTE: If any subarray [1 : i] has less than A elements, then the output should be -1 at the ith index.

Problem Constraints
1 <= N <= 100000
1 <= A <= N
1 <= B[i] <= INT_MAX
 */
public class Athlargestelement {

	public int[] solve(int A, int[] B) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(A);
		int n = B.length;
		int [] op = new int[B.length];
		for(int i = 0 ; i < A; i++) {
			if(i < (A - 1))op[i] = -1;
			pq.add(B[i]);
		}
		op[A-1] = pq.peek();
		for(int i = A ; i < n ; i ++) {
			if(B[i] > pq.peek()) {
				pq.poll();
				pq.offer(B[i]);
				op[i] = pq.peek();
			}else {
				op[i] = pq.peek();
			}
		}
		return op;
    }
	
	public static void main(String[] args) {
		int [] A = {15, 20, 99, 1, 44, 25};
		Athlargestelement ath = new Athlargestelement();
		int [] op = ath.solve(3, A);
		for(int i : op) {
			System.out.print(i + " ");
		}
	}
}
