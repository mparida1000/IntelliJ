package com.scaler.binarysearch;

public class SingleElementSortedArray {

	public int solve(int[] A) {
        int l = 0, h = A.length-1;
		while(l <= h){
			int mid = (l+h)/2;
			if(mid == 0 || mid == A.length-1 || (A[mid] > A[mid-1] && A[mid] < A[mid+1])) {
				return A[mid];
			}
			if(A[mid] == A[mid-1] && mid%2 != 0) {
				l = mid+1;
			}else if(A[mid] == A[mid+1] && mid%2 == 0) {
				l = mid+1;
			}else {
				h = mid-1;
			}
		}
		return -1;
    }
	
	public static void main(String[] args) {
		int [] A = {5,5,6,6,7,7,8,11,11};
		System.out.println(new SingleElementSortedArray().solve(A));
	}
}
