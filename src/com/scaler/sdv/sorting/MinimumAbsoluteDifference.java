package com.scaler.sdv.sorting;

public class MinimumAbsoluteDifference {
	private static int [] B;
	
	public static int solve(int[] A) {
		B = new int[A.length];
		sort(A, 0, A.length-1);
		int min = Integer.MAX_VALUE;
		for(int i = A.length - 1; i > 0; i--) {
			int currMin = Math.abs(A[i] - A[i-1]);
			if( currMin < min) {
				min = currMin;
			}
		}
		return min;
    }
	
	public static void sort(int [] A, int l, int h) {
		if(l == h) return;
		int mid = (l+h)/2;
		sort(A, l, mid);
		sort(A, mid+1, h);
		merge(A, l, mid, h);
	}
	
	public static void merge(int [] A, int l, int mid, int h) {
		int i = l, j = mid+1, k = l;
		while(i <= mid && j <= h) {
			if(A[i] <= A[j]) {
				B[k] = A[i];
				i++;k++;
			}else {
				B[k] = A[j];
				j++;k++;
			}
		}
		while(i <= mid) {
			B[k] = A[i];
			i++;k++;
		}
		while(j <= h) {
			B[k] = A[j];
			j++;k++;
		}
		for(k = l ; k <=h ;k++) {
			A[k] = B[k];
		}
	}
	
	public static void main(String[] args) {
		int [] A = {5, 17, 100, 11};
		System.out.println(solve(A));
	}

}
