//package com.scaler.tries;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//import java.util.Queue;
//
//class BoggleSolver2 {
//    private TrieNode root;
//    private char[][] board;
//    private int m;
//    private int n;
//    private List<String> words;
//
//    public BoggleSolver2(TrieNode root, char[][] board) {
//        this.root = root;
//        this.board = board;
//        m = board.length;
//        n = board[0].length;
//        words = new ArrayList<>();
//    }
//
//    public List<String> findWords() {
//        boolean[][] visited = new boolean[m][n];
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                bfs(i, j);
//            }
//        }
//        return words;
//    }
//
//    private void bfs(int row, int col) {
//        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
//        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
//
//        Queue<int[]> queue = new LinkedList<>();
//        TrieNode node = root.getChild(board[row][col]);
//        if (node != null) {
//            queue.offer(new int[]{row, col, node, 0});
//        }
//
//        while (!queue.isEmpty()) {
//            int[] curr = queue.poll();
//            int currRow = curr[0];
//            int currCol = curr[1];
//            TrieNode currNode = curr[2];
//            int currDepth = curr[3];
//
//            if (currNode.isEndOfWord()) {
//                String word = getWord(currDepth, row, col);
//                if (!words.contains(word)) {
//                    words.add(word);
//                }
//            }
//
//            for (int i = 0; i < 8; i++) {
//                int newRow = currRow + dx[i];
//                int newCol = currCol + dy[i];
//
//                if (isValidCell(newRow, newCol)) {
//                    char nextChar = board[newRow][newCol];
//                    TrieNode nextNode = currNode.getChild(nextChar);
//                    if (nextNode != null) {
//                        queue.offer(new int[]{newRow, newCol, nextNode, currDepth + 1});
//                    }
//                }
//            }
//        }
//    }
//
//    private boolean isValidCell(int row, int col) {
//        return row >= 0 && row < m && col >= 0 && col < n;
//    }
//
//    private String getWord(int depth, int startRow, int startCol) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(board[startRow][startCol]);
//        for (int i = 1; i <= depth; i++) {
//            sb.append(board[startRow][startCol]);
//        }
//        return sb.toString();
//    }
//
//
//    public static void main(String[] args) {
//        TrieNode root = new TrieNode();
//        // Add words to the Trie
//        // Example:
//        root.setChild('a', new TrieNode());
//        root.getChild('a').setChild('p', new TrieNode());
//        root.getChild('a').getChild('p').setChild('p', new TrieNode());
//        root.getChild('a').getChild('p').getChild('p').setEndOfWord(true);
//
//        char[][] board = {
//            {'a', 'p', 'p'},
//            {'t', 'i', 'e'},
//            {'e', 'r', 's'}
//        };
//
//        BoggleSolver2 solver = new BoggleSolver2(root, board);
//        List<String> words = solver.findWords();
//
//        // Print the found words
//        for (String word : words) {
//            System.out.println(word);
//        }
//    }
//    static class TrieNode {
//        private Map<Character, TrieNode> children;
//        private boolean isEndOfWord;
//
//        public TrieNode() {
//            children = new HashMap<>();
//            isEndOfWord = false;
//        }
//
//        public void setEndOfWord(boolean isEndOfWord) {
//            this.isEndOfWord = isEndOfWord;
//        }
//
//        public boolean isEndOfWord() {
//            return isEndOfWord;
//        }
//
//        public TrieNode getChild(char c) {
//            return children.get(c);
//        }
//
//        public void setChild(char c, TrieNode node) {
//            children.put(c, node);
//        }
//    }
//}
//
