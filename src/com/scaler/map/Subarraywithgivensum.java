package com.scaler.map;

import java.util.HashMap;
import java.util.Map;

public class Subarraywithgivensum {
	
	public static int[] solve(int[] A, int B) {
		int n = A.length;
		int notFound [] = {-1};
		int sum = A[0];
		int firstIndex = 0;
		for(int i = 1 ; i < A.length ; i++) {
			sum+=A[i];
			while(sum > B) {
				sum = sum-A[firstIndex];
				firstIndex++;
			}
			if(sum == B) {
				int l = i-firstIndex+1;
				int [] a =  new int[l];
				int k = 0;
				int j = firstIndex;
				while(k < l && j <=i) {
					a[k] = A[j];
					k++;j++;
				}
				return a;
			}
		}
		return notFound;
    }
	
	public static void main(String[] args) {
		
		int [] A = {1,2,3,4,5};
		int[]  B = {42, 9, 38, 36, 48, 33, 36, 50, 38, 8, 13, 37, 33, 38, 17, 25, 50, 50, 41, 29, 34, 18, 16, 6, 49, 16, 21, 29, 41, 7, 37, 14, 5, 30, 35, 26, 38, 35, 9, 36, 34, 39, 9, 4, 41, 40, 3, 50, 27, 17, 14, 5, 31, 42, 5, 39, 38, 38, 47, 24, 41, 5, 50, 9, 29, 14, 19, 27, 6, 23, 17, 1, 22, 38, 35, 6, 35, 41, 34, 21, 30, 45, 48, 45, 37};
		int [] a = solve(B, 100);
		for(int i = 0 ; i < a.length ; i++) {
			System.out.print(a[i] + " ");
		}
		
	}

}
