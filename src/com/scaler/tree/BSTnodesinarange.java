package com.scaler.tree;
/*
 * Problem Description
Given a binary search tree of integers. You are given a range B and C.
Return the count of the number of nodes that lie in the given range.
Problem Constraints
1 <= Number of nodes in binary tree <= 100000
0 <= B < = C <= 109
Input Format
First argument is a root node of the binary tree, A.
Second argument is an integer B.
Third argument is an integer C.
 */

public class BSTnodesinarange {
	public static int count = 0;
	public static int solve(TreeNode A, int B, int C) {
		return BSTNodeInRange(A, B, C);
    }
	
	public static int BSTNodeInRange (TreeNode root, int B, int C) {
		if(root == null)return 0;
		BSTNodeInRange(root.left, B, C);
		BSTNodeInRange(root.right, B, C);
		if(root.val >= B && root.val <=C) {
			count++;
		}
		return count;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(15);
		root.left = new TreeNode(12);
		root.right = new TreeNode(20);
		root.left.left = new TreeNode(10);
		root.left.right = new TreeNode(14);
		root.left.left.left = new TreeNode(8);
		
		root.right.left = new TreeNode(16);
		root.right.right = new TreeNode(27);
		System.out.println(solve(root, 20, 27));
	}

}
