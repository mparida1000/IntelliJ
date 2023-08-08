package com.scaler.array;
/*
Problem Description
Given a row-wise and column-wise sorted matrix A of size N * M.
Return the maximum non-empty submatrix sum of this matrix.


Problem Constraints
1 <= N, M <= 1000
-109 <= A[i][j] <= 109
 */
public class MaximumSubmatrixSum {
	
	public static long solve(int[][] A) {
		int n = A.length;
		int m = A[0].length;
		long max = Long.MIN_VALUE;
		for(int s = 0 ; s < n ; s++) {
			long [] sum = new long[m];
			for(int i = s ; i < n ; i++) {
				for(int j = 0 ; j < m ;j++) {
					sum[j] = sum[j]+A[i][j];
				}
				long currMax = getKadanes(sum);
				max = Math.max(max, currMax);
			}
		}
		return max;
    }
	
	public static long getKadanes(long [] arr) {
		long currSum = 0 ;
		long maxSum = Long.MIN_VALUE;
		for(int i = 0 ; i < arr.length ; i++) {
			currSum+=arr[i];
			if(currSum > maxSum) {
				maxSum = currSum;
			}
			if(currSum < 0) {
				currSum = 0;
			}
		}
		return maxSum;
	}
	
	public static void main(String[] args) {
		int [][] A = {
				{1,2,-1,-4,-20},
				{-8,-3,4,-2,1},
				{3,8,9,1,3},
				{-4,-1,1,7,-6},
				{-2,-3,8,1,-1}
		};
		System.out.println(solve(A));
	}

}
