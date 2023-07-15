package com.scaler.stack;

import java.util.Stack;

public class InficToPostfix {
	public static String solve(String A) {
		Stack<Character> stack = new Stack<>();
		StringBuilder postfix = new StringBuilder();
		for(int i = 0 ; i < A.length() ; i++) {
			char c = A.charAt(i);
			if(Character.isLetterOrDigit(c)) {
				postfix.append(c);
			}else if (c == '(') {
				stack.push(c);
			}else if(c == ')') {
				while(!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
				}
				stack.pop();
			}else {
				while(!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
					postfix.append(stack.pop());
				}
				stack.push(c);
			}
		}
		while(!stack.isEmpty()) {
            if(stack.peek() == '(') {
                return "Invalid Expression";
            }
			postfix.append(stack.pop());
		}
		return postfix.toString();
    }
	
	static int precedence(char c) {
		switch(c){
		case '^':
			return 3;
		case '*':
		case '/':
			return 2;
		case '+':
		case '-':
			return 1;
		default:
			return 0;
		}
	}

	public static void main(String[] args) {
		String A = "7/8-8*8-(4/8-8*2)+5";
		System.out.println(solve(A));
	}
}
