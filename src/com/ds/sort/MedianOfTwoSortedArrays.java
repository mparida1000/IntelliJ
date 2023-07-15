package com.ds.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class MedianOfTwoSortedArrays {
	
	static PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
	static PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
	
	public static void main(String[] args) {
		int [] arr1 = {1,2,3};
		int [] arr2 = {4,5,6};
		insert(arr1, arr2);
		System.out.println (median());
	}
	
	public static void insert(int [] arr1, int [] arr2) {
		int[] mergedArray = new int[arr1.length+arr2.length];
		System.arraycopy(arr1, 0, mergedArray, 0, arr1.length);
		System.arraycopy(arr2, 0, mergedArray, arr1.length, arr2.length);
		for(int num : mergedArray) {
			if(maxHeap.isEmpty() || maxHeap.peek() >= num) {
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
	}
	
	public static double median() {
		return (maxHeap.peek()!=null?maxHeap.peek():0)/2.0+(minHeap.peek()!=null?minHeap.peek():0)/2.0;
	}
}
