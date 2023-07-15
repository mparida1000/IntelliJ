package com.scaler.adv.sorting;

public class KthSmallestElementInArray {
	static int b[];
	
	public static int kthsmallest(final int[] A, int B) {
		b =  new int[A.length];
		sort(A, 0, A.length-1);
		return A[B-1];
    }
	
	public static void sort(int [] A, int l, int h) {
		if(l==h) return;
		int mid = (l+h)/2;
		sort(A, l, mid);
		sort(A, mid+1, h);
		merge(A, l, mid, h);
	}
	
	public static void merge(int [] A, int l, int mid, int h) {
		int i = l, j = mid+1, k = l;
		while(i <= mid && j <= h) {
			if(A[i] <= A[j]) {
				b[k] = A[i];
				i++;k++;
			}else {
				b[k] = A[j];
				j++;k++;
			}
		}
		while(i <= mid) {
			b[k] = A[i];
			i++;k++;
		}
		while(j <= h) {
			b[k] = A[j];
			j++;k++;
		}
		for(k = l ; k<= h ; k++) {
			A[k] = b[k];
		}
	}
	
	public static void main(String[] args) {
		int [] A = {4,8,7,2,5,6,9,11,78,41};
		System.out.println(kthsmallest(A, 9));
	}

}
