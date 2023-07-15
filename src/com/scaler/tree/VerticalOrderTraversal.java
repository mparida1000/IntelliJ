package com.scaler.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/*
 * Problem Description
Given a binary tree, return a 2-D array with vertical order traversal of it. Go through the example and image for more details.


NOTE: If 2 Tree Nodes shares the same vertical level then the one with lesser depth will come first.



Problem Constraints
0 <= number of nodes <= 105
 */
public class VerticalOrderTraversal {
	
	public static ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode A) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		Queue<Pair> queue = new LinkedList<>();
		Map<Integer, ArrayList<Integer>> map = new TreeMap<>();
		Pair p1 = new Pair(A, 0);
		queue.add(p1);
		while(!queue.isEmpty()) {
			Pair pair = queue.poll();
			if(!map.containsKey(pair.hd)) {
				ArrayList<Integer> levList = new ArrayList<>();
				levList.add(pair.node.val);
				map.put(pair.hd, levList);
			}else {
				map.get(pair.hd).add(pair.node.val);
			}
			if(pair.node.left != null) {
				queue.add(new Pair(pair.node.left, pair.hd-1));
			}
			if(pair.node.right != null) {
				queue.add(new Pair(pair.node.right, pair.hd+1));
			}
		}
		for(Integer key : map.keySet()) {
			result.add(map.get(key));
		}
		return result;
    }
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(5);
		root.left.left.right = new TreeNode(7);
		root.right = new TreeNode(4);
		root.right.right = new TreeNode(8);
		root.right.right.left = new TreeNode(3);
		root.right.right.left.left = new TreeNode(1);
		ArrayList<ArrayList<Integer>> res = verticalOrderTraversal(root);
		for(ArrayList<Integer> al : res) {
			for(Integer i :  al) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
	

}

class Pair{
	TreeNode node;
	int hd;
	public Pair(TreeNode node, int hd) {
		this.node = node;
		this.hd = hd;
	}
}
