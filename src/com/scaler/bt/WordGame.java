package com.scaler.bt;
/*
 * There is a word search game in which you have been given a board A of size n*m filled with alphabets, 
 * and your task is to find a given word B from the board. You are allowed to select any letter from the 
 * board as the starting point, and then you can move in any of the four directions, i.e., up, down, left, 
 * or right, to find the next letter. However, you can only use each letter on the board once while making 
 * the word. You need to return true if the word can be constructed from the letters of the board, and 
 * false otherwise.

Note: You can visit a cell only once i.e., use that cell's character only once.
 */
public class WordGame {
	public int solve(String[] A, String B) {
        int m = A.length;
        int n = A[0].length();
        
        boolean[][] visited = new boolean[m][n];
        for(int i = 0 ; i < m ; i++){
            for (int j =0 ; j < n ; j++){
                if(A[i].charAt(j) == B.charAt(0)){
                    if(backtrack(A, B, visited, i,j,0)){
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
    private boolean backtrack(String [] board, String word, boolean[][] visited, int i, int j, int index){
        if (index == word.length()){
            return true;
        }
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length() || visited[i][j] || board[i].charAt(j) != word.charAt(index)){
            return false;
        }
        visited[i][j] = true;
        boolean found = backtrack(board, word, visited, i+1,j,index+1) || 
                        backtrack(board, word, visited, i-1,j,index+1) || 
                        backtrack(board, word, visited, i,j+1,index+1) ||
                        backtrack(board, word, visited, i,j-1,index+1);
        visited[i][j] = false;
        return found;
    }
    
    public static void main(String[] args) {
		String [] A = new String[]{"acd","bcn","dkp"};
		System.out.println(new WordGame().solve(A, "cdn"));
	}
}
