package com.scaler.tree;

public class DeleteanodeinBST {
	
	public static TreeNode solve(TreeNode A, int B) {
		return delete(A, B);
    }
	
	public static TreeNode delete(TreeNode root, int k) {
		if(root == null ) return null;
		if(root.val == k) {
			if(root.left == null && root.right == null) {
				return null;
			}
			if(root.left == null || root.right == null) {
				if(root.left == null) {
					return root.right;
				}else
					return root.left;
			}
			int left_max = max(root.left);
			root.val = left_max;
			root.left = delete(root.left, left_max);
			return root;
		}else if(root.val > k) {
			root.left = delete(root.left, k);
		}else
			root.right = delete(root.right, k);
		return root;
	}
	public static int max(TreeNode root) {
		int max = root.val;
		while(root.right != null) {
			max = root.right.val;
			root= root.right;
		}
		return max;
	}

}
