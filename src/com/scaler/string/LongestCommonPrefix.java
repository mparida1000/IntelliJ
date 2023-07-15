package com.scaler.string;

public class LongestCommonPrefix {

	public static String longestCommonPrefix(String[] A) {
		int n = A.length; 
		String pref = A[0];
		for(int i = 1 ; i < n ; i++) {
			String s = A[i];
			int k = 0;
			StringBuilder newPref = new StringBuilder();
			while(k < s.length() && k < pref.length()) {
				if(s.charAt(k) == pref.charAt(k)) {
					newPref.append(s.charAt(k));
					k++;
				}else break;
			}
			pref = newPref.toString() != "" ? newPref.toString() : pref;
		}
		return pref;
    }
	
	public static void main(String[] args) {
		String [] arr = {"abcdefgh", "abefghijk", "abcefgh"};
		System.out.println(longestCommonPrefix(arr));

	}
}
