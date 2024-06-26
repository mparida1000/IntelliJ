package com.scaler.tree;


public class BinaryTreeFromInorderAndPostorder {

	public static TreeNode buildTree(int[] A, int[] B) {
		return constructBT(0, A.length-1, B.length-1, A, B);
    }
	
	public static TreeNode constructBT(int start, int end, int rootPos, int [] A, int [] B) {
		if(start > end) {
			return null;
		}
		int index = getIndex(A, start, end, B[rootPos]);
		TreeNode root = new TreeNode(B[rootPos]);
		root.left = constructBT(start, index - 1, rootPos - (end - index) -1, A, B);
		root.right = constructBT(index + 1, end, rootPos - 1, A, B);
		return root;
	}
	
	public static int getIndex(int [] A, int start, int end, int value) {
		for(int i = start ; i <= end ; i++) {
			if(A[i] == value) return i;
		}
		return -1;
	}
	
	static void inOrder(TreeNode root) {
		if(root==null) return;
		inOrder(root.left);
		System.out.print(root.val + " ");
		inOrder(root.right);
	}
	

	public static void main(String[] args) {
		int [] A = {4,2,5,1,6,3,7};
		int [] B = {4,5,2,6,7,3,1};
		TreeNode root = buildTree(A, B);
		inOrder(root);
	}
}
