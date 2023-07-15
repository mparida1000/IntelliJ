package com.scaler.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/*
 * Problem Description
Given two strings A and B. Each string represents an expression consisting of lowercase English alphabets, '+', '-', '(' and ')'.
The task is to compare them and check if they are similar. If they are identical, return 1 else, return 0.
NOTE: It may be assumed that there are at most 26 operands from ‘a’ to ‘z’, and every operand appears only once.
Problem Constraints
1 <= length of the each String <= 100
 */
public class Checktwobracketexpressions {

	public static int evalRPN(char[] A) {
		Stack<Integer> stack = new Stack<>();
		for(char s : A) {
			if(isOperator(s)) {
				int operand2 = stack.pop();
				int operand1 = stack.pop();
				int result = performOperation(operand1, operand2, s);
				stack.push(result);
			}else {
				stack.push(1);
			}
		}
		return stack.pop();
    }
	
	static int performOperation(int operand1, int operand2, char operator) {
		switch (operator) {
		case '+' :
			return operand1 + operand2;
		case '-' : 
			return operand1 - operand2;
		default:
			throw new IllegalArgumentException("Unexpected value: " + operator);
		}
	}
	
	static boolean isOperator(char c) {
		if(c == '+' || c == '-') return true;
		return false;
	}
	
	public static int areExpressionsSimilar(String A, String B) {
        return evalRPN(A.toCharArray()) == evalRPN(B.toCharArray()) ? 1 : 0;
    }
	
	public static void main(String[] args) {
        String A = "-(-(-(-a+b)-d+c)-q)";
        String B = "a-b-d+c+q";
        String A_ = InficToPostfix.solve(A);
        String B_ = InficToPostfix.solve(B);
        System.out.println(areExpressionsSimilar(A_,B_));
    }
}
