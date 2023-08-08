 package com.scaler.dp;
/*
https://drive.google.com/file/d/1aBdVc6-LHxtWFmH0PqSc24gGUSV4cIQx/view?usp=drive_link
 */
public class LongestIncreasingSubsequence {

	
	int lis(int [] A) {//Top-Down approach
		int n = A.length;
		int [][] dp = new int[n][n];
		return lis(-1, 0, A, dp);
	}
	int lis(int prev, int curr, int [] arr, int [][] dp) {
		if(curr == arr.length)return 0;
		if(prev != -1 && dp[prev][curr] != 0) {
			return dp[prev][curr];
		}
		int include = 0;
		if(prev == -1 || arr[curr] > arr[prev]) {
			include = 1 + lis (curr, curr+1, arr, dp);
		}
		int exclude = lis(prev, curr+1, arr, dp);
		if(prev != -1) {
			dp[prev][curr] = Math.max(include, exclude);
		}
		return Math.max(include, exclude);
	}
	
	public static void main(String[] args) {
		int [] A = {3, 2, 4, 5, 1, 6};
		System.out.println(new LongestIncreasingSubsequence().lis(A));
	}
}
