package com.scaler.binarysearch;
/**
 * Problem Description
Given 2 integers A and B and an array of integers C of size N. Element C[i] represents the length of ith board.
You have to paint all N boards [C0, C1, C2, C3 … CN-1]. There are A painters available and each of them takes B units of time to 
paint 1 unit of the board.
Calculate and return the minimum time required to paint all boards under the constraints that any painter will only paint contiguous sections of the board.
NOTE:
1. 2 painters cannot share a board to paint. That is to say, a board cannot be painted partially by one painter, and partially by another.
2. A painter will only paint contiguous boards. This means a configuration where painter 1 paints boards 1 and 3 but not 2 is invalid.
Return the ans % 10000003.
Problem Constraints
1 <= A <= 1000
1 <= B <= 10^6
1 <= N <= 10^5
1 <= C[i] <= 10^6
 * @author mp1863
 *
 */
public class PaintersPartitionProblem {
	
	public static int paint(int A, int B, int[] C) {
		int l = Integer.MIN_VALUE;
		int m = 10000003;
		int sum = 0;
		for(int i = 0 ; i < C.length ; i++) {
			l = Math.max(l, C[i]);
			sum+=C[i];
		}
		//l = l*B;
		int h = sum;
		long ans = 0;
		while(l <= h) {
			int mid = l+(h-l)/2;
			if(checkIfAllThePaintersUtilized(C, A, B, mid)) {
				ans = (mid*(long)B)%m;
				h = mid - 1;
			}else {
				l = mid + 1;
			}
		}
		return (int)ans;
    }
	
	public static boolean checkIfAllThePaintersUtilized(int [] C, int A, int B, int mid) {
		int count = 1;
		int total_time = 0;
		for(int i = 0 ; i < C.length ; i++) {
			total_time+=(C[i]);
			if(total_time > mid) {
				count++;
				total_time = C[i];
			}
		}
		if(count > A)return false;
		return true;
		
	}
	
	public static void main(String[] args) {
		//int [] C = {1,8,11,3};
		int [] C = {4,3,2,7,5};
		//System.out.println(paint(2, 1, C));
		System.out.println(paint(3, 1, C));
	}

}
