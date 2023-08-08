package practice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BoggleSolver {
    private char[][] board;
    private Set<String > foundWords;
    private Trie root;
    private int row;
    private int col;
    private boolean [][] visited;
    private final int [] dx = {-1,-1,-1,0,1,1,1,0};
    private final int [] dy = {-1,0,1,1,1,0,-1,-1};

    public BoggleSolver(char [][] arr, Set<String> givenWords){
        board = arr;
        root = buildTrie(givenWords);
        row = board.length;
        col = board[0].length;
        foundWords = new HashSet<>();
        visited = new boolean[row][col];
    }
    public Set<String> findAllWords(){
        for(int i = 0 ; i < row ; i++){
            for(int j = 0  ; j < col ; j++){
                dfs(i, j, root, "");
            }
        }
        return foundWords;
    }

    private void dfs(int row, int col, Trie root, String currentWord) {
        char c = board[row][col];
        if(!root.children.containsKey(c)) return;
        visited[row][col] = true;
        currentWord+=c;
        root = root.children.get(c);
        if(root.isWord){
            foundWords.add(currentWord);
        }
        for(int i = 0 ; i < 8 ; i++){
            int x = row+dx[i];
            int y = col+dy[i];
            if(isValid(x, y) && !visited[x][y]){
                dfs(x,y,root,currentWord);
            }
        }
        visited[row][col] = false;
    }

    private boolean isValid(int x, int y){
        return (x >=0 && x < row && y >= 0 && y < col);
    }

    private Trie buildTrie(Set<String> words){
        Trie root = new Trie();
        for(String word : words){
            Trie node = root;
            for(Character c : word.toCharArray()){
                if(!node.children.containsKey(c)){
                    node.children.put(c, new Trie());
                }
                node = node.children.get(c);
            }
            node.isWord = true;
        }
        return root;
    }
}

class Trie{
    Map<Character, Trie> children;
    boolean isWord = false;
    public Trie(){
        children = new HashMap<>();
    }
}
