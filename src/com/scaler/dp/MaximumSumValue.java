package com.scaler.dp;
/**
 * Problem Description

You are given an array A of N integers and three integers B, C, and D.

You have to find the maximum value of A[i]*B + A[j]*C + A[k]*D, where 1 <= i <= j <= k <= N.
 * @author mp1863
 *
 */
public class MaximumSumValue {
	int [][]dp;
	public int solve(int[] A, int B, int C, int D) {
		int n = A.length;
		dp = new int[3][n];
		dp[0][0] = A[0] * B;
		dp[1][0] = dp[0][0] + A[0] * C;
		dp[2][0] = dp[1][0] + A[0] * D;
		return maximumSum(A, n, B, C, D);
    }
	
	int maximumSum(int [] arr, int n, int B, int C, int D) {
		for(int i = 1 ; i < n ; i++) {
			dp[0][i] = Math.max(dp[0][i-1], arr[i] * B);
			dp[1][i] = Math.max(dp[0][i]+arr[i]*C, dp[1][i-1]);
			dp[2][i] = Math.max(dp[1][i]+arr[i]*D, dp[2][i-1]);
		}
		return dp[2][n-1];
	}
	
	public static void main(String[] args) {
		int [] A = {1, 5, -3, 4, -2}; 
		System.out.println(new MaximumSumValue().solve(A, 2, 1, -1));
	}
}
