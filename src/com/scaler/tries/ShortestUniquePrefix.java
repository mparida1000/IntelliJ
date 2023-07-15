package com.scaler.tries;

public class ShortestUniquePrefix {
	
	public String[] prefix(String[] A) {
		int n = A.length;
		Node root = new Node();
        for(int i=0; i<n; i++) {
            insert(root,A[i]);
        }
        String[] prefix = new String[n];
        for(int i=0; i<n; i++) {
            prefix[i] = findPrefix(root, A[i]);
        }
        return prefix;
    }
	public void insert(Node root, String word) {
        Node node = root;
        for(char c : word.toCharArray()) {
            int index = c - 'a';
            if(node.child[index] == null) {
                node.child[index] = new Node();
            }
            node = node.child[index];
        }
        node.isEnd = true;
    }
    
    public String findPrefix(Node root, String word) {
        Node node = root;
        StringBuilder prefix = new StringBuilder();
        for(char c : word.toCharArray()) {
            int index = c - 'a';
            prefix.append(c);
            node = node.child[index];
            if(countChildren(node) == 1 || node.isEnd) {
                break;
            }
        }
        return prefix.toString();
    }
    
    private int countChildren(Node node) {
        int count = 0;
        for(int i=0; i<26; i++) {
            if(node.child[i] != null) {
                count++;
            }
        }
        return count;
    }
}

