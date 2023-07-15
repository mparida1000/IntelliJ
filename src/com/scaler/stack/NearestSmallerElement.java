package com.scaler.stack;

import java.util.Stack;

/*
 * Given an array A, find the nearest smaller element G[i] for every element A[i] in the array such that the element has an index smaller than i.
More formally,
G[i] for an element A[i] = an element A[j] such that
j is maximum possible AND
j < i AND
A[j] < A[i]
Elements for which no smaller element exist, consider the next smaller element as -1.
Problem Constraints
1 <= |A| <= 100000
-1000000000 <= A[i] <= 10000000000

 */

public class NearestSmallerElement {
	
	public static int[] prevSmaller(int[] A) {
		Stack<Integer> stack = new Stack<>();
		int [] op = new int[A.length];
		for(int i = 0 ; i < A.length ; i++) {
			while(!stack.isEmpty() && stack.peek() >= A[i]) {
				stack.pop();
			}
			if(stack.isEmpty()) {
				op[i] = -1;
			}
			if(!stack.isEmpty() && stack.peek() < A[i]) {
				op[i] = stack.peek();
			}
			stack.push(A[i]);
		}
		return op;
    }
	public static int[] aftreSmaller(int[] A) {
		Stack<Integer> stack = new Stack<>();
		int [] op = new int[A.length];
		for(int i = A.length - 1 ; i >= 0 ; i--) {
			while(!stack.isEmpty() && stack.peek() >= A[i]) {
				stack.pop();
			}
			if(stack.isEmpty()) {
				op[i] = -1;
			}
			if(!stack.isEmpty() && stack.peek() < A[i]) {
				op[i] = stack.peek();
			}
			stack.push(A[i]);
		}
		return op;
    }
	public static void main(String[] args) {
		//int [] A = {1, 3, 2, 4, 5, 3, 6};
		int [] A = {39, 27, 11, 4, 24, 32, 32, 1};
		int [] op = prevSmaller(A);
		int [] op1 = aftreSmaller(A);
		for(int i = 0 ; i < op.length ; i++) {
			System.out.print(op[i] + " ");
		}
		System.out.println();
		for(int i = 0 ; i < op1.length ; i++) {
			System.out.print(op1[i] + " ");
		}
		
	}

}
