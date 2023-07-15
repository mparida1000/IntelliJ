package com.scaler.sdv.sorting;

public class MatrixSearch {
	public static int searchMatrix(int[][] A, int B) {
		int n = A.length;
		int m = A[0].length;
		int [] arr1 = new int[n];
		for(int i = 0 ; i < n ; i++) {
			arr1[i] = A[i][0];
		}
		int row = binarySearch(arr1, B, true);
		int result = binarySearch(A[row], B, false);
		return result >= 0 ? 1 : -1;
    }
	
	public static int binarySearch(int[] A, int B, boolean colSearch) {
		int l = 0, h = A.length-1;
		int possibleInsertIdx = -1;
		while(l <= h) {
			int mid = (l+h)/2;
			if(A[mid] == B)return mid;
			if(A[mid] > B) {
				if(colSearch && mid == l && B <= A[l]) return l;
				else if(colSearch && B < A[mid] && B > A[mid-1]) {
					possibleInsertIdx = mid-1;
					return possibleInsertIdx;
				}
				h = mid-1;
			}else {
				if(colSearch && mid == h && B >= A[mid]) return h;
				else if(colSearch && B > A[mid] && B < A[mid+1]) {
					possibleInsertIdx = mid;
					return possibleInsertIdx;
				}
				l = mid+1;
			}
		}  
		return possibleInsertIdx;
	}
	
	public static void main(String[] args) {
		int [][] A = {
				{1,3,5,7},
				{10,11,16,20},
				{23,30,34,50},
				{60,70,80,90}
		};
		System.out.println(searchMatrix(A, 35));
	}

}
