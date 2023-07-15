package com.scaler.tree;
/*
 * Problem Description
Given a binary tree A. Check whether it is possible to partition the tree to two trees which have equal 
sum of values after removing exactly one edge on the original tree.
Problem Constraints
1 <= size of tree <= 100000
0 <= value of node <= 109
 */
public class EqualTreePartition {
	private boolean isEqPartitionPossible = false;
	public int solve(TreeNode A) {
        long treeSum = allNodesSum(A);
		partitionSum(A, treeSum);
		if(isEqPartitionPossible) return 1;
		return 0;
    }
	
    private long allNodesSum(TreeNode root) {
		if(root == null) return 0;
		long lstSum = allNodesSum(root.left);
		long rstSum = allNodesSum(root.right);
		return root.val +  lstSum + rstSum;
	}
	
	private long partitionSum(TreeNode root, long treeSum) {
		if(root == null) return 0;
		long lstSum = partitionSum(root.left, treeSum);
		long rstSum = partitionSum(root.right, treeSum);
		if(lstSum == treeSum/2 ||rstSum == treeSum/2) {
			isEqPartitionPossible = true;
		}
		return root.val + lstSum + rstSum;
	}
	
	public static void main(String[] args) {
		EqualTreePartition ep = new EqualTreePartition();
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.left.left = new TreeNode(2);
		root.left.right = new TreeNode(6);
		root.left.right.right = new TreeNode(4);
		root.left.right.left = new TreeNode(3);
		root.left.right.left.right = new TreeNode(1);		
		root.right = new TreeNode(7);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(6);
		System.out.println(ep.solve(root));
	}

}
