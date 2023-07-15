package com.scaler.greedy;

import java.util.Arrays;

/**
 * Problem Description
N Mice and N holes are placed in a straight line. Each hole can accommodate only one mouse.
The positions of Mice are denoted by array A, and the position of holes is denoted by array B.
A mouse can stay at his position, move one step right from x to x + 1, or move one step left from x to x − 1. Any of these moves consume 1 minute.
Assign mice to holes so that the time when the last mouse gets inside a hole is minimized.

Problem Constraints
1 <= N <= 105
-10^9 <= A[i], B[i] <= 10^9
 * @author mp1863
 *
 */

public class AssignMicetoHoles {

	public int mice(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int n = A.length;
        int maxTime = Integer.MIN_VALUE;
        for(int i = 0 ; i < n ; i++) {
        	maxTime = Math.max(maxTime, Math.abs( A[i] - B[i]));
        }
        return maxTime;
    }
	public static void main(String[] args) {
		int [] A = {1,-3,2,-8,7,4};
		int [] B = {2,9,-2,-1,4,-3};
		AssignMicetoHoles am = new AssignMicetoHoles();
		System.out.println(am.mice(A, B));
	}
}
