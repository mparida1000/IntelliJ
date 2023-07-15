package com.scaler.bt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * Problem Description
Given an array of size N of candidate numbers A and a target number B. Return all unique combinations in A where the candidate numbers sums to B.
Each number in A may only be used once in the combination.
Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
Warning:
DO NOT USE LIBRARY FUNCTION FOR GENERATING COMBINATIONS.
Example : itertools.combinations in python. If you do, we will disqualify your submission and give you penalty points.
Problem Constraints
1 <= N <= 20
 * @author mp1863
 *
 */
public class CombinationSumII {

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
            backtrack(result, current, A, remaining - A.get(i), i+1); // use i instead of i+1 to allow reusing the same element
            current.remove(current.size()-1);
        }
    }
	
	public static void main(String[] args) {
		Integer [] A1 = {10, 1, 2, 7, 6, 1, 5};
		ArrayList<Integer> A = new ArrayList<>(){{
            add(10);
            add(1);
            add(2);
            add(7);
            add(6);
            add(1);
            add(5);
        }};
		ArrayList<ArrayList<Integer>> op = new CombinationSumII().combinationSum(A, 8);
		for(ArrayList<Integer> al : op) {
    		for(Integer i : al) {
    			System.out.print(i +" ");
    		}
    		System.out.println();
    	}
	}
}
