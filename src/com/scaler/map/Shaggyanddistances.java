package com.scaler.map;
import java.util.HashMap;
import java.util.Map;
/*\
Problem Description
Shaggy has an array A consisting of N elements.
We call a pair of distinct indices in that array a special if elements at those indices in the array are equal.

Shaggy wants you to find a special pair such that the distance between that pair is minimum.
Distance between two indices is defined as |i-j|. If there is no special pair in the array, then return -1.
Problem Constraints
1 <= |A| <= 105
 */
public class Shaggyanddistances {
    public int solve(int[] A) {
        int n = A.length;
        if (n == 0) {
            return -1;
        }
        Map<Integer, Integer> lastSeen = new HashMap<>();
        int minDist = n+1;
        for (int i = 0; i < n; i++) {
            int num = A[i];
            if (lastSeen.containsKey(num)) {
                int dist = i - lastSeen.get(num);
                minDist = Math.min(minDist, dist);
            }
            lastSeen.put(num, i);
        }
        return (minDist < n+1) ? minDist : -1;
    }

    public static void main(String[] args) {
        int [] A ={7, 1, 3, 4, 1, 7};
        System.out.println(new Shaggyanddistances().solve(A));
    }
}
