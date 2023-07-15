package com.scaler.tree;

public class SortedArrayToBalancedBST {
	
	public static TreeNode sortedArrayToBST(final int[] A) {
		return buildBT(A, 0, A.length - 1);
    }
	
	public static TreeNode buildBT(int [] arr, int s, int e) {
		if(s > e) {
			return null;
		}
		int  mid = (s+e)/2;
		TreeNode root = new TreeNode(arr[mid]);
		root.left = buildBT(arr, s, mid - 1);
		root.right = buildBT(arr, mid + 1, e);
		return root;
	}
	
	public static void main(String[] args) {
		int [] A = {1,3,5,8,10,15,18,20};
		TreeNode root = sortedArrayToBST(A);
		inOrder(root);
		int t = 0;
		System.out.println(t++);
		System.out.println(t);
	}
	
	static void inOrder(TreeNode root) {
		if(root==null) return;
		inOrder(root.left);
		System.out.print(root.val+" ");
		inOrder(root.right);
	}

}
