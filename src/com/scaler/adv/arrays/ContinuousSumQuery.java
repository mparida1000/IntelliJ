package com.scaler.adv.arrays;

public class ContinuousSumQuery {
	
	public static int[] solve(int A, int[][] B) {
		int [] op = new int[A];
		for(int i = 0 ; i < B.length ; i++) {
			int start = B[i][0]-1;
			int end = B[i][1]-1;
				op[start]+= B[i][2];
				if(end < A-1)op[end+1]+= -1*B[i][2];
		}
		for(int k = 1 ; k < A; k++) {
			op[k]= op[k-1]+op[k];
		}
		return op;
    }
	
	public static void main(String[] args) {
		int [][] B = {
				{1,2,10},
				{2,3,20},
				{2,5,25}
		};
		int [] A = solve(5, B);
		for(int i = 0 ; i < A.length ; i++) {
			System.out.print(A[i] + " ");
		}
	}

}
