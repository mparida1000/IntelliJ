package com.scaler.binarysearch;

import java.util.Arrays;
/**
 * Problem Description
Given a matrix of integers A of size N x M in which each row is sorted.
Find and return the overall median of matrix A.
NOTE: No extra memory is allowed.
NOTE: Rows are numbered from top to bottom and columns are numbered from left to right.
Problem Constraints
1 <= N, M <= 10^5
1 <= N*M <= 10^6
1 <= A[i] <= 10^9
N*M is odd
 * @author mp1863
 *
 */
public class MatrixMedian {

	public static int findMedian(int[][] A) {
		int l = 1, h = 1000000000;
		int ans = -1;
		int n = A.length, m = A[0].length;
		int mid_pos = (n*m)/2;
		while(l <= h) {
			int mid = (l+h)/2;
			int count  = 0 ;
			for(int i = 0 ; i < n ; i++) {
				count+=countRowWise(A[i], mid);
			}
			if(count <= mid_pos) {
				ans = mid;
				l = mid + 1;
			}else {
				h = mid - 1;
			}
		}
		return ans;
	}
	
	public static int countRowWise(int [] A, int x) {
		int l = 0, h = A.length - 1;
		int count  = 0;
		while(l <= h) {
			int mid = l+(h-l)/2;
			if(A[mid] < x) {
				count = mid+1;
				l = mid + 1;
			}else {
				h = mid - 1;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		//int[][] A = { { 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3 } };
		//int [][] A = { {2},{1},{4},{1},{2},{2},{5}                    };
		int [][] A = {
				{5,16,17},
				{4,5,16},
				{2,13,18}
		};
		System.out.println(findMedian(A));
	}
}
