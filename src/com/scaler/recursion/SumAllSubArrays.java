package com.scaler.recursion;

import java.util.Arrays;

public class SumAllSubArrays {

	public static long subarraySum(int[] A) {
		long[] longArray = Arrays.stream(A).mapToLong(i -> i).toArray();
        long n = longArray.length;
        long totalSum = 0;
        for(long i = 0 ;  i < n ; i++){
            long sum = 0;
            for(long s = i ; s < n ; s++){
                sum+=longArray[(int) s];
                totalSum+=sum;
            }
        }
        return totalSum;
    }
	
	public static void main(String[] args) {
		int [] A = {2, 9, 5};
		System.out.println(subarraySum(A));
	}
}
