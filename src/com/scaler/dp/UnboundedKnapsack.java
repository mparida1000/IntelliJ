package com.scaler.dp;

import java.util.Arrays;

/*
 * Problem Description
Given a knapsack weight A and a set of items with certain value B[i] and weight C[i], 
we need to calculate maximum amount that could fit in this quantity.

This is different from classical Knapsack problem, here we are allowed to use unlimited number of instances of an item.
 */
public class UnboundedKnapsack {
	int [][] dp;
	public int solve(int A, int[] B, int[] C) {
		int n = B.length;
		dp = new int[n+1][A+1];
		for(int i = 0 ; i < n+1 ; i++) {
			Arrays.fill(dp[i], -1);
		}
		return knapsackUnbounded(n, A, B, C);
    }
	int knapsackUnbounded(int i, int j, int [] v, int [] w) {
		if(i == 0 || j == 0)
			return 0;
		if(dp[i][j] != -1)
			return dp[i][j];
		if(j >= w[i-1]) {
			dp[i][j] = Math.max(knapsackUnbounded(i-1, j, v, w), knapsackUnbounded(i, j-w[i-1], v, w)+v[i-1]);
		}else {
			dp[i][j] = knapsackUnbounded(i-1, j, v, w);
		}
		return dp[i][j];
	}
	
	public static void main(String[] args) {
		int [] B = {4,1,5,7};
		int [] C = {3,2,4,5};
		System.out.println(new UnboundedKnapsack().solve(7, B, C));
	}
}
