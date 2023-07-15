package com.scaler.tree;
/*
 * Problem Description
Two elements of a binary search tree (BST), represented by root A are swapped by mistake. Tell us the 2 
values swapping which the tree will be restored.

A solution using O(n) space is pretty straightforward. Could you devise a constant space solution?
 */

public class RecoverBinarySearchTree {
	public static void recoverTree(TreeNode root) {
		TreeNode current = root;
	    while (current != null) {
	        if (current.left == null) {
	            System.out.print(current.val + " ");
	            current = current.right;
	        } else {
	            // find the inorder predecessor of current node
	            TreeNode predecessor = current.left;
	            while (predecessor.right != null && predecessor.right != current) {
	                predecessor = predecessor.right;
	            }
	            
	            if (predecessor.right == null) {
	                // set the right child of inorder predecessor to the current node
	                predecessor.right = current;
	                current = current.left;
	            } else {
	                // restore the tree structure
	                predecessor.right = null;
	                System.out.print(current.val + " ");
	                current = current.right;
	            }
	        }
	    }
    }
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(10);
		
		root.left.left = new TreeNode(4);
		root.left.left.left = new TreeNode(8);
		root.left.right = new TreeNode(5);
		root.left.right.left = new TreeNode(6);
		root.left.right.right = new TreeNode(7);
		
		root.right.right = new TreeNode(12);
		root.right.left = new TreeNode(11);
		recoverTree(root);
	}

}
