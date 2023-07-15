package com.scaler.bt;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Given an array of candidate numbers A and a target number B, find all unique combinations in A where the candidate numbers sums to B.
The same repeated number may be chosen from A unlimited number of times.
Note:
1) All numbers (including target) will be positive integers.
2) Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
3) The combinations themselves must be sorted in ascending order.
4) CombinationA > CombinationB iff (a1 > b1) OR (a1 = b1 AND a2 > b2) OR ... (a1 = b1 AND a2 = b2 AND ... ai = bi AND ai+1 > bi+1)
5) The solution set must not contain duplicate combinations.

Problem Constraints
1 <= |A| <= 20
1 <= A[i] <= 50
1 <= B <= 500
 * @author mp1863
 *
 */
public class CombinationSum {

	public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> A, int B) {
        Collections.sort(A); // sort the input array
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> current = new ArrayList<>();
        backtrack(result, current, A, B, 0);
        return result;
    }
    private void backtrack(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> current, ArrayList<Integer> A, int remaining, int start) {
        if (remaining == 0) { // if the remaining target is reached, add the current combination to the result
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = start; i < A.size() && A.get(i) <= remaining; i++) {
            if (i > start && A.get(i) == A.get(i-1)) {
            	continue; // skip duplicates to avoid duplicate combinations
            }
            current.add(A.get(i));
            backtrack(result, current, A, remaining - A.get(i), i); // use i instead of i+1 to allow reusing the same element
            current.remove(current.size()-1);
        }
    }
    
    public static void main(String[] args) {
    	ArrayList<Integer> A = new ArrayList<>();
    	A.add(2);
    	A.add(2);
    	A.add(3);
    	A.add(6);
    	A.add(7);
    	
    	ArrayList<ArrayList<Integer>> op = new CombinationSum().combinationSum(A, 7);
    	for(ArrayList<Integer> al : op) {
    		for(Integer i : al) {
    			System.out.print(i +" ");
    		}
    		System.out.println();
    	}
	}
}
