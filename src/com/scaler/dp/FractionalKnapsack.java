package com.scaler.dp;

import java.util.Arrays;
import java.util.Collections;

/*
 * Problem Description
Given two integer arrays A and B of size N each which represent values and weights associated with N items respectively.

Also given an integer C which represents knapsack capacity.

Find out the maximum total value that we can fit in the knapsack. If the maximum total value is ans, 
then return ⌊ans × 100⌋ , i.e., floor of (ans × 100).

NOTE:

You can break an item for maximizing the total value of the knapsack..

 */
public class FractionalKnapsack {

	public int solve(int[] A, int[] B, int C) {
		int n = A.length;
		double [] vw = new double[n];
		for(int i = 0 ; i < n ; i++) {
			vw[i] = (double)A[i]/B[i];
		}
		Arrays.sort(vw);
		int sum = 0;
		for(int i = n-1; i >=0 ; i--) {
			//sum+=
		}
		return 0;
    }
	
	public static void main(String[] args) {
		int [] A = {3,8,10,2,5};
		int [] B = {10,4,20,8,15};
		System.out.println(new FractionalKnapsack().solve(A, B, 40));
	}
}
