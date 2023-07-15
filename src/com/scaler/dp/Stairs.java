package com.scaler.dp;

import java.util.Arrays;

/*
 * Problem Description
You are climbing a staircase and it takes A steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Return the number of distinct ways modulo 1000000007
 */
public class Stairs {
	int dp [];
	int m = 1000000007;
	public int climbStairs(int A) {
		if(A == 1)return 1;
		dp = new int[A+1];
		dp[1] = 1;
		dp[2] = 2;
		int r =  steps(A)%m;
		return r;
    }
	
	int steps(int n) {
		for(int i = 3 ; i <= n ; i++) {
			dp[i] = dp[i-1]%m + dp[i-2]%m;
		}
		return dp[n];
	}
	
	public static void main(String[] args) {
		System.out.println(new Stairs().climbStairs(46312));
	}
}
