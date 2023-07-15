package com.scaler.heap;

import java.util.ArrayList;

public class PalindromePartitioning {
	public ArrayList<ArrayList<String>> partition(String a) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), a, 0);
        return result;
	}
    private void backtrack(ArrayList<ArrayList<String>> result, ArrayList<String> current, String A, int start) {
        if (start == A.length()) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = start; i < A.length(); i++) {
            String substring = A.substring(start, i + 1);
            if (isPalindrome(substring)) {
                current.add(substring);
                backtrack(result, current, A, i + 1);
                current.remove(current.size() - 1);
            }
        }
    }
    
    private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    public static void main(String[] args) {
    	ArrayList<ArrayList<String>> op = new PalindromePartitioning().partition("cotton");
    	for(ArrayList<String> al : op) {
    		for(String i : al) {
    			System.out.print(i +" ");
    		}
    		System.out.println();
    	}
	}
}
