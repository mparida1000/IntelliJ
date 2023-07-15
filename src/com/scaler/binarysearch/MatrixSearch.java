package com.scaler.binarysearch;
/**
 * Problem Description
Given a matrix of integers A of size N x M and an integer B. Write an efficient algorithm that searches for integer B in matrix A.
This matrix A has the following properties:
Integers in each row are sorted from left to right.
The first integer of each row is greater than or equal to the last integer of the previous row.
Return 1 if B is present in A, else return 0.
NOTE: Rows are numbered from top to bottom, and columns are from left to right.

Problem Constraints
1 <= N, M <= 1000
1 <= A[i][j], B <= 10^6
 * @author mp1863
 *
 */
public class MatrixSearch {

	public int searchMatrix(int[][] A, int B) {
        int n = A.length;
		int m = A[0].length;
		int [] arr1 = new int[n];
		for(int i = 0 ; i < n ; i++) {
			arr1[i] = A[i][0];
		}
		int row = binarySearch(arr1, B, true);
		int result = binarySearch(A[row], B, false);
		return result >= 0 ? 1 : 0;
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
}
