package com.scaler.tries;

public class Node {
	boolean isEnd;
	Node child [];
	int wt;
	int count;
	int frequency;
	int val;
	public Node() {
		child = new Node[26];
		for(Node node : child) {
			node = null;
		}
		isEnd = false;
		wt = 0;
		count = 0;
		frequency = 0;
		val = 0;
	}

}
