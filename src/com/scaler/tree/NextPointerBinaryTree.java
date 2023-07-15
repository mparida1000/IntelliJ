package com.scaler.tree;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Problem Description
Given a binary tree,
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
Initially, all next pointers are set to NULL.
Assume perfect binary tree.
 */

public class NextPointerBinaryTree {
	
	public void connect(TreeLinkNode root) {
        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
        	int size = queue.size();
        	for(int i = 0 ; i < size ; i++) {
        		TreeLinkNode curr = queue.poll();
        		if(queue.peek() != null && i < size - 1) {
        			curr.next = queue.peek();
        		}
        		if(curr.left != null) {
        			queue.add(curr.left);
        		}
        		if(curr.right != null) {
        			queue.add(curr.right);
        		}
        	}
        }
    }

}

class TreeLinkNode {
  int val;
  TreeLinkNode left, right, next;
  TreeLinkNode(int x) {
	  val = x; 
  }
}
