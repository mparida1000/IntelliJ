package com.scaler.string;

public class RepeatedCharacter {
	
	static char firstRep(String s) {
		int [] charArr = new int[128];
		boolean found = false;
		char c = '#';
		int firstCharIndex = Integer.MAX_VALUE;
		for(int i = 0 ; i <s.length() ; i++) {
			char p = s.charAt(i);
			charArr[p] = charArr[p]+1;
			if(charArr[p] > 1 && (p-'0') < firstCharIndex) {
				firstCharIndex = p - '0';
				c = p;
			}
		}
		return c;
	}
	
	public static void main(String[] args) {
		System.out.println(firstRep("geeksg"));//geeksforgeeks
	}

}
