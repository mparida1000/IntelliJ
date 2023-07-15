package com.scaler.tries;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Boggle {
	static class Trie
	{
	    // true when the node is a leaf node
	    boolean isLeaf;
	 
	    Map<Character, Trie> character = new HashMap<>();
	 
	    // Constructor
	    Trie() {
	        isLeaf = false;
	    }
	}
	// `M × N` matrix
    private static int M, N;
 // Iterative function to insert a string into a Trie
    private static void insert(Trie root, String str)
    {
        // start from the root node
        Trie curr = root;
 
        for (char ch: str.toCharArray())
        {
            // create a new node if the path doesn't exist
        	curr.character.putIfAbsent(ch, new Trie());
 
            // go to the next node
            curr = curr.character.get(ch);
        }
 
        curr.isLeaf = true;
    }
 
    // Below arrays detail all eight possible movements from a cell
    // (top, right, bottom, left, and four diagonal moves)
    private static int[] row = { -1, -1, -1, 0, 1, 0, 1, 1 };
    private static int[] col = { -1, 1, 0, -1, -1, 1, 0, 1 };
 
    // The function returns false if (x, y) is not valid matrix coordinates
    // or cell (x, y) is already processed or doesn't lead to the solution
    public static boolean isSafe(int x, int y, boolean[][] processed,
                                 char[][] board, char ch)
    {
        return (x >= 0 && x < M) && (y >= 0 && y < N) &&
                !processed[x][y] && (board[x][y] == ch);
    }
 
    // A recursive function to search valid words present in a boggle using trie
    public static void searchBoggle(Trie root, char[][] board, int i, int j,
                                    boolean[][] processed, String path,
                                    Set<String> result)
    {
        // if a leaf node is encountered
        if (root.isLeaf) {
            // update result with the current word
            result.add(path);
        }
 
        // mark the current cell as processed
        processed[i][j] = true;
 
        // traverse all children of the current Trie node
        for (var entry: root.character.entrySet())
        {
            // check for all eight possible movements from the current cell
            for (int k = 0; k < row.length; k++)
            {
                // skip if cell is invalid or entry is already processed
                // or doesn't lead to any path in the Trie
                if (isSafe(i + row[k], j + col[k], processed, board, entry.getKey()))
                {
                    searchBoggle(entry.getValue(), board, i + row[k], j + col[k],
                            processed, path + entry.getKey(), result);
                }
            }
        }
 
        // backtrack: mark the current cell as unprocessed
        processed[i][j] = false;
    }
 
    // Function to search for a given set of words in a boggle
    public static Set<String> searchBoggle(char[][] board, Set<String> words)
    {
        // construct a set for storing the result
        Set<String> result = new HashSet<>();
 
        // base case
        if (board.length == 0) {
            return result;
        }
 
        // `M × N` board
        int M = board.length;
        int N = board[0].length;
 
        // insert all words into a trie
        Trie root = new Trie();
        for (String word: words) {
            insert(root, word);
        }
 
        // construct a boolean matrix to store whether a cell is processed or not
        boolean[][] processed = new boolean[M][N];
 
        // consider each character in the matrix
        for (int i = 0; i < M; i++)
        {
            for (int j = 0; j < N; j++)
            {
                // current character
                char ch = board[i][j];
 
                // proceed only if the current character is a child of the Trie root
                if (root.character.containsKey(ch))
                {
                    searchBoggle(root.character.get(ch), board, i, j,
                            processed, Character.toString(ch), result);
                }
            }
        }
 
        // return the result set
        return result;
    }
	public static void main(String[] args)
    {
        char[][] board =
        {
            {'M', 'S', 'E', 'F'},
            {'R', 'A', 'T', 'D'},
            {'L', 'O', 'N', 'E'},
            {'K', 'A', 'F', 'B'}
        };
 
        M = board.length;
        N = board[0].length;
 
        Set<String> words = Stream.of("ANT", "AND", "ANDI", "ANS")
                .collect(Collectors.toSet());
 
        Set<String> validWords = searchBoggle(board, words);
        System.out.println(validWords);
    }
	
}
