package com.ds.string;

import java.util.Map;
import java.util.TreeMap;

public class RemoveDupFromString {
	public static void main(String[] args) {
		String s = "samisagoodboy";
		int[] charArr = new int[128];
		Map<Integer,Character> charMap = new TreeMap<Integer,Character>();
		for(int i = 0 ; i < s.length() ; i++) {
			char c = s.charAt(i);
			if(charArr[c] == 0) {
				charArr[c] = i;
			}
		}
		for(int i = 0 ; i < 128 ; i++) {
			if(charArr[i] != 0) {
				charMap.put(charArr[i], (char)i);
			}
			
		}
		StringBuffer sb = new StringBuffer();
		for(Character c : charMap.values()) {
			sb.append(c);
		}
		System.out.println(sb.toString());
		
	}

}
