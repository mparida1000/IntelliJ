package com.scaler.binarysearch;
/*
 * Problem Description
Farmer John has built a new long barn with N stalls. Given an array of integers A of size N where each element of the array represents the location 
of the stall and an integer B which represents the number of cows.
His cows don't like this barn layout and become aggressive towards each other once put into a stall. To prevent the cows from hurting each other, 
John wants to assign the cows to the stalls, such that the minimum distance between any two of them is as large as possible. What is the largest 
minimum distance?
Problem Constraints
2 <= N <= 100000
0 <= A[i] <= 10^9
2 <= B <= N
 */
import java.util.Arrays;

public class Aggressivecows {
	
	public static int solve(int[] A, int B) {
		Arrays.sort(A);
		int _minimumDist = minimumDistance(A, A.length, B);
		return _minimumDist;
    }
	
	public static int minimumDistance(int [] A, int m, int n) {
		int l = Integer.MAX_VALUE;
		for(int i = 1 ; i < m ; i++) {
			if((A[i] - A[i-1]) < l) {
				l = Math.min(l, A[i] - A[i-1]);
			}
		}

		int h = A[m-1] - A[0];
		int ans = 0;
		while(l <= h) {
			int mid = l+(h-l)/2;
			if(checkIfAllCowsPlaced(A, m, n, mid)) {
				ans = mid;
				l = mid+1;
			}else {
				h = mid - 1;
			}
		}
		return ans;
	}
	
	public static boolean checkIfAllCowsPlaced(int [] A, int m, int n, int mid) {
		int count  = 1; 
		int _lastCowPos= A[0];
		for(int i = 1; i < m ; i++) {
			if ((A[i] - _lastCowPos) >= mid ) {
				count++;
				_lastCowPos = A[i];
				if(count == n)return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		int []  A = {4,9,2,6,5,8,10,13};
		System.out.println(solve(A, 3));
	}

}
