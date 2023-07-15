package com.scaler.array;

import java.util.Arrays;

public class SubmatrixSumQueries {
	
	public static int[] solve(int[][] A, int[] B, int[] C, int[] D, int[] E) {
		int n = A.length;
		int m  = A[0].length;
		int q = B.length;
		int [] op = new int[q];
		long [][] pf = new long [n][m];
		pf[0] = Arrays.stream(A[0]).asLongStream().toArray();
		for(int j = 0 ; j < m ; j++) {//column-wise prefix
			for(int i = 1 ; i < n ; i++) {
				pf[i][j] = A[i][j]+pf[i-1][j];
			}
		}
		for(int i = 0 ; i < n ; i++) {//row-wise prefix
			for(int j = 1; j < m ; j++) {
				pf[i][j] = pf[i][j]+pf[i][j-1];
			}
		}
		int mo = 1000000007;
		for(int i = 0 ; i < q ; i ++) {
			int a1 = B[i]-1;
			int b1 = C[i]-1;
			int a2 = D[i]-1;
			int b2 = E[i]-1;
			op[i] = (int)(pf[a2][b2]%mo - (b1 > 0 ? pf[a2][b1-1]%mo : 0) - (a1 > 0 ? pf[a1-1][b2]%mo : 0) + (a1 > 0 && b1 > 0 ? pf[a1-1][b1-1]%mo : 0))%mo;
		}
		return op;
    }
	
	public static void main(String[] args) {
		System.out.println(-5%3);
//		int [][] A = {
//				{1,2,3},
//				{4,5,6},
//				{7,8,9}
//		};
		int [][] A = {
				{5, 17, 100, 11},
				{0, 0,  2,   8}
		};
		int [] B = {1,1};
		int [] C = {1,4};
		int [] D = {2,2};
		int [] E = {2,4};
		int [] op = solve(A, B, C, D, E);
		for(int i = 0 ; i < op.length ; i++) {
			System.out.print(op[i] + " ");
		}
		
	}

}
