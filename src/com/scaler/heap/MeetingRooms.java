package com.scaler.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRooms {
	
	static int [][] arr = { {10, 20}, {11, 30}, {1, 10}, {3, 19}, {8, 12}, {1, 10}, {2, 10}, {9, 11}, {10, 11},{10,11},{10,11}};
	public static void main(String[] args) {
		System.out.println(minMeetingRooms(arr));
	}

	public static int minMeetingRooms(int[][] intervals ) {
		if(intervals.length == 0) {
			return 0;
		}
		//Create MinHeap which will contain the end time of each slot
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
				/*intervals.length, new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				return a-b;
			}
		});*/
		
		//Sort Arrays by start time of all the slots
		Arrays.sort(intervals, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				return a[0] - b [0];
			}
		});
		
		//iterate the array, compare start time of array with top node,
		//if the start time of the slot is greater than top node in the queue , poll it
		//add the end time to the queue
		pq.add(arr[0][1]);
		for(int i = 1 ; i < arr.length ; i++) {
			if(arr[i][0] >= pq.peek()) {
				pq.poll();
			}
			pq.add(arr[i][1]);
		}
		
		return pq.size();
		
	}
}
