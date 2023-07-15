package com.scaler.array;

import java.util.Arrays;

public class Multipleleftrotationsofthearray {
	
	public static int[][] solve(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int [][] op = new int[n][m];
        for(int i = 0 ; i < n ; i++) {
        	int [] copyArr = new int[m];
        	System.arraycopy(A, 0, copyArr, 0, m);
        	int k = B[i] % m;
        	reverse(0, k-1, copyArr);
        	reverse(k, m-1, copyArr);
        	reverse(0, m-1, copyArr);
        	for(int j = 0 ; j < m ; j++) {
        		op[i][j] = copyArr[j];
        	}
        }
        return op;
    }
	
	public static void reverse(int start, int end, int [] arr) {
		int i = start, j = end;
		while(i < j) {
			swap(arr, i, j );
			i++;
			j--;
		}
	}
	 public static void swap(int [] arr, int i, int j) {
		 int temp = arr[i];
		 arr[i] = arr[j];
		 arr[j] = temp;
		 
	 }
	
	public static void main(String[] args) {
		int [] A = {1, 2, 3, 4, 5};
		int [] B = {2, 3};
		int[][]op = solve(A, B);
		for(int i = 0 ; i < op.length ; i++) {
			for(int j = 0 ; j < op[0].length ; j++) {
				System.out.print(op[i][j] + " ");
			}
			System.out.println("\n");
			
		}
	}

}
