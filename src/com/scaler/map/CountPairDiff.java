package com.scaler.map;
/*
 * You are given an array A of N integers and an integer B.
Count the number of pairs (i,j) such that A[i] - A[j] = B and i ≠ j.

Since the answer can be very large, return the remainder after dividing the count with 109+7.
 */
import java.util.HashMap;
import java.util.Map;

public class CountPairDiff {
	public static int solve(int[] A, int B) {
		Map<Long, Long> map = new HashMap();
		long n = A.length;
		long c = 0;
		int m = 1000000007;
		for(long i = 0 ; i < n ; i++) {
				long a = A[(int)i];
				long b = a - B;
				long b_ = a + B;
				if(map.containsKey(b)) {
					c = c + map.get(b);
				}
				if(map.containsKey(b_)) {
					c = c + map.get(b_);
				}
				map.put((long)A[(int)i], map.get((long)A[(int)i])!=null ? map.get((long)A[(int)i])+1 : 1);
		}
		return (int)c%m;
		
    }
	
	public static void main(String[] args) {
		//int [] A = {1,2,1,2};
		int [] A = {9, 14, 4, 3, 8, 1, 6, 9, 4, 4, 4};
		System.out.println(solve(A, 5));
	}

}
