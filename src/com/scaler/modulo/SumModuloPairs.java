package com.scaler.modulo;

import java.util.HashMap;
import java.util.Map;

public class SumModuloPairs {
	public static int sumModuloPairs(int[] A) {
		long sum = 0;
	    int MOD = 1000000007;
	    int n = A.length;
	    Map<Integer, Integer> bucket = new HashMap<>();
	    for (int i = 0; i < n; i++) {
	        for (int j = A[i]; j <= 1000; j += A[i]) {
	            if (j > A[i]) {
	                int r = j % A[i];
	                if (!bucket.containsKey(r)) {
	                    bucket.put(r, j);
	                }
	            }
	        }
	        if (!bucket.isEmpty()) {
	            sum += A[i] % bucket.get(0);
	            sum %= MOD;
	        }
	    }
	    return (int) sum;
	}
	
	public static void main(String[] args) {
		int [] A = {2,3,5};
		System.out.println(sumModuloPairs(A));
	}
}
