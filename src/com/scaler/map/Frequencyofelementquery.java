package com.scaler.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
