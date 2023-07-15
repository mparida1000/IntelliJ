package com.scaler.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LevelOrder {
	public static ArrayList<ArrayList<Integer>> solve(TreeNode A) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(A);
		queue.add(null);
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> levelList = new ArrayList<>();;
		while(!queue.isEmpty()) {
			TreeNode curr = queue.poll();
			if(curr == null) {
				if(levelList != null && levelList.size() > 0) {
					result.add(levelList);
				}	
				if(queue.isEmpty())return result;
				levelList = new ArrayList<>();
				queue.add(null);
			}else {
				levelList.add(curr.val);
				if(curr.left!=null) {
					queue.add(curr.left);
				}
				if(curr.right!=null) {
					queue.add(curr.right);
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		
		ArrayList<ArrayList<Integer>> result = solve(root);
		for(ArrayList<Integer> a : result) {
			for(Integer i : a) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
		
		
	}
}
