package com.scaler.tries;

import java.util.ArrayList;
import java.util.Arrays;

public class SpellingChecker {
	
	public ArrayList<Integer> solve(ArrayList<String> A, ArrayList<String> B) {
		Node root = new Node();
		if(A == null || A.size() == 0)return null;
		for(String word : A) {
			insert(root, word);
		}
		ArrayList<Integer> opList = new ArrayList<>();
		for(String word : B) {
			if(search(root, word))
				opList.add(1);
			else
				opList.add(0);
		}
		return opList;
    }
	public void insert(Node root, String word) {
		Node curr = root;
		int n = word.length();
		for(int i = 0 ; i < n ; i++) {
			int index = word.charAt(i) - 'a';
			if(curr.child[index] == null) {
				curr.child[index] = new Node();
			}
			curr = curr.child[index];
		}
		curr.isEnd = true;
	}
	
	public boolean search(Node root, String word) {
		Node curr = root;
		int n = word.length();
		for(int i = 0 ; i < n ; i++) {
			int index = word.charAt(i) - 'a';
			if(curr.child[index] == null)return false;
			curr = curr.child[index];
		}
		return curr.isEnd;
	}
	
	public static void main(String[] args) {
		SpellingChecker sc = new SpellingChecker();
		ArrayList<String> A = new ArrayList<>(Arrays.asList("hat", "cat", "rat", "sat"));	
		ArrayList<String> B = new ArrayList<>(Arrays.asList("cat", "ball", "sat"));	
		ArrayList<Integer> op = sc.solve(A, B);
		for(Integer i : op) {
			System.out.print(i + " ");
		}
	}

}
