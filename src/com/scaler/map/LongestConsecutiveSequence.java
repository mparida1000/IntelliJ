package com.scaler.map;

import java.util.HashSet;
import java.util.Set;

/*
Problem Description
Given an unsorted integer array A of size N.
Find the length of the longest set of consecutive elements from array A.
Problem Constraints
1 <= N <= 106

-106 <= A[i] <= 106
Input Format
First argument is an integer array A of size N.
 */
public class LongestConsecutiveSequence {
    // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
    public int longestConsecutive(final int[] A) {
        int n = A.length;
        if (n == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : A) {
            set.add(num);
        }
        int maxLen = 1;
        for (int num : set) {
            if (!set.contains(num-1)) {
                int curLen = 1;
                while (set.contains(num+curLen)) {
                    curLen++;
                }
                maxLen = Math.max(maxLen, curLen);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int [] A ={100, 4, 200, 1, 3, 2};
        System.out.println(new LongestConsecutiveSequence().longestConsecutive(A));
    }
}
