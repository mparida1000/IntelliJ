package com.ds.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Parenthesis {
	
	public static void main(String[] args) {
		String s = "{}[(){}]([])";
		System.out.println(isValid(s));
		
	}
	
	public static boolean isValid(String expression) {
		Map<Character, Character> exprMap = new HashMap<Character, Character>();
		exprMap.put('}', '{');
		exprMap.put(']', '[');
		exprMap.put(')', '(');
		
		Stack<Character>stack = new Stack<Character>();
		for(int i = 0 ; i < expression.length() ; i ++) {
			char c = expression.charAt(i);
			if(exprMap.containsKey(c)) {
				char top = stack.isEmpty() ? '1' : stack.pop();
				if(top != exprMap.get(c)) {
					return false;
				}
				
			}else {
				stack.push(c);
			}
		}
		System.out.println(stack.size());
		return stack.isEmpty();
	}

}
