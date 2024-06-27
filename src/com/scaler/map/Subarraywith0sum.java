package com.scaler.map;

import java.util.HashSet;
import java.util.Set;

/*
Problem Description
Given an array of integers A, find and return whether the given array contains a non-empty subarray with a sum equal to 0.

If the given array contains a sub-array with sum zero return 1, else return 0.
Problem Constraints
1 <= |A| <= 100000

-10^9 <= A[i] <= 10^9
 */
public class Subarraywith0sum {
    public int solve(int[] A) {
        Set<Long> sumSet = new HashSet();
        int n = A.length;
        long sum = 0;
        for(int i = 0 ; i < n ; i++){
            sum+=A[i];
            if(A[i] == 0 || sumSet.contains(sum) || sum == 0)
                return 1;
            sumSet.add(sum);
        }
        return 0;
    }

    public static void main(String[] args) {
        int [] A = {1, 2, 3, 4, 5};
        System.out.println(new Subarraywith0sum().solve(A));
    }
}
