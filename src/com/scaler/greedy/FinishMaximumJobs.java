package com.scaler.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * Problem Description
There are N jobs to be done, but you can do only one job at a time.
Given an array A denoting the start time of the jobs and an array B denoting the finish time of the jobs.
Your aim is to select jobs in such a way so that you can finish the maximum number of jobs.
Return the maximum number of jobs you can finish.
 */
public class FinishMaximumJobs {
	public int solve(int[] A, int[] B) {
		int n = A.length;
		int [][]intervals = new int[n][2];
		for(int i = 0 ; i < n ; i++) {
			intervals[i][0] = A[i];
			intervals[i][1] = B[i];
		}
		Arrays.sort(intervals, new Comparator<int[]>() {
			public int compare(int a[], int b[]) {
				return a[1] - b[1];
			}
		});
		//[[3, 4], [6, 8], [5, 8], [1, 9], [8, 9], [7, 10]]
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		int count = 1;
		for(int i = 0 ; i < n ; i++) {
			if(!pq.isEmpty()) {
				if(intervals[i][0] >= pq.peek()) {
					count++;
					pq.add(intervals[i][1]);
				}
			}else
				pq.add(intervals[i][1]);
		}
		return count;
    }
	
	public static void main(String[] args) {
		int [] A = {1,6,5,8,7,3,9};
		int [] B = {9,8,8,9,10,4,10};
		FinishMaximumJobs fmj = new FinishMaximumJobs();
		System.out.println(fmj.solve(A, B));
	}
}
