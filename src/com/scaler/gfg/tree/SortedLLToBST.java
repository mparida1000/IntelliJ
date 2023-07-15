package com.gfg.tree;

public class SortedLLToBST {

	public TNode sortedListToBST(LNode head) {
		return null;
	}
	public static void main(String[] args) {
		LNode node = new LNode(1);
		node.next = new LNode(2);
		node.next.next = new LNode(3);
		node.next.next.next = new LNode(4);
		node.next.next.next.next = new LNode(5);
		node.next.next.next.next.next = new LNode(6);
		node.next.next.next.next.next.next = new LNode(7);
		node.next.next.next.next.next.next.next = new LNode(8);
		SortedLLToBST stb = new SortedLLToBST();
		TNode root = stb.sortedListToBST(node);
		InOrder(root);
	}
	static void InOrder(TNode root) {
		if(root.left!=null) {
			InOrder(root.left);
		}
		System.out.print(root.data + " ");
		if(root.right != null) {
			InOrder(root.right);
		}
	}
}
class LNode
{
    int data;
    LNode next;
    LNode(int d) {
        data = d; 
        next = null;
    }
}

//Tree Node
class TNode
{
    int data;
    TNode left, right;
    TNode(int x)
    {
        data=x;
        left=right=null;
    }
    
}
