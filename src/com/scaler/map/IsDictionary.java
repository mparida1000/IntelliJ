package com.scaler.map;

import java.util.HashMap;
import java.util.Map;
/*
Surprisingly, in an alien language, they also use English lowercase letters, but possibly in a different order.
The order of the alphabet is some permutation of lowercase letters.

Given an array of words A of size N written in the alien language, and the order of the alphabet denoted by string B of size 26,
return 1 if and only if the given words are sorted lexicographically in this alien language else, return 0.

Problem Constraints
1 <= N, length of each word <= 105

Sum of the length of all words <= 2 * 106
Input Format
The first argument is a string array A of size N.

The second argument is a string B of size 26, denoting the order.
 */
public class IsDictionary {
	public static int solve(String[] A, String B) {
		if(B == null || "".equals(B))return 0;
		String ourAlphabets = "abcdefghijklmnopqrstuvwxyz";
        Map<Character, Integer> map = new HashMap();
        Map<Integer, Character> ourMap = new HashMap();
        int n = B.length();
        for(int i = 0 ; i < 26 ; i++) {
        	map.put(B.charAt(i), i+1 );
        	ourMap.put(i+1, ourAlphabets.charAt(i));
        }
        
        for(int i = 0 ; i < A.length ;  i++) {
        	String s = A[i];
        	StringBuffer sb = new StringBuffer();
        	for(int j = 0 ; j < s.length() ; j++) {
        		int pos = map.get(s.charAt(j));
        		sb.append(ourMap.get(pos));
        	}
        	A[i] = sb.toString();
        }
        for(int k = 1 ; k < A.length ; k++) {
        	if(A[k].compareTo(A[k-1]) < 1) {
        		return 0;
        	}
        }
        return 1;
    }
	public static void main(String[] args) {
		
		String [] A = {"ipial", "qjqgt", "vfnue", "vjqfp", "eghva", "ufaeo", "atyqz", "chmxy", "ccvgv", "ghtow"};
		String B = "nbpfhmirzqxsjwdoveuacykltg";
		System.out.println(solve(A, B));
		
		String s1 = "134";
		String s2 = "12";
		System.out.println(s1.compareTo(s2));
	}

}
