package com.scaler.sdv.sorting;

import java.util.Arrays;

public class LargestNumber {
	public static String largestNumber(final int[] A) {
        Arrays.sort(A);
        StringBuffer largest = new StringBuffer();
        int start = 0 ;
        while(start < A.length-1) {
        	if(A[start] == 0) start++;
        }
        for(int i = A.length-1 ; i >= start ; i--){
            largest.append(A[i]);
        }
        return largest.toString();
    }
	public static void main(String[] args) {
		int [] A = {0, 0, 0, 0, 0};
		System.out.println(largestNumber(A));
	}
}
