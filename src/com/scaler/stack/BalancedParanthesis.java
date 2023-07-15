package com.scaler.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
/*
Problem Description
Given an expression string A, examine whether the pairs and the orders of “{“,”}”, ”(“,”)”, ”[“,”]” are correct in A.
Refer to the examples for more clarity.
Problem Constraints
1 <= |A| <= 100
 */
public class BalancedParanthesis {
	
	public static int solve(String A) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("}", "{");
        map.put(")", "(");
        map.put("]", "[");
        Stack<Character> stack = new Stack<>();

        for(int i = 0 ; i < A.length() ; i++){
            char c = A.charAt(i);
            if(map.containsKey(String.valueOf(c))) {
            	if(!stack.isEmpty()) {
	            	char c1 = stack.pop();
	            	if(!String.valueOf(c1).equals(String.valueOf(map.get(String.valueOf(c))))) {
	            		return 0;
	            	}
            	}else {
            		return 0;
            	}
            }else {
            	stack.push(c);
            }
        }
        if(stack.size() > 0) return 0;
        return 1;
    }
	
	public static void main(String[] args) {
		String A = "))))))))";
		System.out.println(solve(A));
	}

}
