package com.scaler.map;

import java.util.HashMap;
import java.util.Map;
/**
 * You are given an array A of N integers and an integer B. Count the number of pairs (i,j) such that A[i] + A[j] = B and i ≠ j.
 * @author mp1863
 *
 */
public class CountPairSum1 {
	public static int solve(int[] A, int B) {
		Map<Integer, Integer> map = new HashMap();
		int n = A.length;
		int c = 0;
		for(int i = 0 ; i < n ; i++) {
				int a = A[i];
				int b = B-a;
				if(map.containsKey(b)) {
					c = c + map.get(b);
				}
				map.put(A[i], map.get(A[i])!=null ? map.get(A[i])+1 : 1);
		}
		return c;
    }
	
	public static void main(String[] args) {
		int [] A = {5,5,1,6,2,2,5,1,4,2,1,5};
		System.out.println(solve(A, 6));
	}
	

}
