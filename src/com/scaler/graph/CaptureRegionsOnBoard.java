package com.scaler.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/*
 * Problem Description
Given a 2-D board A of size N x M containing 'X' and 'O', capture all regions surrounded by 'X'.
A region is captured by flipping all 'O's into 'X's in that surrounded region.

Problem Constraints
1 <= N, M <= 1000
 */

public class CaptureRegionsOnBoard {
	private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	public void solve(ArrayList<ArrayList<Character>> a) {
        if (a == null || a.isEmpty())
            return;

        int rows = a.size();
        int cols = a.get(0).size();

        // Mark 'O' cells on the boundary and perform BFS
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < rows; i++) {
            if (a.get(i).get(0) == 'O') {
                a.get(i).set(0, '#');
                queue.offer(new int[]{i, 0});
            }

            if (a.get(i).get(cols - 1) == 'O') {
                a.get(i).set(cols - 1, '#');
                queue.offer(new int[]{i, cols - 1});
            }
        }

        for (int j = 0; j < cols; j++) {
            if (a.get(0).get(j) == 'O') {
                a.get(0).set(j, '#');
                queue.offer(new int[]{0, j});
            }

            if (a.get(rows - 1).get(j) == 'O') {
                a.get(rows - 1).set(j, '#');
                queue.offer(new int[]{rows - 1, j});
            }
        }

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];

            for (int[] direction : DIRECTIONS) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && a.get(newRow).get(newCol) == 'O') {
                    a.get(newRow).set(newCol, '#');
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        // Convert remaining 'O' cells to 'X' and revert marked cells to 'O'
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (a.get(i).get(j) == 'O')
                    a.get(i).set(j, 'X');
                else if (a.get(i).get(j) == '#')
                    a.get(i).set(j, 'O');
            }
        }
    }
	public static void main(String[] args) {
        // Example Input 1
        ArrayList<ArrayList<Character>> board1 = new ArrayList<>();
        board1.add(new ArrayList<>(List.of('X', 'X', 'O', 'X')));
        board1.add(new ArrayList<>(List.of('0', 'O', 'O', 'X')));
        board1.add(new ArrayList<>(List.of('X', 'X', 'O', 'X')));
        board1.add(new ArrayList<>(List.of('X', 'O', 'X', 'X')));

        CaptureRegionsOnBoard solution = new CaptureRegionsOnBoard();
        solution.solve(board1);

        System.out.println("Output 1:");
        for (ArrayList<Character> row : board1) {
            System.out.println(row);
        }
	}
}
