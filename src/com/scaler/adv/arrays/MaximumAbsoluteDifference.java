package com.scaler.adv.arrays;

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
