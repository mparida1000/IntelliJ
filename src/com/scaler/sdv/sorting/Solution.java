package com.scaler.sdv.sorting;

import java.util.ArrayList;

public class Solution {
    public static int solve(int [] A) {
        int chunks = 0;
        int curr = -1;
        for(int i = 0; i<A.length; i++){
            curr = Math.max(curr, A[i]);
            if(curr==i) chunks++;
        }
        return chunks;
    }
    
    public static void main(String[] args) {
		int [] A = {3,2,0,1,4,5};
		System.out.println(solve(A));
	}
}
