package com.scaler.adv.sorting;

public class UniqueElements {
	public static int solve(int[] A) {
        int count = 0;
        quickSort(A, 0, A.length-1);
        for(int i = 1 ; i < A.length ; i++) {
        	if(A[i] == A[i-1]) {
        		A[i]+=1;
        		count++;
        	}else if(A[i] < A[i-1]) {
        		int inr = A[i-1] - A[i] +1;
        		A[i]+= inr;
        		count+=inr;
        	}
        }
        return count;
    }
	public static void quickSort(int [] A, int l, int r) {
		if(l < r) {
			int pivot = reArrange(A, l, r);
			quickSort(A, l, pivot-1);
			quickSort(A, pivot+1, r);
		}
	}
	public static int reArrange(int [] A, int l, int r) {
		int i = l+1, j = r;
		while(i<=j) {
			if(A[i] <= A[l]) {
				i++;
			}else if(A[j] > A[l]) {
				j--;
			}else {
				swap(A, i, j);
				i++;j--;
			}
		}
		swap(A, j, l);
		return j;
	}
	
	public static void swap(int [] A, int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}
	
	public static void main(String[] args) {
		int [] A = {3,3,1,1,4,4,6,6};
		System.out.println(solve(A));
	}
}
