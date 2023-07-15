package com.gfg.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BottomViewBT {
	
	ArrayList<Integer> bottomView(Node root){
		Queue<Pair> q = new LinkedList<>();
		Map<Integer, Integer> map = new HashMap<>();
		q.offer(new Pair(root, 0));
		int minHd = Integer.MAX_VALUE;
		int maxHd = Integer.MIN_VALUE;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i = 0 ; i < size ; i++) {
				Pair p = q.poll();
				map.put(p.hd, p.node.data);
				if(p.hd < minHd) {
					minHd = p.hd;
				}else if(p.hd > maxHd) {
					maxHd = p.hd;
				}
				if(p.node.left != null) {
					q.offer(new Pair(p.node.left, p.hd-1));
				}
				if(p.node.right != null) {
					q.offer(new Pair(p.node.right, p.hd+1));
				}
			}
		}
		ArrayList<Integer> bottomViewList = new ArrayList<>();
		for(int level = minHd ; level <= maxHd ; level++) {
			bottomViewList.add(map.get(level));
		}
		return bottomViewList;
	}
	
	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.left.left = new Node(3);
		root.right = new Node(5);
		root.right.right = new Node(6);
		root.right.right.right = new Node(8);
		root.right.right.left = new Node(7);
		BottomViewBT bt = new BottomViewBT();
		ArrayList<Integer> nodes = bt.bottomView(root);
		for(Integer node : nodes) {
			System.out.print(node + " ");
		}
	}

}
