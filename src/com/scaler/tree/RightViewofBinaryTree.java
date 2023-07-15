package com.scaler.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RightViewofBinaryTree {
	
	public static ArrayList<Integer> solve(TreeNode A) {
		return rightViewOfBT(A);
    }
	
	public static ArrayList<Integer> rightViewOfBT(TreeNode root){ 
		Queue<TreeNode> queue = new LinkedList<>();
		if(root == null) return null;
		queue.add(root);
		ArrayList<Integer> rightNodeList = new ArrayList<>();
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int i = 0 ; i < size ; i++) {
				TreeNode curr = queue.poll();
				if(i == size - 1) {
					rightNodeList.add(curr.val);
				}
				if(curr.left != null)queue.add(curr.left);
				if(curr.right != null)queue.add(curr.right);
			}
		}
		return rightNodeList;
		
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(4);
		root.right.left.right = new TreeNode(6);
		ArrayList<Integer> rightNodeList = rightViewOfBT(root);
		for(Integer i : rightNodeList) {
			System.out.print(i + " ");
		}
		
	}

}
