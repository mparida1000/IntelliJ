package com.scaler.binarysearch;
/**
 * 
Given an array of integers A of size N and an integer B.
The College library has N books. The ith book has A[i] number of pages.
You have to allocate books to B number of students so that the maximum number of pages allocated to a student is minimum.
A book will be allocated to exactly one student.
Each student has to be allocated at least one book.
Allotment should be in contiguous order, for example: A student cannot be allocated book 1 and book 3, skipping book 2.
Calculate and return that minimum possible number.

NOTE: Return -1 if a valid assignment is not possible.
 * @author mp1863
 *
 */
public class AllocateBooks {

	public static int books(int[] A, int B) {
		if(B > A.length) return -1;
		int sum = 0, min = A[0], n = A.length;
		for(int i = 0 ; i < n ; i++) {
			sum+=A[i];
			min = Math.min(min, A[i]);
		}
		int l = min, h = sum, ans = 0;
		while(l <= h) {
			int mid = (h + l)/2;
			if(checkIfAllStudentsCanBeAllocated(A, mid, B)) {
				ans = mid;
				h = mid - 1;
			}else {
				l = mid + 1;
			}
		}
		return ans;
    }
	
	public static boolean checkIfAllStudentsCanBeAllocated(int [] A, int mid, int B) {
		int count = 1, sum = 0;
		for(int i = 0 ; i < A.length ; i++) {
			if(A[i] > mid) return false;
			sum+=A[i];
			if(sum > mid) {
				count++;
				sum = A[i];
			}
		}
		if(count > B)
			return false;
		return true;
	}
	
	public static void main(String[] args) {
		//int [] A = {12, 34, 52, 31, 26, 14};
		int [] A = {97, 26, 12, 67, 10, 33, 79, 49, 79, 21, 67, 72, 93, 36, 85, 45, 28, 91, 94, 57, 1, 53, 8, 44, 68, 90, 24};
		System.out.println(books(A, 26));
		
	}
	
}
