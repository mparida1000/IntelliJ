package com.scaler.adv.sorting;

public class MergeTwoSortedArrays {
	
	public static int[] solve(final int[] A, final int[] B) {
		int m = A.length, n = B.length;
		int i = 0, j = 0, k = 0;
		int [] op = new int[m+n];
		while(i < m && j < n) {
			if(A[i] <= B[j]) {
				op[k] = A[i];
				i++;k++;
			}else {
				op[k] = B[j];
				j++;k++;
			}
		}
		while(i < m) {
			op[k] = A[i];
			i++;k++;
		}
		while(j < n) {
			op[k] = B[j];
			j++;k++;
		}
		return op;
    }
	
	public static void main(String[] args) {
		int [] A = {4,7,9};
		int [] B = {2,11,19};
		int [] op = solve(A, B);
		for(int i = 0 ; i < op.length ; i++) {
			System.out.print(op[i] + " ");
		}
	}

}
