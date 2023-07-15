package com.scaler.dp;
/*
 * Problem Description

Given an array A of positive elements, you have to flip the sign of some of its elements such that the resultant sum of the elements of 
array should be minimum non-negative(as close to zero as possible).

Return the minimum number of elements whose sign needs to be flipped such that the resultant sum is minimum non-negative.
 */
public class FlipArray {
	Integer [][] dp;
	public int solve(final int[] A) {
		int sum = 0;
		int n = A.length;
		for(int i = 0 ; i < n ; i ++) {
			sum+=A[i];
		}
		int target = sum/2;
		dp = new Integer[n+1][target+1];
		return flip(n,target, A);
		
    }
	
	int flip(int i, int j, int [] arr){
		if(j == 0) {
			return 0;
		}
		if(i == 0 || j < 0) {
			return Integer.MAX_VALUE;
		}
		if(dp[i][j] != null) {
			return dp[i][j];
		}
		
		int flips = flip(i-1, j, arr);
        int remainingTarget = j - arr[i-1];
        int remainingFlips = flip(i-1, remainingTarget, arr);
        if(remainingFlips != Integer.MAX_VALUE){
            flips = Math.min(flips, 1+remainingFlips);
        }
		dp[i][j] = flips;
		return flips;
	}
	
	public static void main(String[] args) {
		int [] A = {14,10,10};
		System.out.println(new FlipArray().solve(A));
	}
}
