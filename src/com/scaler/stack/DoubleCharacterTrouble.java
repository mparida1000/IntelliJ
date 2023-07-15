package com.scaler.stack;

import java.util.Stack;

public class DoubleCharacterTrouble {
	
	public static String solve(String A) {
		Stack<Character> stack = new Stack<>();
		for(int i = 0; i < A.length() ; i++) {
			char c = A.charAt(i);
			if(!stack.isEmpty() && String.valueOf(stack.peek()).equals(String.valueOf(c))) {
				stack.pop();
			}else {
				stack.push(c);
			}
		}
		StringBuilder sb = new StringBuilder();
		for(Character c : stack) {
			sb.append(c);
		}
		return sb.toString();
    }
	public static void main(String[] args) {
		String A = "agyzzipoopejg";
		System.out.println(solve(A));
		System.out.println(2*8/3-4+5/2);
		
	}

}
