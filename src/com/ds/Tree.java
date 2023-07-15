package com.ds;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

public class Tree {
	static int count = 0;
	static Scanner sc = null;
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		Node root = createTree();
		/*inOrder(root);
		System.out.println();
		preOrder(root);
		System.out.println();
		postOrder(root);
		System.out.println();
		int height = heightOfTree(root);
		System.out.println("Height : "+height);
		int max = maxOfTree(root);
		System.out.println("Maximum in the Tree : "+max);
		levelOrderUsingQueue(root);
		System.out.println();
		leftViewBinaryTree(root);
		System.out.println();
		topViewOfTree(root);
		convertToDL(root);
		System.out.println("Dia of Tree");
		int diam = diaOfTree(root);
		System.out.println(dia);*/
		ArrayList<Integer> list = verticalOrderBinaryTree(root);
		solve(root);
		System.out.println(count);
		System.out.println(list.toString());

		
		
	}
	
	public static int solve(Node A) {
        getNodes(A, Integer.MIN_VALUE);
        return count;
    }

    public static void getNodes(Node node, int max){
        if(node == null) return;
        if(node.data > max) count++;
         getNodes(node.left, Math.max(node.data, max));
         getNodes(node.right, Math.max(node.data, max));
    }
	
	//Only care about each node, don't care about what happens to subsequent nodes, recursion will take care
	static Node createTree() {
		System.out.println("Enter data..");
		int data = sc.nextInt();
		if(data == -1) return null;
		Node root = new Node(data);
		System.out.println("Enter left for Node "+data);
		root.left = createTree();
		System.out.println("Enter right for Node "+data);
		root.right = createTree();
		
				/* Construct the following tree
		        1
		      /   \
		     /     \
		    2       3
		   /      /   \
		  /      /     \
		 4      5       6
		       / \
		      /   \
		     7     8
		*/
		
		/*
		Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.right.left = new Node(5);
        root.right.right = new Node(6);
        root.right.left.left = new Node(7);
        root.right.left.right = new Node(8);
        */
        
		return root;
	}
	
	static void inOrder(Node root) {
		if(root==null) return;
		inOrder(root.left);
		System.out.print(root.data+" ");
		inOrder(root.right);
	}
	
	static void preOrder(Node root) {
		if(root==null) return;
		System.out.print(root.data+" ");
		preOrder(root.left);
		preOrder(root.right);
	}
	
	static void postOrder(Node root) {
		if(root==null) return;
		postOrder(root.left);
		postOrder(root.right);
		System.out.print(root.data+" ");
	}
	
	static int heightOfTree(Node root) {
		if(root == null)return 0;
		return Math.max(heightOfTree(root.left), heightOfTree(root.right))+1;
	}
	
	static int maxOfTree(Node root) {
		if(root == null) return Integer.MIN_VALUE;
		return Math.max(root.data, Math.max(maxOfTree(root.left), maxOfTree(root.right)));
	}
	
	static void levelOrderTraversal(Node root) {
		int level = 1;
		Map<Integer, List<Integer>> map = new HashMap();
		preOrder(root, 1, map);
		for(int i = 1 ; i <= map.size() ; i++) {
			System.out.println("Level "+ i + ": "+map.get(i));
		}
	}
	static void preOrder(Node root, int level, Map<Integer, List<Integer>> map) {
		
		if(root==null) return;
		map.putIfAbsent(level, new ArrayList());
		map.get(level).add(root.data);
		preOrder(root.left, level+1, map);
		preOrder(root.right, level+1, map);
		
	}
	
	static void levelOrderUsingQueue(Node root) {
		if(root==null) return;
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		queue.add(null);
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			if(curr == null) {
				if(queue.isEmpty()) return;
				queue.add(null);
				System.out.println();
			}
			if(curr!=null) {
				System.out.print(curr.data + " ");
				if(curr.left!=null) {
					queue.add(curr.left);
				}
				if(curr.right!=null) {
					queue.add(curr.right);
				}
			}
			
		}
	}
	
	static void leftViewBinaryTree(Node root) {
		ArrayList<Node> list = new ArrayList<Node>();
		
		leftViewUtil(root, 0, list);
		for(Node node : list) {
			System.out.println(node.data+" ");
		}
		
	}
	static void leftViewUtil(Node root, int level, ArrayList<Node> list) {
		if(root == null) return;
		if(list.size() == level) {
			list.add(root);
		}
		leftViewUtil(root.left, level+1, list);
		leftViewUtil(root.right, level+1, list);
	}
	
	static void topViewOfTree(Node root){
		Map<Integer, Integer> nodeMap = new TreeMap<Integer, Integer>();
		Queue<Pair> q = new ArrayDeque<Pair>();
		if(root == null)return;
		q.add(new Pair(0, root));
		while(!q.isEmpty()) {
			Pair currPair = q.poll();
			if(!nodeMap.containsKey(currPair.hd)) {
				nodeMap.put(currPair.hd, currPair.node.data);
			}
			if(currPair.node.left!=null)
			q.add(new Pair(currPair.hd-1, currPair.node.left));
			if(currPair.node.right!=null)
			q.add(new Pair(currPair.hd+1, currPair.node.right));
		}
		ArrayList<Integer> nodes  = new ArrayList();
		for (Map.Entry<Integer, Integer> entry : nodeMap.entrySet()) {
			nodes.add(entry.getValue());
		}
		for (Integer node : nodes) {
			System.out.print(node+" ");
		}
	}
	
	static Node head=null, prev=null;
	static void convertToDL(Node root) {
		if(root == null) return;
		convertToDL(root.left);
		if(prev == null) head = root;
		else {
			root.left = prev;
			prev.right = root;
		}
		prev = root;
		convertToDL(root.right);
	}
	static int dia = 0;
	static int diaOfTree(Node root) {
		if(root == null) return 0;
		int lh = diaOfTree(root.left);
		int rh = diaOfTree(root.right);
		dia = Math.max(dia, 1+lh+rh);
		return (1+Math.max(lh, rh));
	}
	
	static ArrayList verticalOrderBinaryTree(Node root) {
		Map<Integer, ArrayList<Integer>> map = new TreeMap();
		Queue<Pair> q = new ArrayDeque<Pair>();
		q.add(new Pair(0, root)); //Add root of binary tree
		while(!q.isEmpty()) {
			Pair curr = q.poll();
			if(map.containsKey(curr.hd)) {
				map.get(curr.hd).add(curr.node.data);
			}else {
				ArrayList<Integer> tempList = new ArrayList<Integer>();
				tempList.add(curr.node.data);
				map.put(curr.hd, tempList);
			}
			if(curr.node.left != null) {
				q.add(new Pair(curr.hd-1, curr.node.left));
			}
			if(curr.node.right != null) {
				q.add(new Pair(curr.hd+1, curr.node.right));
			}
		}
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for(Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
			ans.addAll(entry.getValue());
		}
		return ans;
		
	}
	
	public static Node formBSTFromPreOrder(int[] arr) {
		if(arr.length == 0) return null;
		Deque<Node> deque = new ArrayDeque<Node>();
		Node root = new Node(arr[0]);
		deque.push(root);
		for(int i = 1 ; i < arr.length ; i++) {
			Node child = new Node(arr[i]);
			Node parent = deque.peek();
			while(!deque.isEmpty() && child.data > deque.peek().data) {
				parent = deque.poll();
			}
			if(child.data > parent.data ) {
				parent.right = child;
			}else
				parent.left = child;
			deque.push(child);
		}
		return root;
	}
	
	
	
	//Recursive call for each section after finding the middle node in LL
	public static Node convertSortedLinkedListToBST(LinkedNode head) {
		if(head == null) {
			return null;
		}
		LinkedNode mid = findMiddleNode(head);
		Node node = new Node(mid.data);//Only one node in LL
		if(head == mid) return node;
		
		node.left = convertSortedLinkedListToBST(head);
		node.left = convertSortedLinkedListToBST(mid.next);
		
		return node;
	}
	
	//Sorted Node, find mid by fastPtr
	private static LinkedNode findMiddleNode(LinkedNode head) {
		
		LinkedNode prev  = null, slowPtr = head, fastPtr = head;
		while(fastPtr != null && fastPtr.next != null) {
			prev = slowPtr;
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
		}
		if(prev != null) {
			prev.next = null; //detach in to 2 halves
		}
		return slowPtr;
	}

}


class Node{
	Node left, right;
	int data;
	
	public Node(int data) {
		this.data = data;
	}
}

class Pair{
	int hd;
	Node node;
	public Pair(int hd, Node node){
		this.hd = hd;
		this.node = node;
	}
}