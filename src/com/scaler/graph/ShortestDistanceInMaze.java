package com.scaler.graph;
/*
 * Problem Description:
Given a matrix of integers A of size N x M describing a maze. The maze consists of empty 
locations and walls.

1 represents a wall in a matrix and 0 represents an empty location in a wall.

There is a ball trapped in a maze. The ball can go through empty spaces by rolling up, down, left or right, 
but it won't stop rolling until hitting a wall (maze boundary is also considered as a wall). 
When the ball stops, it could choose the next direction.

Given two array of integers of size B and C of size 2 denoting the starting and destination position of 
the ball.

Find the shortest distance for the ball to stop at the destination. The distance is defined by the number 
of empty spaces traveled by the ball from the starting position (excluded) to the destination (included). 
If the ball cannot stop at the destination, return -1.
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistanceInMaze {
	private static final int[][] DIRS = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
	public int solve(int[][] maze, int[] start, int[] dest) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row: distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distance[start[0]][start[1]] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            for (int[] dir: DIRS) {
                int x = position[0];
                int y = position[1];
                int count = 0;
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                // Roll Back - When the program break from while loop above,
                // it meas that x, y has been added dir[0], dir[1] one more time and count also has been added 1.
                // But the correct answer (in the range) is less than it, so we should minus dir[0], dir[1] and count-- here.
                x -= dir[0];
                y -= dir[1];
                count--;
                if (distance[position[0]][position[1]] + count < distance[x][y]) {
                    distance[x][y] = distance[position[0]][position[1]] + count;
                    queue.add(new int[] {x, y});
                }
            }
        }
        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }
	public static void main(String[] args) {
		int [][] A = {
				{1,1,0,1},
				{0,0,0,1},
				{1,0,0,1},
				{0,0,1,0}
		};
		int [] B = {1,1};
		int [] C = {2,1};
		System.out.println(new ShortestDistanceInMaze().solve(A, B, C));
	}
}


