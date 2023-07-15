package com.scaler.array;

public class Sumofoddindices {
	public static int[] solve(int[] A, int[][] B) {
		int n = A.length;
		int m = B.length;
		int [] pf = new int [n];
		int [] op = new int[m];
		if(n == 1) return  op;
		pf[0] = A[0];
		pf[1] = A[1];
		for(int i = 2 ; i < n ; i++) {
			if(i%2 != 0) {
				pf[i]+=pf[i-2]+A[i];
			}else {
				pf[i] = pf[i-1];
			}
		}
		for(int i = 0 ; i < m ; i++) {
			int sum = 0;
			int start = B[i][0];
			int end = B[i][1];
			if (end == 0) {
				sum = 0;
			}else if(start == 0 || start == 1) {
				sum+=pf[end];
			}else {
				sum+=pf[end] - pf[start-1];
			}
			op[i] = sum;
		}
		return op;
    }
	
	public static void main(String[] args) {
		int [] A = {10, 14, 19};
		int[][] B = {
				{0,0}
		};
		int [] op = solve(A, B);
		for(int i = 0 ; i < op.length ; i++) {
			System.out.print(op[i] + " ");
		}
	}
	
}
