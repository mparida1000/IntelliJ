package com.scaler.dp;

import java.util.Arrays;

/*
 * Problem Description
Find out the number of A digit positive numbers, whose digits on being added equals to a given number B.

Note that a valid number starts from digits 1-9 except the number 0 itself. i.e. 
leading zeroes are not allowed.

Since the answer can be large, output answer modulo 1000000007
 */
public class NDigitNumbersSum {
	int [][] dp;
	int m = 1000000007;
	public int solve(int A, int B) {
		if(B == 1) return 1;
		dp = new int [A+1][B+1];
		for(int i = 0 ; i < A+1 ; i++)
			for(int j = 0 ; j < B+1 ; j++)
				dp[i][j] = -1;
		dp[1][0] = 0;
		for(int i = 1 ; i <= Math.min(9, B) ; i++) {
			dp[1][i] = 1;
		}
		for(int i = 10 ; i <= B ; i++) {
			dp[1][i] = 0;
		}
		return findWays(A, B);
    }
	
	int findWays(int n, int s) {
		if(dp[n][s] != -1)
			return dp[n][s];
		int ans = 0;
		for(int i = 0 ; i <= 9 ; i++) {
			if(i < s)
				ans = ans%m + findWays(n-1, s-i)%m;
		}
		dp[n][s] = ans%m;
		return dp[n][s]%m;
	}
	
	public static void main(String[] args) {
		System.out.println(new NDigitNumbersSum().solve(53,2));
	}
}
