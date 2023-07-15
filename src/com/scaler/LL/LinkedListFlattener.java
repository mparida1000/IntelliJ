package com.scaler.LL;

public class LinkedListFlattener {
	Node head;

    public Node flatten(Node root) {
        if (root == null || root.right == null)
            return root;
        
        root.right = flatten(root.right);
        root = merge(root, root.right);
        return root;
    }

    private Node merge(Node a, Node b) {
        if (a == null)
            return b;
        if (b == null)
            return a;
        
        Node result;
        if (a.data < b.data) {
            result = a;
            result.down = merge(a.down, b);
        } else {
            result = b;
            result.down = merge(a, b.down);
        }
        return result;
    }
 
    public static void main(String[] args) {
    	Node head = new Node(1);
    	head.down = new Node(2);
    	head.down.down = new Node(10);
    	head.down.down.down = new Node(11);

    	head.right = new Node(3);
    	head.right.down = new Node(5);
    	head.right.down.down = new Node(7);
    	head.right.down.down.down = new Node(8);

    	head.right.right = new Node(4);
    	head.right.right.down = new Node(6);
    	head.right.right.down.down = new Node(9);

    	LinkedListFlattener flattener = new LinkedListFlattener();
    	Node result = flattener.flatten(head);

    	// Expected Output:
    	// 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11 -> null
	}
}
class Node {
    int data;
    Node right, down, next;

    Node(int data) {
        this.data = data;
        right = null;
        down = null;
        next = null;
    }
}
