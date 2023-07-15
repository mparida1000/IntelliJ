package com.scaler.array;

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
		int [][] A = {{-57668}};
		System.out.println(solve(A));
	}

}
