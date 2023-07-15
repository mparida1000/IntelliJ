package com.gfg.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Merge2BST {

	public List<Integer> merge(Node root1,Node root2){
		Stack<Node> s1 = new Stack();
		Stack<Node> s2 = new Stack();
		List<Integer> nodes = new ArrayList<>();
		while(!s1.isEmpty() || !s2.isEmpty() || root1 != null || root2 != null) {
			while(root1 != null) {
				s1.push(root1);
				root1 = root1.left;
			}
			while(root2 != null) {
				s2.push(root2);
				root2 = root2.left;
			}
			if(s2.isEmpty() || (!s1.isEmpty()&& s1.peek().data < s2.peek().data)) {
				root1 = s1.pop();
				nodes.add(root1.data);
				root1 = root1.right;
			}else {
				root2 = s2.pop();
				nodes.add(root2.data);
				root2 = root2.right;
			}
		}
		return nodes;
	}
	public static void main(String[] args) {
		Node root1 = new Node(3);
		root1.left = new Node(1);
		root1.right = new Node(5);
		Node root2 = new Node(4);
		root2.left = new Node(2);
		root2.right = new Node(6);
		Merge2BST mb = new Merge2BST();
		List<Integer> nodes = new ArrayList<>();
		nodes = mb.merge(root1, root2);
		for(Integer node : nodes) {
			System.out.print(node + " ");
		}
	}
}
