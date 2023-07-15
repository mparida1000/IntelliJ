package com.scaler.bt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/**
 * Given an integer array A of size N denoting collection of numbers , return all possible permutations.

NOTE:

No two entries in the permutation sequence should be the same.
For the purpose of this problem, assume that all the numbers in the collection are unique.
Return the answer in any order
WARNING: DO NOT USE LIBRARY FUNCTION FOR GENERATING PERMUTATIONS. 
Example : next_permutations in C++ / itertools.permutations in python.
If you do, we will disqualify your submission retroactively and give you penalty points.
Problem Constraints
1 <= N <= 9
 * @author mp1863
 *
 */
public class Permutations {

	public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> A) {
		ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
		ArrayList<Integer> list = new ArrayList<>();
		HashSet<Integer> set = new HashSet<>();
		int n = A.size();
		permutations(A,set,n,list,ans);
		return ans;
    }
	void permutations(ArrayList<Integer> A, HashSet<Integer> set, int n, 
			ArrayList<Integer> list, ArrayList<ArrayList<Integer>> ans) {
		System.out.println("Entry");
		if(set.size() == n) {
			ans.add(new ArrayList<>(list));
			return;
		}
		for(int i = 0 ; i < n ; i++) {
			System.out.println(i);
			if(!set.contains(A.get(i))) {
				list.add(A.get(i));
				set.add(A.get(i));
				permutations(A,set,n,list,ans);
				list.remove(list.size()-1);
				set.remove(A.get(i));
			}
		}
		
	}
	
	public static void main(String[] args) {
		ArrayList <Integer> al = new ArrayList<Integer>() {
			{
				add(1);
				add(2);
				add(3);
			}
		};
		Permutations perm = new Permutations();
		ArrayList<ArrayList<Integer>> op = perm.permute(al);
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
