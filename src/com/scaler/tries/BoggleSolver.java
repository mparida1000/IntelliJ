package com.scaler.tries;
/*
The time complexity for building the Trie is O(L), where L is the total length
of all valid words. The time complexity for DFS on each cell remains O
(MN). Therefore, the overall time complexity of the code is O(L + MN)
 */
import java.util.*;

public class BoggleSolver {
	
	static class TrieNode {
	    Map<Character, TrieNode> children;
	    boolean isWord;

	    public TrieNode() {
	        children = new HashMap<>();
	        isWord = false;
	    }
	}
    private final Set<String> foundWords;
    private final TrieNode root;
    private final char[][] board;
    private final int rows;
    private final int cols;
    private final boolean[][] visited;
    private final int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private final int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public BoggleSolver(char[][] board, Set<String> validWords) {
        this.foundWords = new HashSet<>();
        this.root = buildTrie(validWords);
        this.board = board;
        this.rows = board.length;
        this.cols = board[0].length;
        this.visited = new boolean[rows][cols];
    }

    public List<String> findAllWords() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dfs(i, j, root, "");
            }
        }
        return new ArrayList<>(foundWords);
    }

    private void dfs(int row, int col, TrieNode node, String currentWord) {
        char c = board[row][col];
        if (!node.children.containsKey(c))
            return;

        visited[row][col] = true;
        currentWord += c;
        node = node.children.get(c);

        if (node.isWord) {
            foundWords.add(currentWord);
        }

        for (int i = 0; i < 8; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];

            if (isValid(newRow, newCol) && !visited[newRow][newCol]) {
                dfs(newRow, newCol, node, currentWord);
            }
        }

        visited[row][col] = false;
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
        validWords.add("NAM");
        BoggleSolver solver = new BoggleSolver(board, validWords);
        List<String> allWords = solver.findAllWords();

        System.out.println("All possible words on the Boggle board:");
        for (String word : allWords) {
            System.out.println(word);
        }
    }
}

