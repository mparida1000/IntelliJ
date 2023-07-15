package com.scaler.dp;

import java.util.Arrays;

public class LongestCommonSubsequence {
	int[][] dp;
	public int solve(String A, String B) {
		int n = A.length();
		int m = B.length();
		dp = new int [n+1][m+1];
		for(int i = 0 ; i <=n ; i++) {
			Arrays.fill(dp[i], -1);
		}
		int res = lcs(A, B, n-1, m-1, dp);
		return res;
    }
	
	int lcs(String A, String B, int i, int j, int [][] dp) {
		if(i == -1 || j == -1)
			return 0;
		if(dp[i][j] != -1)
			return dp[i][j];
		if(A.charAt(i) == B.charAt(j))
			dp[i][j] = lcs(A, B, i-1, j-1, dp) + 1;
		else
			dp[i][j] = Math.max( lcs(A, B, i-1, j, dp), lcs(A, B, i, j-1, dp));
		return dp[i][j];
	}
	
	public static void main(String[] args) {
		System.out.println(new LongestCommonSubsequence().solve("abc", "ac"));
	}
}
