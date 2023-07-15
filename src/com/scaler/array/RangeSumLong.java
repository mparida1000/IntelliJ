package com.scaler.array;

public class RangeSumLong {
	
	public static long[] rangeSum(int[] A, int[][] B) {
        int m = B.length;
        int n = A.length;
        long sum = 0;
        long result [] = new long[m];
        for(int i = 1 ; i < n ; i++){
            A[i] = A[i-1] + A[i];
        }
        for(int i = 0 ; i < m ; i++){
            int start = B[i][0]-1;
            int end = B[i][1]-1;
            if(start == 0) {
            	result[i] = A[end];
            }else {
                result[i] = A[end] - A[start-1];
            }

        }
        return result;
    }
	
	public static void main(String[] args) {
		int [] A = {7, 3, 1, 5, 5, 5, 1, 2, 4, 5 };
		int [][] B = {
				{7, 10},
				{3, 10},
				{3,5},
				{1,10}
				
		};
		long L[] = rangeSum(A, B);
		for(int i = 0 ; i < L.length ; i++) {
			System.out.print(L[i] +" ");
		}
	}

}
