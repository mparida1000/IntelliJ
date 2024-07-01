package com.scaler.map;

import java.util.HashSet;
/*
Problem Description

Misha likes finding all Subarrays of an Array. Now she gives you an array A of N elements and told you to
find the number of subarrays of A, that have unique elements.

Since the number of subarrays could be large, return value % 109 +7.
Problem Constraints

1 <= N <= 105
1 <= A[i] <= 106
 */
public class UniqueSubarrays {
    public static void main(String[] args) {
        // Example static array
        int[] A = {1, 2, 1, 3};
        int N = A.length;

        System.out.println(countUniqueSubarrays(A, N));
    }

    private static int countUniqueSubarrays(int[] A, int N) {
        final int MOD = 1000000007;
        HashSet<Integer> set = new HashSet<>();
        int left = 0;
        long count = 0;

        for (int right = 0; right < N; right++) {
            // Move left pointer to ensure all elements in window are unique
            while (set.contains(A[right])) {
                set.remove(A[left]);
                left++;
            }
            set.add(A[right]);

            // All subarrays ending at `right` with unique elements
            // contribute to the count
            count += (right - left + 1);
            count %= MOD;
        }

        return (int) count;
    }
}
