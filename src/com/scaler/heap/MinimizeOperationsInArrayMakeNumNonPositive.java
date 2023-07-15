package com.scaler.heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class MinimizeOperationsInArrayMakeNumNonPositive {
	static int [] arr = {6,4,9,2};
	public static void main(String[] args) {
		
		System.out.println(minimizeOperations(21));
		
		
	}
	
	 public static int minimizeOperations(int x) {
		 PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		 int count = 0;
		 for(int i = 0 ; i < arr.length ; i++) {
			 pq.add(arr[i]);
		 }
		 while(!pq.isEmpty() && x > 0) {
			 if(pq.peek() == 0)
				 break;
			 count++;
			 int num = pq.peek();
			 pq.poll();
			 x-=num;
			 num/=2;
			 if(num > 0) {
				 pq.add(num);
			 }
		 }
		 if(x > 0) return -1;
		 
		 return count;
	 }

}
