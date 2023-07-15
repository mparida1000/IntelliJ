package com.scaler.tree;

public class FlattenBT {
	TreeNode prev=null;
    public void flatten(TreeNode root) {
    	if (root == null) return;
        
        TreeNode left = root.left;
        TreeNode right = root.right;
        
        root.left = null;
        
        flatten(left);
        flatten(right);
        
        root.right = left;
        TreeNode cur = root;
        while (cur.right != null) {
        	cur = cur.right;
        }
        cur.right = right;
    }
    
    public static void main(String[] args) {
    	TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.left.right = new TreeNode(5);
		root.left.left.right.right = new TreeNode(6);
		root.left.right = new TreeNode(4);

		root.right = new TreeNode(5);
		root.right.right = new TreeNode(6);
		FlattenBT fl = new FlattenBT();
		fl.flatten(root);
	}

}
