package com.scaler.heap;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MedianOfStream {
	
	static PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
	static PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MedianOfStream ms = new MedianOfStream();
		StringBuffer input = new StringBuffer();
		for(int i = 1 ; i <=10 ; i++) {
			int data = sc.nextInt();
			input.append(data + " ");
			insert(data);
			System.out.println("Median is .."+findMedian());
		}
		System.out.println("Input Stream..."+input.toString());
		System.out.println(maxHeap.toString());
		System.out.println(minHeap.toString());

	}
	
	public static void insert(int num) {
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
	
	public static double findMedian() {
		return (maxHeap.peek()!=null?maxHeap.peek():0)/2.0+(minHeap.peek()!=null?minHeap.peek():0)/2.0;
	}

}
