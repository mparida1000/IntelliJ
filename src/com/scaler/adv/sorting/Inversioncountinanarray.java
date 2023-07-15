package com.scaler.adv.sorting;

public class Inversioncountinanarray {
	private static int [] B;
	static int m = 1000000007;
	public static int solve(int[] A) {
		B = new int [A.length];
		return sort(A, 0, A.length-1);
    }
	
	public static int sort(int [] A, int l, int h) {
		if(l == h) return 0;
		int mid = (l+h)/2;
		long cont_left = (long)sort(A, l, mid);
		long cont_right = (long)sort(A, mid+1, h);
		long cont_left_and_right = (long)merge(A, l, mid, h);
		return (int)(cont_left%m + cont_right%m + cont_left_and_right%m)%m;
	}
	
	public static int merge(int [] A, int l, int mid, int h) {
		int i = l, j = mid+1, k = l; long cnt = 0;
		
		while(i <= mid && j <=h) {
			if(A[i] <= A[j]) {
				B[k] = A[i];
				i++;k++;
			}else {
				B[k] = A[j];
				j++;k++;
				cnt = (cnt%m + (mid-i+1)%m)%m;
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
		for(k = l ; k <= h ; k++) {
			A[k] = B[k];
		}
		return (int)(cnt%m);
	}
	
	public static void main(String[] args) {
		int [] A = {4,9,6,2,1,5,11,6};
		System.out.println(solve(A));
	}

}
