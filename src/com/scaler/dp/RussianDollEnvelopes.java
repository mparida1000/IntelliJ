package com.scaler.dp;

import java.util.Arrays;
import java.util.Comparator;

/*
 * Problem Description
Given a matrix of integers A of size N x 2 describing dimensions of N envelopes, 
where A[i][0] denotes the height of the ith envelope and A[i][1] denotes the width of the 
ith envelope.

One envelope can fit into another if and only if both the width and height of one envelope is 
greater than the width and height of the other envelope.

Find the maximum number of envelopes you can put one inside other.
 */
public class RussianDollEnvelopes {

	public int solve(int[][] A) {
		int n = A.length;
		Arrays.sort(A, new Comparator<int[]>() {
			@Override
			public int compare(int [] arr1, int [] arr2) {
				if(arr1[0] == arr2[0])
	                return arr2[1] - arr1[1];
	            else
	                return arr1[0] - arr2[0]; 
			}
		});
		printArr(A);
		int[][] dp = new int [n][n];
		int[] arr = new int[n];
		for(int i = 0 ; i < n ; i++)
			arr[i] = A[i][1];
		return lis(arr, 0,-1,dp);
    }
	int lis(int [] arr, int curr, int prev, int [][] dp) {
		if(curr == arr.length)
			return 0;
		if(prev != -1 && dp[prev][curr] != 0)
			return dp[prev][curr];
		int include = 0;			
		if(prev == -1 || arr[prev] < arr[curr]) {
			include = 1+lis(arr,curr+1,curr,dp);
		}
		int exclude = lis(arr,curr+1,prev,dp);
		if(prev != -1) {
			dp[prev][curr] =  Math.max(include, exclude);
		}			
		return Math.max(include, exclude);
	}
	public static void main(String[] args) {
		int [][] A = {
				{2,3},
				{2,4},
				{4,6},
				{4,7},
				{5,2}
		};
		System.out.println(new RussianDollEnvelopes().solve(A));
	}
	void printArr(int [][] arr) {
		for(int i = 0 ; i < arr.length ; i++) {
			for(int j = 0 ; j < arr[0].length ; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
