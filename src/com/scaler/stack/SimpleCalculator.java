package com.scaler.stack;

import java.util.Stack;

public class SimpleCalculator {

    public static int calculate(String s) {
        Stack<Integer> operands = new Stack<>(); // To store numbers
        Stack<Character> operators = new Stack<>(); // To store operators and parentheses

        int currentNumber = 0;
        boolean isParsingNumber = false;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                // Build the number
                currentNumber = currentNumber * 10 + (ch - '0');
                isParsingNumber = true;
            } else {
                if (isParsingNumber) {
                    // Push the number to the stack
                    operands.push(currentNumber);
                    currentNumber = 0;
                    isParsingNumber = false;
                }

                if (ch == '(') {
                    operators.push(ch);
                } else if (ch == ')') {
                    // Evaluate until '('
                    while (operators.peek() != '(') {
                        operands.push(evaluate(operands.pop(), operands.pop(), operators.pop()));
                    }
                    operators.pop(); // Pop '('
                } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                    // Process operators based on precedence
                    while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(ch)) {
                        operands.push(evaluate(operands.pop(), operands.pop(), operators.pop()));
                    }
                    operators.push(ch);
                }
            }
        }

        // Push the last number
        if (isParsingNumber) {
            operands.push(currentNumber);
        }

        // Evaluate remaining operators
        while (!operators.isEmpty()) {
            operands.push(evaluate(operands.pop(), operands.pop(), operators.pop()));
        }

        return operands.pop();
    }

    // Helper function to evaluate expressions
    private static int evaluate(int b, int a, char operator) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b; // Integer division
            default: throw new IllegalArgumentException("Invalid operator");
        }
    }

    // Helper function to determine operator precedence
    private static int precedence(char operator) {
        if (operator == '+' || operator == '-') return 1;
        if (operator == '*' || operator == '/') return 2;
        return 0; // '(' has the lowest precedence
    }

    public static void main(String[] args) {
        String expression = "3+(2-1*3)*5+2*(6-4)";
        System.out.println("Result: " + calculate(expression)); // Output: 8
    }
}

