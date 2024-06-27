package com.scaler.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/*
Problem Description
Given an array A. You have some integers given in the array B.
For the i-th number, find the frequency of B[i] in the array A and return a list containing all the frequencies.
Problem Constraints
1 <= |A| <= 105
1 <= |B| <= 105
1 <= A[i] <= 105
1 <= B[i] <= 105
 */
public class Frequencyofelementquery {

	public int[] solve(int[] A, int[] B) {
        Map<Integer, Integer> numMap = new HashMap();

        
        for(int i = 0 ; i < A.length ; i++){
            if(!numMap.containsKey(A[i])) {
            	numMap.put(A[i], 1);
            }else {
            	numMap.computeIfPresent(A[i],  (key, val) -> val + 1);
            }
        }
        for(int i = 0 ; i < B.length ; i++){
        	if(numMap.containsKey(B[i])) {
        		B[i] = numMap.get(B[i]);
        	}else {
        		B[i] = 0;
        	}
        }
        
     
        return B;
    }
}
