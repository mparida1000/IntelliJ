package com.scaler.tree;
/*
 * Problem Description
You are given a binary tree represented by root A.

Assume a BST is defined as follows:

1) The left subtree of a node contains only nodes with keys less than the node's key.

2) The right subtree of a node contains only nodes with keys greater than the node's key.

3) Both the left and right subtrees must also be binary search trees.
 */
public class ValidBT {
	public static int isValidBST(TreeNode A) {
		if(isValidBST(A, Integer.MIN_VALUE, Integer.MAX_VALUE))return 1;
		return 0;
    }
	
	public static boolean isValidBST(TreeNode root, int min, int max) { 
		if(root == null) {
			return true;
		}
		if(root.val >= min && root.val <= max) {
			return (isValidBST(root.left, min, root.val-1) && isValidBST(root.right, root.val+1, max));
		}else {
			return false;
		}
    }
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(8);
		root.right = new TreeNode(15);
		root.left.left = new TreeNode(7);
		root.left.right = new TreeNode(9);
		root.right.left = new TreeNode(14);
		root.right.right = new TreeNode(16);
		System.out.println(isValidBST(root));
	}
	
}
