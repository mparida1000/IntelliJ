package com.scaler.string;

public class LongestPalindromicSubstring {
	
	public static String longestPalindrome(String A) {
		int s = 0, e = 0;
		int currMax = 0;
		for(int i = 0 ; i < A.length() ; i++) {
			int l1 = getLengthByExpanding(i, i, A);
			int l2 = getLengthByExpanding(i, i+1, A);
			int l = Math.max(l1, l2);
			if(l > e - s && l > currMax) {
				s = i - (l -1 )/2;
				e = i + l/2;
				currMax = l;
			}
		}
		return A.substring(s, e+1);
		
    }
	
	public static int getLengthByExpanding(int i, int j , String A) {
		int l = i, r = j;
		while(l >= 0 && r <= A.length()-1 && A.charAt(l) == A.charAt(r)) {
			l--;
			r++;
		}
		return r - l - 1;
	}
	
	public static void main(String[] args) {
		System.out.println(longestPalindrome("aabb"));
	}

}
