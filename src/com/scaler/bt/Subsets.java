package com.scaler.bt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * Problem Description
Given a set of distinct integers A, return all possible subsets.

NOTE:

Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
Also, the subsets should be sorted in ascending ( lexicographic ) order.
The list is not necessarily sorted.


Problem Constraints
1 <= |A| <= 16
INTMIN <= A[i] <= INTMAX
 * @author mp1863
 *
 */
public class Subsets {

	public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> A) {
		Collections.sort(A);
		ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
		ans.add(new ArrayList<>());
		ArrayList<Integer> list = new ArrayList<>();
		int n = A.size();
		findSubSets(A, 0, n, list, ans);
		return ans;
    }
	
	void findSubSets(ArrayList<Integer> A, int pos, int n, ArrayList<Integer> list, 
			ArrayList<ArrayList<Integer>> ans) {
		if(pos == n) {
			
			return;
		}
		list.add(A.get(pos));
		ans.add(new ArrayList<>(list));
		findSubSets(A, pos+1, n, list, ans);
		list.remove(list.size()-1);
		findSubSets(A, pos+1, n, list, ans);
	}
	
	public static void main(String[] args) {
		ArrayList <Integer> al = new ArrayList<Integer>() {
			{
				add(1);
				add(2);
				//add(3);
			}
		};
		Subsets s = new Subsets();
		ArrayList<ArrayList<Integer>> op = s.subsets(al);
		for(ArrayList<Integer> l : op) {
			System.out.print("[");
			for(Integer i : l) {
				System.out.print(i +" ");
			}
			System.out.print("]");
			System.out.println();
		}
	}
}
