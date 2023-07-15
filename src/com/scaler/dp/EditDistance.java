package com.scaler.dp;
/*
 * Problem Description
Given two strings A and B, find the minimum number of steps required to convert A to B. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character


Problem Constraints
1 <= length(A), length(B) <= 450
 */
import java.util.Arrays;

public class EditDistance {
	public int minDistance(String A, String B) {
		int n = A.length();
		int m = B.length();
		int [][] dp = new int[n+1][m+1];
		for(int i = 0 ; i <=n ; i++) {
			Arrays.fill(dp[i], -1);
		}
		return editDistance(A, B, n-1,m-1,dp);
    }
	private int editDistance(String A, String B, int i, int j, int[][] dp) {
		if(i == -1 || j == -1) {
			int l_A = i+1;
			int l_B = j+1;
			return Math.max(l_A, l_B);
		}
		if(dp[i][j] != -1)
			return dp[i][j];
		if(A.charAt(i) == B.charAt(j))
			dp[i][j] = editDistance(A, B, i-1, j-1, dp);
		else
			dp[i][j] = Math.min(Math.min(editDistance(A, B, i-1, j-1, dp), editDistance(A, B, i, j-1, dp)),editDistance(A, B, i-1, j, dp))+1;
		return dp[i][j];
	}
	
	public static void main(String[] args) {
		System.out.println(new EditDistance().minDistance("ABCAB", "EACB"));
	}

}
