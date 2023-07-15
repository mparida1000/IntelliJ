package com.scaler.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.scaler.adv.arrays.Solution;

/*
 * Problem Description
Given a binary tree of integers. Find the difference between the sum of nodes at odd level and sum of nodes at even level.

NOTE: Consider the level of root node as 1.
 */
public class OddandEvenLevels {

	public static int solve(TreeNode A) {
		int evenSum = 0;
		int oddSum = 0;
		int lev = 1;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(A);
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int i = 0 ; i < size ; i++) {
				TreeNode curr = queue.poll();
				if(lev % 2 != 0) {
					oddSum += curr.val;
				}else {
					evenSum += curr.val;
				}
				if(curr.left != null) {
					queue.add(curr.left);
				}
				if(curr.right != null) {
					queue.add(curr.right);
				}
			}
			lev++;
		}
		return oddSum - evenSum;
    }
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.left.left = new TreeNode(10);
		root.left.left.left = new TreeNode(11);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		
		root.right.left.left = new TreeNode(16);
		root.right.left.right = new TreeNode(18);
		
		root.right.left.right.right = new TreeNode(20);
		root.right.left.right.right.right = new TreeNode(25);
		
		System.out.println(solve(root));
	}
}
