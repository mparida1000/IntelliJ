package com.scaler.stack;

import java.util.Stack;

public class EvaluateExpression {
	
	public static int evalRPN(String[] A) {
		Stack<Integer> stack = new Stack<>();
		for(String s : A) {
			if(isOperator(s)) {
				int operand2 = stack.pop();
				int operand1 = stack.pop();
				int result = performOperation(operand1, operand2, s);
				stack.push(result);
			}else {
				stack.push(Integer.parseInt(s));
			}
		}
		return stack.pop();
    }
	
	static int performOperation(int operand1, int operand2, String operator) {
		switch (operator) {
		case "+":
			return operand1 + operand2;
		case "-":
			return operand1 - operand2;
		case "/":
			return operand1 / operand2;
		case "*":
			return operand1 * operand2;
		default:
			throw new IllegalArgumentException("Unexpected value: " + operator);
		}
	}
	
	static boolean isOperator(String s) {
		if(s.equals("/") || s.equals("*") || s.equals("+") || s.equals("-")) return true;
		return false;
	}
	
	public static void main(String[] args) {
		String [] A = {"2","8","*","3","/","4","-","5","2","/","+"};
		System.out.println(evalRPN(A));
	}

}
