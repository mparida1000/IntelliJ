package com.scaler.tree;

public class Diam {
	int dia = Integer.MIN_VALUE;
	 public int solve(TreeNode A) {
         diaTree(A);
         return dia;
    }
    public int diaTree(TreeNode root){
        if(root == null) return 0;
        int left = diaTree(root.left);
        int right = diaTree(root.right);
        dia = Math.max(dia, 1+left+right);
        return Math.max(left, right)+1;
    }
    
    public static void main(String[] args) {
    	TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		Diam d = new Diam();
		System.out.println(d.solve(root));
	}
}
