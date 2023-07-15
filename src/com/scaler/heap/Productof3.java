package com.scaler.heap;

import java.util.Collections;
import java.util.PriorityQueue;

/*
 * Given an integer array A of size N.
You have to find the product of the three largest integers in array A from range 1 to i, where i goes from 1 to N.
Return an array B where B[i] is the product of the largest 3 integers in 
range 1 to i in array A. If i < 3, then the integer at index i in B should be -1.
 */
public class Productof3 {
	public int[] solve(int[] A) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(3);
		int n = A.length;
		if(n == 1) return new int []{-1};
		if(n==2) return new int []{-1,-1};
		int op [] = new int[n];
		int prod = 0;
		op[0] = -1; op[1] = -1;
		pq.offer(A[0]);
		pq.offer(A[1]);
		pq.offer(A[2]);
		prod = A[0]*A[1]*A[2];
		op[2] = prod;
		for (int i = 3 ; i < n ; i++) {
			if(A[i] > pq.peek()) {
				int num = pq.poll();
				prod/=num;
				prod*=A[i];
				pq.offer(A[i]);
				op[i] = prod;
			}else {
				op[i] = prod;
			}
		}
		return op;		
    }
	
	public static void main(String[] args) {
		int [] A = {5,9,15,8,4,10,14,5,4,9,6};
		Productof3 pr3 = new Productof3();
		int [] op = pr3.solve(A);
		for(int i = 0 ; i < op.length ; i++) {
			System.out.print(op[i] + " ");
		}
	}
}
