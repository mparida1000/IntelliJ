package com.ds.pq;

import java.util.PriorityQueue;//Java PQ works on Min Heap ==> Complete Binary Tree with with Min heap

public class KthMaxinArray {
	static int[] arr = {40, 10, 30, 60, 70, 20, 50, 80, 90};
	
	public static void main(String[] args) {
		System.out.println(kthMaxElement(3));
	}
	static int kthMaxElement(int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i = 0 ; i < k ; i++) {
			pq.add(arr[i]);
		}
		for(int i = k ; i < arr.length ; i++) {
			if(pq.peek() < arr[i]) {
				pq.poll();
				pq.add(arr[i]);
			}
		}
		return pq.peek();
	}

}
