package com.scaler.dp;
/**
 * Problem Description

Given a 2 x N grid of integer, A, choose numbers such that the sum of the numbers is maximum and no two chosen numbers are adjacent horizontally, 
vertically or diagonally, and return it.

Note: You can choose more than 2 numbers.
 * @author mp1863
 *
 */
public class MaxSumWithoutAdjacentElements {
	int dp [];
	public int adjacent(int[][] A) {
		int n = A[0].length;
		if(n == 1)
			return Math.max(A[0][0], A[1][0]);
		dp = new int[n];
		dp[0] = Math.max(A[0][0], A[1][0]);
		dp[1] = Math.max(dp[0], Math.max(A[0][1], A[1][1]));
		if(n == 2)return dp[1];
		int ans = Integer.MIN_VALUE;
		for(int i = 2; i < n ; i++) {
			dp[i] = Math.max(A[0][i],A[1][i]) + Math.max(dp[i-2], ans);
			ans = Math.max(dp[i-1], ans);
		}
		return Math.max(ans, dp[n-1]);
    }
	
	public static void main(String[] args) {
		int [][] A = {
				{2, 6, 6, 1, 16, 6, 15 },
				{9, 16, 5, 4, 20, 3, 3 }
		};
		System.out.println(new MaxSumWithoutAdjacentElements().adjacent(A));
	}
}
