package com.scaler.array;
/**
 * You are given a string S, and you have to find all the amazing substrings of S.
 * * An amazing Substring is one that starts with a vowel (a, e, i, o, u, A, E, I, O, U).
 * * Input
 * * Only argument given is string S.
 * Output
 * * Return a single integer X mod 10003, here X is the number of Amazing Substrings in given the string.
 * Constraints
 * * 1 <= length(S) <= 1e6
 * S can have special characters
 */
public class AmazingSubArray {
	
	public static int solve(String A) {
        final String allVowels= "AEIOUaeiou";
        int n = A.length();
        int vowelCount = 0;
        int count = 0;
        for(int i = 0 ; i < n ; i++){
            if(allVowels.contains(String.valueOf(A.charAt(i)))){
                vowelCount++;
                count+=vowelCount;
            }else{
              if(vowelCount > 0) {
            	  count+=vowelCount;
              }
            }
        }
        return count;
    }
	
	public static void main(String[] args) {
		String A = "ABECAD";
		System.out.println(solve(A));
	}

}
