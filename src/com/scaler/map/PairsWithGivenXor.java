package com.scaler.map;

import java.util.HashSet;
import java.util.Set;

public class PairsWithGivenXor {
	public static int solve(int[] A, int B) {
		int n = A.length;
		Set<Integer> set = new HashSet();
		int c = 0;
		for(int i = 0 ; i < n ; i++) {
			int a = B^A[i];
			if(set.contains(a)) {
				c++;
			}
			set.add(A[i]);
		}
		return c;
        
    }
	
	public static void main(String[] args) {
		int []  A = {3, 6, 8, 10, 15, 50, 6};
		System.out.println(solve(A, 5));
		System.out.println(6^5);
	}

}
