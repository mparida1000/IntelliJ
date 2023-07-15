package com.scaler.tree;

public class MaxSumPathinBinaryTree {
	private static int maxSum = Integer.MIN_VALUE;
	public static int maxPathSum(TreeNode A) {
        maxSum(A);
        return maxSum;
    }

    public static int maxSum(TreeNode root) {
        if(root == null) return 0;
        int left = Math.max(maxSum(root.left),0);
        int right = Math.max(maxSum(root.right), 0);
        int sum = root.val + left + right;
        maxSum = Math.max(maxSum, sum);
        return root.val + Math.max(right, left);
        
    }
    
    public static void main(String[] args) {
    	TreeNode root = new TreeNode(10);
		root.left = new TreeNode(-10);
		root.left.left = new TreeNode(20);
		root.left.right = new TreeNode(-10);
		root.left.right.right = new TreeNode(30);
		
		root.right = new TreeNode(40);
		root.right.right = new TreeNode(-30);
		root.right.right.left = new TreeNode(20);
		root.right.right.left.right = new TreeNode(-10);
		System.out.println(maxPathSum(root));
	}

}
