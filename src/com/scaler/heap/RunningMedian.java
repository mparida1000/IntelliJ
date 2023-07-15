package com.scaler.heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class RunningMedian {

	public int[] solve(int[] A) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		int n = A.length;
		int [] op = new int[n];
		for(int i = 0 ; i < n ;i ++) {
			insert(A[i], minHeap, maxHeap);
			op[i] = findMedian(minHeap, maxHeap);
		}
		return op;
    }
	
	private void insert(int num, PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
		if(maxHeap.isEmpty() || maxHeap.peek() > num) {
			maxHeap.add(num);
		}else {
			minHeap.add(num);
		}
		if(maxHeap.size() > minHeap.size() + 1){
			minHeap.add(maxHeap.poll());
		}else if(maxHeap.size() < minHeap.size()){
			maxHeap.add(minHeap.poll());
		}
	}
	
	private int findMedian(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
		
		return maxHeap.peek();
	}
	
	
	
	public static void main(String[] args) {
		int [] A = {6,5,3,2,8,10,9};
		RunningMedian rm = new RunningMedian();
		int [] op = rm.solve(A);
		for(int i : op) {
			System.out.print(i + " ");
		}
	}
}
