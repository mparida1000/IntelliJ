package com.scaler.array;
/*
Problem Description
You are given an array of N integers, A1, A2, .... AN.
Return the maximum value of f(i, j) for all 1 ≤ i, j ≤ N. f(i, j) is defined as |A[i] - A[j]| + |i - j|, where |x| denotes absolute value of x.
Problem Constraints
1 <= N <= 100000
-109 <= A[i] <= 109
 */
public class MaximumAbsoluteDifference {
	
	public static int maxArr(int[] A) {
		int [] X = new int[A.length];
		int [] Y = new int[A.length];
		for(int i = 0 ; i < A.length ; i++) {
			X[i] = A[i]+i;
			Y[i] = A[i]-i;
		}
		int x[] = getMinMax(X);
		int y[] = getMinMax(Y);
		return Math.max(x[0]-x[1], y[0]-y[1]);
    }
	
	public static int[] getMinMax(int [] arr) {
		int op [] = new int [2];
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i < arr.length ; i++) {
			if(arr[i] < min)min = arr[i];
			if(arr[i] > max)max = arr[i];
		}
		op[0] = max;op[1] = min;
		return op;
	}
	
	public static void main(String[] args) {
		int [] A = {1, 3, -1};
		System.out.println(maxArr(A));
	}

}
