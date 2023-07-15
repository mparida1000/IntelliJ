package com.scaler.heap;

import java.util.PriorityQueue;

public class KPlacesApart {
	public int[] solve(int[] A, int B) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int n = A.length;
		for(int i = 0 ; i <= B ; i++) {
			pq.add(A[i]);
		}
		int k = 0;
		for(int i = B+1 ; i < n ; i ++) {
			int num = pq.poll();
			A[k] = num;
			pq.offer(A[i]);
			k++;
		}
		while(!pq.isEmpty()){
			int num = pq.poll();
			A[k] = num;
			k++;
		}
		return A;
    }
	
	public static void main(String[] args) {
		int []  A = {6,5,3,2,8,10,9};
		KPlacesApart kp = new KPlacesApart();
		int [] sorted = kp.solve(A, 3);
		for(int  i : sorted) {
			System.out.print(i + " ");
		}
	}
}
