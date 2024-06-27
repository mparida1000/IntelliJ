package com.scaler.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//You are given an array of N integers, A1, A2 ,..., AN and an integer B. Return the of count of distinct numbers in all
// windows of size B.
//
//Formally, return an array of size N-B+1 where i'th element in this array contains number of distinct elements
// in sequence Ai, Ai+1 ,..., Ai+B-1.
//
//NOTE: if B > N, return an empty array.

public class DistinctNumbersinWindow {
	
	public static int[] dNums(int[] A, int B) {
        int n = A.length;
        if(B > n){
            int [] a = new int[0];
            return a;
        }
        int [] a = new int[n - B + 1];
        ArrayList<Integer> al = new ArrayList<>();
        Map<Integer, Integer> numMap = new HashMap<>();
        for(int  i = 0 ; i < B ; i++) {
        	if(!numMap.containsKey(A[i])) {
            	numMap.put(A[i], 1);
            }else {
            	numMap.computeIfPresent(A[i],  (key, val) -> val + 1);
            }
        }
        al.add(numMap.size());
        int s = 1, e = B;
        while(e < n) {
        	numMap.computeIfPresent(A[s-1],  (key, val) -> val - 1);
        	if(numMap.get(A[s-1]) == 0 )numMap.remove(A[s-1]);
        	if(!numMap.containsKey(A[e])) {
            	numMap.put(A[e], 1);
            }else {
            	numMap.computeIfPresent(A[e],  (key, val) -> val + 1);
            }
        	al.add(numMap.size());
        	s++;
        	e++;
        }
        for(int i = 0 ; i < al.size() ; i++) {
        	a[i] = al.get(i);
        }
        return a;
        
    }
	
	public static void main(String[] args) {
		int  [] A = {1, 2, 1, 3, 4, 3};
		int [] a = dNums(A, 3);
		 for(int i = 0 ; i < a.length ; i++) {
	        	System.out.print(a[i] + " ");
	        }
	}

}
