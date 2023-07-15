package com.scaler.tries;

class TrieNode {
    TrieNode[] child = new TrieNode[2];
}

public class MaxXORPair {
    static TrieNode root;

    public static void insert(int num) {
        TrieNode temp = root;
        for (int i = 30; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (temp.child[bit] == null) {
                temp.child[bit] = new TrieNode();
            }
            temp = temp.child[bit];
        }
    }

    public static int findMaxXOR(int[] nums) {
        root = new TrieNode();
        int maxXOR = Integer.MIN_VALUE;

        for (int num : nums) {
            insert(num);
        }

        for (int num : nums) {
            TrieNode temp = root;
            int currXOR = 0;

            for (int i = 30; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (temp.child[1 - bit] != null) {
                    temp = temp.child[1 - bit];
                    currXOR |= (1 << i);
                } else {
                    temp = temp.child[bit];
                }
            }

            maxXOR = Math.max(maxXOR, currXOR);
        }

        return maxXOR;
    }

    public static void main(String[] args) {
        int[] nums = {3, 4, 7};
        int maxXOR = findMaxXOR(nums);
        System.out.println(maxXOR); // should print 6
    }
}

