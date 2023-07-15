package com.scaler.tree;

public class SearchinBST {
	public static int solve(TreeNode A, int B) {
		if(searchInBT(A, B)) return 1;
		return 0;
    }
	
	public static boolean searchInBT(TreeNode root, int B) {
		TreeNode curr = root;
		if(curr == null) {
			return false;
		}
		while(curr != null) {
			if(curr.val == B)return true;
			if(curr.val < B) {
				curr = curr.right;
			}else {
				curr = curr.left;
			}
		}
		return false;
    }
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(8);
		root.right = new TreeNode(15);
		root.left.left = new TreeNode(7);
		root.left.right = new TreeNode(9);
		root.right.left = new TreeNode(14);
		root.right.right = new TreeNode(16);
		System.out.println(solve(root, 71));
	}
}
