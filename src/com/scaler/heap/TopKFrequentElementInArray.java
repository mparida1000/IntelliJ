package com.scaler.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class TopKFrequentElementInArray {
	
	static int[] arr = {1,2,3,1,1,4,5,6,4,4,1,3,5,6,4};
	public static void main(String[] args) {
		int[] farr = topKFrequentElements(3);
		for(int a : farr) {
			System.out.println(a);
		}
		
	}
	public static int[] topKFrequentElements(int k) {
		Map<Integer, Integer> numMap = new HashMap<Integer, Integer>();
		for(int n : arr) {
			numMap.put(n, numMap.getOrDefault(n, 0)+1);
		}
		
		Queue<Integer> pq = new PriorityQueue<>(
				(n1,n2) -> numMap.get(n1) - numMap.get(n2));
		
		for(int n : numMap.keySet()) {
			pq.add(n);
			if(pq.size() > k) pq.poll();
		}
		int[] farr = new int[k];
		for(int i = k-1 ; i >=0 ; i--) {
			farr[i] = pq.poll();
		}
		return farr;
	}

}
