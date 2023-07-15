package com.scaler.dp;

import java.util.Arrays;

public class MinimumNumberSquares {
	int [] dp;
	public int countMinSquares(int A) {
		dp = new int[A+1];
		Arrays.fill(dp, -1);		
		dp[0] = 0;		
		return minSquare(A);
    }
	
	int minSquare(int n) {
		if(dp[n] != -1)
			return dp[n];
		int min = Integer.MAX_VALUE;
		for(int i = 1; i*i <= n ; i++) {
			min = Math.min(min, minSquare(n - i*i));
		}
		dp[n] = min+1;
		return dp[n];
	}
	
	public static void main(String[] args) {
		System.out.println(new MinimumNumberSquares().countMinSquares(11));
	}
}
