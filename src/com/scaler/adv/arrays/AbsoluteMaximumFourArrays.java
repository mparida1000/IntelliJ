package com.scaler.adv.arrays;

public class AbsoluteMaximumFourArrays {
	
	public static int solve(int[] A, int[] B, int[] C, int[] D) {
		int n = A.length;
		int [][] copyArr = new int [4][n];
		copyArr[0] = A;copyArr[1] = B;copyArr[2] = C;copyArr[3] = D;
		int [][] X = new int [4][n];
		int [][] Y = new int [4][n];
		for(int i = 0 ; i < 4 ; i++) {
			for(int j = 0 ; j < n ; j++) {
				X[i][j] = copyArr[i][j]+j;
				Y[i][j] = copyArr[i][j]-j;
			}
		}
		int [][] x = new int[4][2];
		int [][] y = new int[4][2];
		x = getMinMax(X);
		y = getMinMax(Y);
		
		return Math.max(x[0][0] - x[0][1], y[0][0] - y[0][1]) +
				Math.max(x[1][0] - x[1][1], y[1][0] - y[1][1])+
				Math.max(x[2][0] - x[2][1], y[2][0] - y[2][1])+
				Math.max(x[3][0] - x[3][1], y[3][0] - y[3][1]);
    }
	
	public static int [][] getMinMax(int [][] arr){
		int [][] op = new int[4][2];
		int n = arr[0].length;
		for(int i = 0 ; i < 4 ; i++) {
			int min  = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for(int j = 0 ; j < n ; j++) {
				if(arr[i][j] < min) {
					min = arr[i][j];
				}
				if(arr[i][j] > max) {
					max = arr[i][j];
				}
			}
			op[i][0] = max;
			op[i][1] = min;
		}
		return op;
	}
	
	public static void main(String[] args) {
		int [] A = {1, 2, 3, 4};
		int [] B = {-1, 4, 5, 6};
		int [] C = {-10, 5, 3, -8};
		int [] D = {-1, -9, -6, -10};
		int res = solve(A, B, C, D);
		System.out.println(res);
	}

}
