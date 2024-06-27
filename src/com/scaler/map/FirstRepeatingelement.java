package com.scaler.map;

import java.util.HashMap;
import java.util.Map;
/*
Problem Description
Given an integer array A of size N, find the first repeating element in it.
We need to find the element that occurs more than once and whose index of the first occurrence is the smallest.
If there is no repeating element, return -1.
Problem Constraints
1 <= N <= 105
1 <= A[i] <= 109
Input Format
The first and only argument is an integer array A of size N.


 */
public class FirstRepeatingelement {

	public static int solve(int[] A) {
        Map<Integer, Integer> numMap = new HashMap();
        int n = A.length;
        for(int i = 0 ; i < n ; i++){
            if(!numMap.containsKey(A[i])) {
            	numMap.put(A[i], 1);
            }else {
            	numMap.computeIfPresent(A[i],  (key, val) -> val + 1);
            }
        }
        for(int i = 0 ; i < n ; i++){
            if(numMap.get(A[i])  > 1 ) return A[i];
        }
        return -1;
    }
	
	public static void main(String[] args) {
		int [] A = {10, 5, 3, 4, 3, 5, 6};
		System.out.println(solve(A));
	}
}
