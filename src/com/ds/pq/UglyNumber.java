package com.ds.pq;

import java.util.PriorityQueue;

public class UglyNumber {//Get nth Ugly Number
	
	public static void main(String[] args) {
		System.out.println(getNthUglyNumber(7));
	}
	
	public static int getNthUglyNumber(int n) {
		
		PriorityQueue<Long> heap = new PriorityQueue<Long>();
		heap.add(1l);
		for(int i = 1 ; i < n ; i++){
			long root = heap.poll();
			while(!heap.isEmpty() && root == heap.peek()) heap.poll();
			heap.add(root*2);
			heap.add(root*3);
			heap.add(root*5);
		}
		return heap.poll().intValue();
	}

}
