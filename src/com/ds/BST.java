package com.ds;

import java.util.Scanner;

public class BST {
	
	
	static Scanner sc = null;
	static boolean searchNode(Node root, int x) {
		if(root == null) return false;
		if(root.data == x) return true;
		if(root.data > x) {
			return searchNode(root.left, x);
		}
		return searchNode(root.right, x);
	}
	
	static Node insert(Node root, int x) {
		if(root == null) {
			return new Node(x);
		}
		if(root.data > x) {
			root.left = insert(root.left, x);
		}else if(root.data < x) {
			root.right = insert(root.right, x);
		}
		return root;
	}
	
	static Node insertItr(Node root, int x){
		Node newNode = new Node(x);
		Node parent = null;
		Node curr = root;
		if(root == null){
			return newNode;
		}
		while(curr != null) {
			parent = curr;
			if(x < curr.data) {
				curr = curr.left;
			}else if(x > curr.data) {
				curr = curr.right;
			}
		}
		if(parent == null) 
		parent = newNode;
		if(x < parent.data) {
			parent.left = newNode;
		}else parent.right = newNode;
		return parent;
		
	}
	
	static Node delete(Node root, int x) {
		if(root == null) return root;
		if(x < root.data) {
			root.left = delete(root.left, x);
		}
		else if (x > root.data){
			root.right = delete(root.right, x);
			
		}
		else {
			if(root.left == null) {
			return root.right;
		}else if(root.right == null) {
			return root.left;
		}
			root.data = minValue(root.right);
			root.right = delete(root.right, root.data);
		}
		return root;
	}
	static int minValue(Node root) {
		int minV = root.data;
		while(root.left != null) {
			minV = root.left.data;
			root = root.left;
		}
		
		return minV;
	}
	
	static Node createTreeFromScanner() {
		sc = new Scanner(System.in);
		System.out.println("Enter data..");
		int data = sc.nextInt();
		if(data == -1) return null;
		Node root = new Node(data);
		System.out.println("Enter left for Node "+data);
		root.left = createTreeFromScanner();
		System.out.println("Enter right for Node "+data);
		root.right = createTreeFromScanner();
		return root;
	}
	
	int Floor(Node root, int x) {
		int ans = Integer.MAX_VALUE;
			if(x == root.data) {
				return root.data;
			}
			while(root != null) {
				if(x < root.data) {
					root = root.left;
				}else {
					ans = root.data;
					root = root.right;
				}
			}
		return ans;
	}
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		Node root = createTreeFromScanner();
		System.out.println("Print Tree \n");
		Tree.preOrder(root);
		
		/*Node afterInsert = insertItr(root, 10);
		System.out.println("After Insert \n");
		Tree.preOrder(root);
		
        boolean isFound = searchNode(root, 10);
        System.out.println(isFound);
        
        /** Insert Node **/
        //Node insertedNode = insert(root, 24);
        //Tree.preOrder(insertedNode);
        
        Node delRoot = delete(root, 5);
        
        System.out.println("Print after delete");
        Tree.preOrder(delRoot);
        
	}

}


