package com.scaler.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class TopViewofBinaryTree {
	
	public static ArrayList<Integer> solve(TreeNode A) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Queue<Pair> queue = new LinkedList<>();
		Map<Integer, Integer> map = new TreeMap<>();
		Pair p1 = new Pair(A, 0);
		queue.add(p1);
		while(!queue.isEmpty()) {
			Pair pair = queue.poll();
			if(!map.containsKey(pair.hd)) {
				map.put(pair.hd, pair.node.val);
			}
			if(pair.node.left != null) {
				queue.add(new Pair(pair.node.left, pair.hd-1));
			}
			if(pair.node.right != null) {
				queue.add(new Pair(pair.node.right, pair.hd+1));
			}
		}
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			result.add(entry.getValue());
		}
		return result;
    }
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.right = new TreeNode(5);
		root.left.left = new TreeNode(4);
		root.left.left.left = new TreeNode(8);
		root.right = new TreeNode(3);
		root.right.right = new TreeNode(7);
		root.right.left = new TreeNode(5);
		ArrayList<Integer> result = solve(root);
		for(Integer i : result) {
			System.out.print(i + " ");
		}
	}

}
