package com.scaler.array;

public class CountEvenInPrefixArray {
	
	public static int[] solve(int[] A, int[][] B) {
		int n = A.length;
		A[0] = A[0] %2 == 0 ? 1: 0;
		for(int i = 1 ; i<n ; i++) {
			if(A[i] %2 == 0) {
				A[i] = A[i-1]+1;
			}else {
				A[i] = A[i-1];
			}
		}
		int m = B.length;
		int result[] = new int[m];
		for(int i = 0 ; i < m ; i++) {
			int l = B[i][0];
			int r = B[i][1];
			if (l == 0) {
				result[i] = A[r];
			}else {
				result[i] = A[r] - A[l-1];
			}
			
		}
		return result;
    }
	
	public static void main(String[] args) {
		int [] A = {2, 1, 8, 3, 9};
		int [][] B = {
				{0,3},
				{2,4}
		};
		int [] result = solve(A, B);
		for(int i = 0 ; i < result.length ; i++) {
			System.out.print(result[i] + " ");
		}
	}

}
