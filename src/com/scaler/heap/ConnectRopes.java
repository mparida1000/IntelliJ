package com.scaler.heap;

import java.util.PriorityQueue;

public class ConnectRopes {
	
	public int solve(int[] A) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 0 ; i < A.length ; i++) {
			pq.add(A[i]);
		}
		int totalSum = 0 ;
		while(pq.size() > 1) {
			int sum = 0;
			int x = pq.poll();
			int y = pq.poll();
			sum=x+y;
			totalSum+=sum;
			pq.add(sum);
		}
		return totalSum;
    }
	
	public static void main(String[] args) {
		int [] A = {3,5,2,1};
		ConnectRopes cr = new ConnectRopes();
		System.out.println(cr.solve(A));
	}

}
