package com.gfg.tree;

public class MaximumPathSumFromAnyNode {
	int max = Integer.MIN_VALUE;
	int findMaxSum(Node node ) {
        maxSum(node);
        return max;
    }
	int maxSum(Node root) {
		if(root == null) {
			return 0;
		}
		int l = Math.max(maxSum(root.left), 0);
		System.out.print(l+",");
		int r = Math.max(maxSum(root.right), 0);
		System.out.print(r+",");		
		max = Math.max(max, r+l+root.data);
		System.err.print("M"+max+",");
		return Math.max(l, r)+root.data;
	}
	public static void main(String[] args) {
		/*Node root = new Node(10);
		root.left = new Node(2);
		root.left.left = new Node(20);
		root.left.right = new Node(1);
		root.right = new Node(-25);
		root.right.left = new Node(3);
		root.right.right = new Node(4);
		root.right.right.left = new Node(41);
		root.right.right.right = new Node(40);
		root.left.right.right = new Node(25);*/
		Node root = new Node(10);
		root.left = new Node(2);
		root.left.left = new Node(20);
		root.left.right = new Node(1);
		root.right = new Node(-25);
		root.right.left = new Node(3);
		root.right.right = new Node(4);
		MaximumPathSumFromAnyNode mas = new MaximumPathSumFromAnyNode();
		System.out.println(mas.findMaxSum(root));
	}
}

class Node{
    int data;
    Node left,right;
    Node(int d){
        data=d;
        left=right=null;
    }
}
