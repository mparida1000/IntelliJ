package com.scaler.dp;

import java.util.Arrays;

/*
 * Problem Description
Given two integer arrays A and B of size N each which represent values and weights associated with N items respectively.

Also given an integer C which represents knapsack capacity.

Find out the maximum value subset of A such that sum of the weights of this subset is smaller than or equal to C.

NOTE:

You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).
 */
public class ZeroOneKnapsack {
	int [][] dp;
	public int solve(int[] A, int[] B, int C) {
		int n = A.length;
		dp = new int[n+1][C+1];
		for(int i = 0 ; i < n+1 ; i++) {
			Arrays.fill(dp[i], -1);
		}
		return knapsack(n, C, A, B);		
    }
	int knapsack(int i, int j, int [] v, int [] w) {
		if(i == 0 || j == 0)
			return 0;
		if(dp[i][j] != -1)
			return dp[i][j];
		if(j >= w[i-1]) {
			dp[i][j] = Math.max(knapsack(i-1, j, v, w), knapsack(i-1, j-w[i-1], v, w)+v[i-1]);
		}else {
			dp[i][j] = knapsack(i-1, j, v, w);
		}
		return dp[i][j];
	}
	
	public static void main(String[] args) {
		int [] A = {4,1,5,7};
		int [] B = {3,2,4,5};
		System.out.println(new ZeroOneKnapsack().solve(A, B, 7));
	}
}
