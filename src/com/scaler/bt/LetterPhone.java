package com.scaler.bt;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * Given a digit string A, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.



The digit 0 maps to 0 itself. The digit 1 maps to 1 itself.

NOTE: Make sure the returned strings are lexicographically sorted.
Problem Constraints
1 <= |A| <= 10
 * @author mp1863
 *
 */
public class LetterPhone {
	ArrayList<String>result;
	String[] mapping = new String[]{"0","1","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
	public ArrayList<String> letterCombinations(String A) {
		if(A.startsWith("0") || A.startsWith("1")) return new ArrayList<>(Arrays.asList(A));
		result = new ArrayList<>();
        if(A.length() == 0) return result;
        dfs(0,A,new StringBuilder());
        return result;
    }
	
	void dfs(int length, String input, StringBuilder sb) {
		if(length == input.length()) {
			result.add(sb.toString());
			return;
		}
		char c  = input.charAt(length);
		String str = mapping[c-'0'];
		for(char ch : str.toCharArray()) {
			sb.append(ch);
			dfs(length+1,input, sb);
			sb.deleteCharAt(sb.length()-1);
		}		
	}
	
	public static void main(String[] args) {
		String s = "0";
		ArrayList<String> res = new LetterPhone().letterCombinations(s);
		for(String l : res) {
			System.out.print(l + " ");
		}
	}
}
