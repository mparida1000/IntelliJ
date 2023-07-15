package com.scaler.tries;

public class MaximumXOR {

	public int solve(int[] A) {
		Node root = new Node();
		int n = A.length;
		for(int i = 0 ; i < n ; i++) {
			insert(root, A[i]);
		}
		return maxXOR(root, A);
    }
	
	private void insert(Node root, int x) {
		Node curr = root;
		for(int i = 30 ; i >= 0 ; i--) {
			int bit = checkBit(x, i);
			if(curr.child[bit] == null) {
				curr.child[bit] = new Node();
			}
			curr = curr.child[bit];
		}
		curr.val = x;
	}
	
	private int maxXOR(Node root, int [] A) {
		return 0;
	}
	
	private int checkBit(int x, int i) {
		return (( x >> i) & 1);
	}
	
}
