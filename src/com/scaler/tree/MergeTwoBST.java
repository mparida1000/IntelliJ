package com.scaler.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MergeTwoBST {

	static void mergeTwoBST(Node root1, Node root2)	{
		
	}
	public static void main(String[] args)
    {
        Node root1 = null, root2 = null;
        root1 = new Node(3);
        root1.left = new Node(1);
        root1.right = new Node(5);
        
        root2 = new Node(4);
        root2.left = new Node(2);
        root2.right = new Node(6);
 
        mergeTwoBST(root1, root2);
    }
}
class Node {
	 
    int data;
    Node left;
    Node right;
    public Node(int data)
    {
        // TODO Auto-generated constructor stub
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
