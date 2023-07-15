package com.scaler.stack;

import java.util.Stack;

/*
 * Problem Description
Given a stack of integers A, sort it using another stack.
Return the array of integers after sorting the stack using another stack.
Problem Constraints
1 <= |A| <= 5000
0 <= A[i] <= 109
 */

public class Sortstackusinganotherstack {

	public static int[] solve(int[] A) {
		Stack<Integer> stack1 = new Stack<>();
		Stack<Integer> stack2 = new Stack<>();
		int n = A.length;
		for(int  i = 0 ;  i < n ; i++) {
			stack1.push(A[i]);
		}
		
		while(!stack1.isEmpty()) {
			int temp = stack1.pop();
			while(!stack2.isEmpty() && stack2.peek() > temp) {
				stack1.push(stack2.pop());
			}
			stack2.push(temp);
		}
		int [] op = new int [n];
		for(int i = n - 1 ; i >= 0 ; i--) {
			op[i] = stack2.pop();
		}

		return op;
    }
	
	public static void main(String[] args) {
		int [] A = {9,7,6,10,58,1,13};
		int [] op = solve(A);
		for(Integer i : op) {
			System.out.print(i + " ");
		}
	}
}
