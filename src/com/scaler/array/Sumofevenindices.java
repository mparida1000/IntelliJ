package com.scaler.array;

public class Sumofevenindices {
	public static int[] solve(int[] A, int[][] B) {
		int n = A.length;
		int m = B.length;
		int [] pf = new int [n];
		int [] op = new int[m];
		pf[0] = A[0];
		for(int i = 1 ; i < n ; i++) {
			if(i%2 == 0) {
				pf[i]+=pf[i-2]+A[i];
			}else {
				pf[i] = pf[i-1];
			}
		}
		
		for(int i = 0 ; i < m ; i++) {
			int sum = 0;
			int start = B[i][0];
			int end = B[i][1];
			if(start == 0) {
				sum+=pf[end];
			}else {
				sum+=pf[end] - pf[start-1];
			}
			op[i] = sum;
		}
		return op;
    }
	public static void main(String[] args) {
		int [] A = {16, 3, 3, 6, 7, 8, 17, 13, 7};
		int[][] B = {
				{2, 6},//13
				{4,7},//18
				{6,7}//18
		};
		int [] op = solve(A, B);
		for(int i = 0 ; i < op.length ; i++) {
			System.out.print(op[i] + " ");
		}
	}

}
