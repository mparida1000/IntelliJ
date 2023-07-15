package com.scaler.array;
/*
Problem Description
Given a vector A of non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
Problem Constraints
1 <= |A| <= 100000
 */
public class RainWaterTrapped {
	
	public static int trap(final int[] A) {
		int n = A.length;
		int [] lArr = new int[n];
		int [] rArr = new int[n];
		int leftmax = A[0];
		int rightMax = A[n-1];
		for(int i = 0 ; i < n ; i++) {
			leftmax = Math.max(leftmax, A[i]);
			lArr[i] = leftmax;
		}
		for(int i = n-1 ; i >= 0 ; i--) {
			rightMax = Math.max(rightMax, A[i]);
			rArr[i] = rightMax;
		}
		int total = 0;
		for(int i = 0 ; i < n ; i++) {
			total+=Math.abs(A[i]-Math.min(rArr[i], lArr[i]));
		}
		return total;
    }
	
	public static void main(String[] args) {
		int [] A = {0,1,0,2};
		System.out.println(trap(A));
	}

}
