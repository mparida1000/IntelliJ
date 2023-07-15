package com.scaler.sdv.sorting;

import java.util.Arrays;

public class SumTheDiff {
	
	public static int solve(int[] A) {
		Arrays.sort(A);
		long sum = 0;
		int m = 1000000007;
		int max = 0, min = 0;
		for(int i = 0 ; i < A.length ; i++) {
			max = max%m + (A[i]*(1<<i))%m;
			min = min%m + (A[i]*(1<<(A.length-1-i)))%m;
		}
		return((max-min)%m+m)%m;
    }
	
	public static void main(String[] args) {
		int [] A = {3,2,1};
		System.out.println(solve(A));
		System.out.println(1<<0);
	}

}
