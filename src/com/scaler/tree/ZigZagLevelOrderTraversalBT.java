package com.scaler.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ZigZagLevelOrderTraversalBT {
	public static ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode A) {
		return zigzagTraversal(A);
    }
	
	public static ArrayList<ArrayList<Integer>> zigzagTraversal(TreeNode root){ 
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		Queue<TreeNode> queue = new LinkedList<>();
		if(root == null) return null;
		queue.add(root);
		ArrayList<Integer> nodeList;
		boolean leftToRight = true;
		while(!queue.isEmpty()) {
			int size = queue.size();
			nodeList = new ArrayList<>();
			for(int i = 0 ; i < size ; i++) {
				TreeNode curr = queue.poll();
				if(leftToRight) {
					nodeList.add(curr.val);
				}else {
					nodeList.add(0, curr.val);
				}
				if(curr.left != null)queue.add(curr.left);
				if(curr.right != null)queue.add(curr.right);
			}
			result.add(nodeList);
			leftToRight = !leftToRight;
		}
		return result;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.left.left = new TreeNode(10);
		root.left.left.left = new TreeNode(11);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		
		root.right.left.left = new TreeNode(16);
		root.right.left.right = new TreeNode(18);
		
		root.right.left.right.right = new TreeNode(20);
		root.right.left.right.right.right = new TreeNode(25);
		
		ArrayList<ArrayList<Integer>> result = zigzagLevelOrder(root) ;
		for(ArrayList<Integer> al : result) {
			for(Integer i :  al) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
		
	}

}
