package com.scaler.tree;
/*
 * Given a binary search tree represented by root A, write a function to find the Bth smallest element in the tree.
Problem Constraints
1 <= Number of nodes in binary tree <= 100000
0 <= node values <= 10^9
 */

public class KthSmallestElementInBST {
	private int  kthNode = 0;
	private int counter = 0;
	public int kthsmallest(TreeNode A, int B) {
		inOrder(A, B);
		return kthNode;
    }
	
	public void inOrder(TreeNode root, int k) {
		if(root == null) return;
		inOrder(root.left, k);
		counter++;
		if(counter == k) {
			kthNode = root.val;
		}
		inOrder(root.right, k);
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(8);
		root.right = new TreeNode(15);
		root.left.left = new TreeNode(7);
		root.left.right = new TreeNode(9);
		root.right.left = new TreeNode(14);
		root.right.right = new TreeNode(16);
		KthSmallestElementInBST ksm = new KthSmallestElementInBST();
		System.out.println(ksm.kthsmallest(root, 3));
	}
	
}
