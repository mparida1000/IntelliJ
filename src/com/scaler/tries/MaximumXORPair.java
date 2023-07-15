package com.scaler.tries;

public class MaximumXORPair {

    static class TrieNode {
        TrieNode[] child = new TrieNode[2];
    }

    static TrieNode root;

    static void insert(int num) {
        TrieNode temp = root;

        for (int i = 2; i >= 0; i--) {
            int bit = (num >> i) & 1;

            if (temp.child[bit] == null) {
                temp.child[bit] = new TrieNode();
            }
            temp = temp.child[bit];
        }
    }

    static int findMaxXOR(int[] nums) {
    	root = new TrieNode();
        int maxXOR = 0;

        for (int num : nums) {
            insert(num);
        }

        for (int num : nums) {
            TrieNode temp = root;
            int currXOR = 0;

            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                int complement = 1 - bit;

                if (temp.child[complement] != null) {
                    temp = temp.child[complement];
                    currXOR |= (complement << i);
                } else if (temp.child[bit] != null) {
                    temp = temp.child[bit];
                    currXOR |= (bit << i);
                } else {
                    break;
                }
            }

            maxXOR = Math.max(maxXOR, currXOR);
        }

        return maxXOR;
    }

    public static void main(String[] args) {
        int[] nums = {3,5,7};// { 25, 10, 2, 8, 5, 3 };
        System.out.println(findMaxXOR(nums)); // Output: 30
    }
}

