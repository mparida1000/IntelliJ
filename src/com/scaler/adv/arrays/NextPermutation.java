package com.scaler.adv.arrays;

public class NextPermutation {
	public static int[] nextPermutation(int[] A) {
		int n = A.length;
		int i = n-2;
		while(i >= 0 && A[i] >= A[i+1]) i--;
		if(i >= 0) {
			int j = n-1;
			while(A[j] <= A[i]) j--;
			swap(A, i, j);
		}
		reverse(A, i+1, n-1);
		return A;
    }
	
	public static void swap (int [] A, int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;;
	}
	
	public static void reverse (int [] A, int i, int j) {
		while(i < j) {
			swap(A, i++, j--);
		}
	}
	
	public static void main(String[] args) {
		int [] A = {9,6,5,9,2,3,8,6,5};
		int op [] = nextPermutation(A);
		for(int i = 0 ; i < op.length ; i++) {
			System.out.print(op[i] + " ");
		}
	}

}
