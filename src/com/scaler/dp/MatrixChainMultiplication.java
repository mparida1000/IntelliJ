package com.scaler.dp;
/*
 * Given an array of integers A representing chain of 2-D matices such that the dimensions of ith matrix is A[i-1] x A[i].

Find the most efficient way to multiply these matrices together. The problem is not actually to 
perform the multiplications, but merely to decide in which order to perform the multiplications.

Return the minimum number of multiplications needed to multiply the chain.
 */
public class MatrixChainMultiplication {

	public int solve(int[] A) {
		int n = A.length;
		int [][] dp = new int[n][n];
		for(int len = 2; len < n ; len++) {
			for(int row = 0, col = len ; row < n-len ; row++,col++){
				dp[row][col] = Integer.MAX_VALUE;
				for(int k = row+1 ; k < col ; k++) {
					dp[row][col]  = Math.min(dp[row][col], 
							dp[row][k] + dp[k][col] + 
							A[row] * A[col] * A[k]);
				}
			}			
		}
		return dp[0][n-1];
    }
	
	public static void main(String[] args) {
		int [] A = {2,4,3,2,1};
		System.out.println(new MatrixChainMultiplication().solve(A));
	}
}
