package com.scaler.map;

import java.util.HashMap;
import java.util.Map;

public class CountPairSum {
	public static int solve(int[] A, int B) {
		Map<Integer, Integer> m = new HashMap<>();
		int n = A.length;
		long count = 0;
		for(int i = 0 ; i < n ; i++) {
			if(!m.containsKey(A[i])) {
				m.put(A[i], 1);
			}else {
				m.put(A[i], m.get(A[i])+1);
			}
		}
		for(int i = 0 ; i < n ; i++) {
			int a = A[i];
			int b = B - a;
			if(a == b && m.get(b) > 1) {
				count++;
			}else if(m.containsKey(b)) {
				count++;
			}
		}
		return (int)count%1000000007;
    }
	
	public static void main(String[] args) {
		int [] A = {1,6,2,5,4,3,1,4};
		System.out.println(solve(A, 6));
	}

}
