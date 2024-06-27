package com.scaler.map;

import java.util.HashMap;
import java.util.Map;
/*
Problem Description
You are given an array A of N integers and an integer B. Count the number of pairs (i,j) such that A[i] + A[j] = B and i ≠ j.

Since the answer can be very large, return the remainder after dividing the count with 109+7.

Note - The pair (i,j) is same as the pair (j,i) and we need to count it only once.
Problem Constraints
1 <= N <= 105
1 <= A[i] <= 109
1 <= B <= 109


Input Format
First argument A is an array of integers and second argument B is an integer.

 */
public class CountPairSum {
	public static int solve(int[] A, int B) {
		Map<Integer, Integer> m = new HashMap<>();
		int n = A.length;
		long count = 0;
		for(int i = 0 ; i < n ; i++) {
			if(!m.containsKey(A[i])) {
				m.put(A[i], 1);
			}else {
				m.put(A[i], m.get(A[i])+1);
			}
		}
		for(int i = 0 ; i < n ; i++) {
			int a = A[i];
			int b = B - a;
			if(a == b && m.get(b) > 1) {
				count++;
			}else if(m.containsKey(b)) {
				count++;
			}
		}
		return (int)count%1000000007;
    }
	
	public static void main(String[] args) {
		int [] A = {1,6,2,5,4,3,1,4};
		System.out.println(solve(A, 6));
	}

}
