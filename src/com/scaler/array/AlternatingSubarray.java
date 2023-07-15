package com.scaler.array;

import java.util.ArrayList;

/**
 * Problem Description
 * You are given an integer array A of length N comprising of 0's & 1's, and an integer B.
 * You have to tell all the indices of array A that can act as a center of 2 * B + 1 length 0-1 alternating subarray.
 * A 0-1 alternating array is an array containing only 0's & 1's, and having no adjacent 0's or 1's. For e.g. arrays [0, 1, 0, 1], [1, 0] and [1] are 0-1 alternating, while [1, 1] and [0, 1, 0, 0, 1] are not.
 * Problem Constraints
 * 1 <= N <= 103
 *
 * A[i] equals to 0 or 1.
 *
 * 0 <= B <= (N - 1) / 2
 */
public class AlternatingSubarray {
	public static int[] solve(int[] A, int B) {
        ArrayList<Integer> arrList = new ArrayList<Integer>();
        int L = 2*B+1;
        int n = A.length;
        for(int i = 0 ; i < n ; i++){
            int start = (i - L/2);
            int end = (i + L/2);
            if(start >=0 && end < n){
                boolean b = false;
                for(int j = end; j >start ; j--){
                    if(A[j] == A[j-1]) b = true;
                }
                if(!b) arrList.add(i);
            }
        }
        int[] arr = arrList.stream().mapToInt(i -> i).toArray();
        return arr;
    }
	
	public static void main(String[] args) {
		int [] A = {0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1};
		int [] B = solve(A, 1);
		for(int i = 0 ; i < B.length ; i++) {
			System.out.print(B[i] + " ");
		}
	}

}
