package com.scaler.dp;
/*
 * Problem Description
Given an integer A, how many structurally unique BST's 
(binary search trees) exist that can store values 1...A?
 */
public class UniqueBinarySearchTreesII {
	int [] dp;
	public int numTrees(int A) {
		dp = new int[A+1];
		dp[0] = 1;
		return uniqueBT(A);
    }
	
	int uniqueBT(int n) {
		if(n <= 1)
			return 1;
		if(dp[n] > 0)
			return dp[n];
		for(int i = 1; i <= n ; i++) {
			dp[n]+= uniqueBT(i - 1) * uniqueBT(n - i);
		}
		return dp[n];
	}
	
	public static void main(String[] args) {
		System.out.println(new UniqueBinarySearchTreesII().numTrees(5));
	}
}
