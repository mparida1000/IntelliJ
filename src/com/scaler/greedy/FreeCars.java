package com.scaler.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * Problem Description
Given two arrays, A and B of size N. A[i] represents the time by which you can buy the ith car without paying any money.
B[i] represents the profit you can earn by buying the ith car. It takes 1 minute to buy a car, so you can only buy 
the ith car when the current time <= A[i] - 1.
Your task is to find the maximum profit one can earn by buying cars considering that you can only buy one car at a time.
NOTE:
You can start buying from time = 0.
Return your answer modulo 109 + 7.
 */
public class FreeCars {

	public int solve(int[] A, int[] B) {
		int n = A.length;
		int [][] cars = new int [n][2];
		for(int i = 0 ; i < n ; i++) {
			cars[i][0] = A[i];
			cars[i][1] = B[i];
		}
		Arrays.sort(cars, new Comparator<int[]>() {
			public int compare(int [] a, int [] b) {
				return a[0] - b[0];
			}
		});
		int m = 1000000007;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		long sum = 0;
		int t = 0;
		for(int i = 0 ; i < n ; i++) {
			if(t < cars[i][0]) {
				sum+=cars[i][1]%m;
				pq.add(cars[i][1]);
			}else if (t == cars[i][0]) {
				t--;
				if(pq.peek() < cars[i][1]) {
					sum-=pq.poll()%m;
					pq.add(cars[i][1]);
					sum+=cars[i][1]%m;
				}
			}
			t++;
		}
		return (int)(sum%m);
    
    }
	public static void main(String[] args) {
		int [] A = {7,5,9,1,7,9,9,4,10,4,7,4};//{5,5,3,3,1,5,3,5,5};
		int [] B = {46,15,28,36,29,18,9,50,13,16,21,40};//{4,8,7,1,5,3,2,6,9};
		FreeCars fc = new FreeCars();
		System.err.println(fc.solve(A, B));
	}
}
