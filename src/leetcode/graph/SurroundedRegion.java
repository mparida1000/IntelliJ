package leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

public class SurroundedRegion {
    private static final int [][] DIR = {{1, 0},{-1, 0},{0, 1},{0, -1}};
    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        Queue<int []> queue = new LinkedList<>();
        for(int i = 0 ; i < n ; i++){
            if(board[i][0] == '0'){
                board[i][0] = '#';
                queue.offer(new int[]{i, 0});
            }
            if(board[i][m-1] == '0'){
                board[i][m-1] = '#';
                queue.offer(new int[]{i, m-1});
            }
        }
        for(int j = 1 ; j < m-1 ; j++){
            if(board[0][j] == '0'){
                board[0][j] = '#';
                queue.offer(new int[]{0, j});
            }
            if(board[n-1][j] == '0'){
                board[n-1][j] = '#';
                queue.offer(new int[]{n-1, j});
            }
        }
        while(!queue.isEmpty()){
            int [] cell = queue.poll();
            for(int [] dir : DIR){
                int row = cell[0] + dir[0];
                int col = cell[1] + dir[1];
                if(row >=0 && row < n && col >= 0 && col < m && board[row][col] == '0'){
                    board[row][col] = '#';
                    queue.offer(new int[] {row, col});
                }
            }
        }
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(board[i][j] == '0'){
                    board[i][j] = 'X';
                }else if (board[i][j] == '#'){
                    board[i][j] = '0';
                }

            }
        }
    }

    public static void main(String[] args) {
        /*char [][] board = {
                {'X','X','X','X'},
                {'X','0','0','X'},
                {'X','X','0','X'},
                {'X','0','X','X'}
        };*/
        char [][] board = {
                {'0','0','0'},
                {'0','0','0'},
                {'0','0','0'}
        };

        new SurroundedRegion().solve(board);
        for(int i = 0 ; i < board.length ; i++){
            for(int j = 0 ; j < board[0].length ; j++){
                System.out.print(board[i][j] +" ");
            }
            System.out.println();
        }
    }
}
