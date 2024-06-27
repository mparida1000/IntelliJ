package com.scaler.map;

import java.util.HashMap;
import java.util.Map;

/*
Problem Description
Given an one-dimensional unsorted array A containing N integers.

You are also given an integer B, find if there exists a pair of elements in the array whose difference is B.

Return 1 if any such pair exists else return 0.

Problem Constraints
1 <= N <= 105
-103 <= A[i] <= 103
-105 <= B <= 105
 */
public class PairWithGivenDiff {
    public int solve(int[] A, int B) {
        int n = A.length;
        Map<Integer, Integer> numMap = new HashMap();
        for(int i = 0 ; i < n ; i++){
            if(!numMap.containsKey(A[i])) {
                numMap.put(A[i], 1);
            }else {
                numMap.computeIfPresent(A[i],  (key, val) -> val + 1);
            }
        }
        for(int i = 0 ; i < n ; i++){
            int a = A[i];
            int b = A[i] - B;
            if(a != b && numMap.containsKey(b)){
                return 1;
            } else if(a == b && numMap.get(b) > 1){
                return 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int [] arr = {5, 10, 3, 2, 50, 80};
        System.out.println(new PairWithGivenDiff().solve(arr, 78));
    }
}
