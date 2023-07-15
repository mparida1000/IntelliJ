package com.scaler.twopointer;

import java.util.Arrays;

public class ThreeSum {
	
	public static int threeSumClosest(int[] A, int B) {
        int n = A.length;
        int minDiff = Integer.MAX_VALUE;
        int closestSum = Integer.MAX_VALUE;
        Arrays.sort(A);
        for(int i = 0 ; i < n - 2 ; i++) {
        	int j = i + 1;
        	int k = n - 1;
        	while(j < k) {
        		int sum = A[i] + A[j] + A[k];
        		int diff = Math.abs(sum - B);
        		if(diff == 0 ) return sum;
        		if(diff < minDiff) {
        			minDiff = diff;
        			closestSum = sum;
        		}
        		if(sum < B) {
        			j++;
        		}else {
        			k--;
        		}
        	}
        }
        return closestSum;
    }

	public static void main(String[] args) {
		int [] A = {-1, 2, 1, -4};
		System.out.println(threeSumClosest(A, 1));
	}

}
