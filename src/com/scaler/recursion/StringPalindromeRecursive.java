package com.scaler.recursion;

public class StringPalindromeRecursive {
	
	public static boolean isPalindrome(String s, int i, int j) { //level
		if((s.charAt(i)) != (s.charAt(j))) {
			return false;
		}
		if(i < j) {
			return isPalindrome(s, i+1, j-1);
		}
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(isPalindrome("", 0, 0));
	}

}
