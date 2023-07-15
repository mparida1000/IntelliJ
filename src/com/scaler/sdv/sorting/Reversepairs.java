package com.scaler.sdv.sorting;

public class Reversepairs {
	private static int [] B;
	public static int solve(int[] A) {
		B = new int[A.length];
		int count = sort(A, 0 , A.length-1);
		return count;
    }
	
	public static int sort(int [] A, int l, int h) {
		if(l == h) return 0;
		int mid = (l+h)/2;
		int cnt_left = sort(A, l, mid);
		int cnt_right = sort(A, mid+1, h);
		int cont_l_and_r = merge(A, l, mid, h);
		return cnt_left+cnt_right+cont_l_and_r;
	}
	
	public static int merge(int [] A, int l, int mid, int h) {
		int i = l, j = mid+1, k = l, cnt = 0;
		while(i <= mid && j <= h) {
			if((long)A[i] > (long)2*A[j]) {
				cnt = cnt + (mid-i+1);
				j++;
			}else {
				i++;
			}
		}
		i = l;
		j = mid+1;
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
		for(k = l ; k <= h ; k++) {
			A[k] = B[k];
		}
		return cnt;
	}
	public static void main(String[] args) {
		//int [] A = {1, 3, 2, 3, 1};
		//int [] A = {4,8,6,3,2};
		int [] A = {2000000000, 2000000000, -2000000000};
		System.out.println(solve(A));
	}

}
