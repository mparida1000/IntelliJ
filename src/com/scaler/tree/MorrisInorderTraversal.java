package com.scaler.tree;

import java.util.ArrayList;

public class MorrisInorderTraversal {

	public ArrayList<Integer> solve(TreeNode A) {
		return morrisInorder(A);
    }
	
	public ArrayList<Integer> morrisInorder(TreeNode root) {
		TreeNode curr = root;
		ArrayList<Integer> list = new ArrayList<>();
		while(curr != null) {
			if(curr.left == null) {
				list.add(curr.val);
				curr = curr.right;
			}else {
				TreeNode temp = curr.left;
				while(temp.right != null && temp.right != curr) {
					temp = temp.right;
				}
				if(temp.right == null) {
					temp.right = curr;
					curr = curr.left;
				}else {
					temp.right = null;
					list.add(curr.val);
					curr = curr.right;
				}
			}
		}
		return list;
	}
}
