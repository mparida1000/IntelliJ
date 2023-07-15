package com.scaler.string;
public class Checkanagrams {
	public static int solve(String A, String B) {
		if(A.length() != B.length()) return 0;
		int[] charArr = new int[26];
		int n = A.length();
		for(int i = 0 ; i < n ; i++) {
			int c1 = A.charAt(i);
			charArr[c1-97] = charArr[c1-97]+1;
		}
		for(int i = 0 ; i < n ; i++) {
			int c = B.charAt(i);
			if(charArr[c-97] > 0) {
				charArr[c-97] = charArr[c-97]-1;
			}else {
				return 0;
			}
		}
		return 1;
    }
	
	public static void main(String[] args) {
		System.out.println(solve("zyletsyqoideoxtaj", "lrksykjdbkprotthi"));
	}

}
