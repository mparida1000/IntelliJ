package com.scaler.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonElements {
	
	public static int[] solve(int[] A, int[] B) {
        int m = A.length; int n = B.length;
        Map<Integer, Integer> m1 = new HashMap<>();
        Map<Integer, Integer> m2 = new HashMap<>();
        List<Integer> l = new ArrayList<>();
        for(int i = 0 ; i < m ; i++) {
        	if(!m1.containsKey(A[i])) m1.put(A[i], 1);
        	else m1.put(A[i], m1.get(A[i]) + 1);        }
        for(int j = 0 ; j < n ; j++) {
        	if(!m2.containsKey(B[j]) && m1.containsKey(B[j])) {
        		m2.put(B[j], 1);
        		l.add(B[j]);
        	}else if(m1.containsKey(B[j]) && m2.get(B[j]) < m1.get(B[j])){
        		m2.put(B[j], m2.get(B[j])+1);
        		l.add(B[j]);
        	}
        }
        Collections.sort(l);
        int [] arr = new int [l.size()];
        for(int k = 0 ; k < l.size() ; k++) {
        	arr[k] = l.get(k);
        }
        return arr;
    }
	
	public static void main(String[] args) {
		int [] A = {1,2,2,2,3,6,5,4};
		int [] B = {1,4,7,8,5,2,36,9,5,5};
		int [] arr = solve(A, B);
		for(int k = 0 ; k < arr.length ; k++) {
        	System.out.print(arr[k] + " ");
        }
	}

}
