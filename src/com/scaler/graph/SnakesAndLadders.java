package com.scaler.graph;

import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n * n + 1];
        boolean isLeftToRight = true;
        int k = 1;

        // Convert the 2D board to 1D array
        for (int i = n - 1; i >= 0; i--) {
            if (isLeftToRight) {
                for (int j = 0; j < n; j++) {
                    arr[k++] = board[i][j];
                }
            } else {
                for (int j = n - 1; j >= 0; j--) {
                    arr[k++] = board[i][j];
                }
            }
            isLeftToRight = !isLeftToRight;
        }

        boolean[] visited = new boolean[n * n + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 0}); // {cell, num of moves}

        // BFS
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int position = cell[0];
            int moves = cell[1];

            if (position == n * n) {
                return moves;
            }

            for (int i = 1; i <= 6; i++) {
                int nextPos = position + i;
                if (nextPos > n * n) break;
                if (arr[nextPos] != -1) nextPos = arr[nextPos];

                if (!visited[nextPos]) {
                    visited[nextPos] = true;
                    queue.add(new int[]{nextPos, moves + 1});
                }
            }
        }

        return -1; // If not possible to reach the end
    }
    public static void main(String[] args) {
        int[][] board = {
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}
        };

        SnakesAndLadders game = new SnakesAndLadders();
        int result = game.snakesAndLadders(board);
        System.out.println("Minimum number of moves to reach the end: " + result);
    }
}
