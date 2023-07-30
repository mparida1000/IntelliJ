package com.scaler.tries;
/*
The outer loop iterates over all cells in the board, which gives us a time complexity of O(M * N) to visit all cells
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class BoggleSolverBFS {
	private final Set<String> foundWords;
    private final TrieNode root;
    private final char[][] board;
    private final int rows;
    private final int cols;
    private final int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private final int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public BoggleSolverBFS(char[][] board, Set<String> validWords) {
        this.foundWords = new HashSet<>();
        this.root = buildTrie(validWords);
        this.board = board;
        this.rows = board.length;
        this.cols = board[0].length;
    }

    public List<String> findAllWords() {
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                bfs(i, j, visited);
            }
        }

        return new ArrayList<>(foundWords);
    }

    private void bfs(int row, int col, boolean[][] visited) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(row, col, "", root));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int currentRow = node.row;
            int currentCol = node.col;
            String currentWord = node.word;
            TrieNode currentNode = node.trieNode;

            if (currentNode.isWord) {
                foundWords.add(currentWord);
            }

            for (int i = 0; i < 8; i++) {
                int newRow = currentRow + dx[i];
                int newCol = currentCol + dy[i];

                if (isValid(newRow, newCol) && !visited[newRow][newCol] && currentNode.children.containsKey(board[newRow][newCol])) {
                    visited[newRow][newCol] = true;
                    queue.offer(new Node(newRow, newCol, currentWord + board[newRow][newCol], currentNode.children.get(board[newRow][newCol])));
                }
            }
            visited[currentRow][currentCol] = false;
        }
    }

    private boolean isValid(int row, int col) {
        return (row >= 0 && row < rows && col >= 0 && col < cols);
    }

    private TrieNode buildTrie(Set<String> words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TrieNode());
                }
                node = node.children.get(c);
            }
            node.isWord = true;
        }
        return root;
    }

    static class Node {
        int row;
        int col;
        String word;
        TrieNode trieNode;

        public Node(int row, int col, String word, TrieNode trieNode) {
            this.row = row;
            this.col = col;
            this.word = word;
            this.trieNode = trieNode;
        }
    }
    class TrieNode {
        Map<Character, TrieNode> children;
        boolean isWord;

        public TrieNode() {
            children = new HashMap<>();
            isWord = false;
        }
    }
    public static void main(String[] args) {
        char[][] board = {
        		{'M', 'S', 'E', 'F'},
                {'R', 'A', 'T', 'D'},
                {'L', 'O', 'N', 'S'},
                {'K', 'A', 'F', 'B'}
        };

        Set<String> validWords = new HashSet<>();
        validWords.add("ANT");
        validWords.add("ANS");
        validWords.add("AND");
        validWords.add("ANDI");
        validWords.add("ANVIL");
        validWords.add("ALKAF");
        validWords.add("MAN");
        BoggleSolverBFS solver = new BoggleSolverBFS(board, validWords);
        List<String> allWords = solver.findAllWords();

        System.out.println("All possible words on the Boggle board:");
        for (String word : allWords) {
            System.out.println(word);
        }
    }
}
