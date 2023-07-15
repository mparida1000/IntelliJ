package com.scaler.graph;

import java.util.LinkedList;
import java.util.Queue;


/*
 * Problem Description
There is a rectangle with left bottom as (0, 0) and right up as (x, y).
There are N circles such that their centers are inside the rectangle.
Radius of each circle is R. Now we need to find out if it is possible that we can move from (0, 0) to (x, y) without touching any circle.
Note : We can move from any cell to any of its 8 adjecent neighbours and we cannot move outside the boundary of the rectangle at any point of time.

Problem Constraints
0 <= x , y, R <= 100
1 <= N <= 1000
Center of each circle would lie within the grid
Input Format
1st argument given is an Integer x , denoted by A in input.
2nd argument given is an Integer y, denoted by B in input.
3rd argument given is an Integer N, number of circles, denoted by C in input.
4th argument given is an Integer R, radius of each circle, denoted by D in input.
5th argument given is an Array A of size N, denoted by E in input, where A[i] = x cordinate of ith circle
6th argument given is an Array B of size N, denoted by F in input, where B[i] = y cordinate of ith circle

Output Format
Return YES or NO depending on weather it is possible to reach cell (x,y) or not starting from (0,0).
 */
public class HW_ValidPath {
	public String solve(int A, int B, int C, int D, int[] E, int[] F) {
        // create a visited array to keep track of visited cells
        boolean[][] visited = new boolean[A+1][B+1];
        
        // create a queue for BFS traversal
        Queue<int[]> queue = new LinkedList<>();
        
        // add the starting cell (0,0) to the queue
        queue.offer(new int[]{0, 0});
        
        // loop through all the circles and mark their cells as visited
        for (int i = 0; i < C; i++) {
            int cx = E[i];
            int cy = F[i];
            
            int startx = Math.max(0, cx-D);
            int endx = Math.min(A, cx+D);
            int starty = Math.max(0, cy-D);
            int endy = Math.min(B, cy+D);
            
            for (int x = startx; x <= endx; x++) {
                for (int y = starty; y <= endy; y++) {
                    double dist = Math.sqrt((x-cx)*(x-cx) + (y-cy)*(y-cy));
                    if (dist <= D) {
                        visited[x][y] = true;
                    }
                }
            }
        }
        
        // perform BFS traversal
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            
            // check if we have reached the destination cell
            if (x == A && y == B) {
                return "YES";
            }
            
            // add all the adjacent cells to the queue if they are not visited
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int newx = x + i;
                    int newy = y + j;
                    
                    if (newx >= 0 && newx <= A && newy >= 0 && newy <= B && !visited[newx][newy]) {
                        queue.offer(new int[]{newx, newy});
                        visited[newx][newy] = true;
                    }
                }
            }
        }
        
        // if we have not reached the destination cell, then it is not possible to reach it
        return "NO";
    }
	public static void main(String[] args) {
		HW_ValidPath solution = new HW_ValidPath();

	    int A = 58;//x
	    int B = 91;//y
	    int C = 6;//circles
	    int D = 8;//Dia
	    int[] E = {40, 6, 36, 38, 23, 54};
	    int[] F = {88, 14, 50, 10, 15, 5};
	    String expectedOutput = "YES";

	    String output = solution.solve(A, B, C, D, E, F);
	    System.out.println(output.equals(expectedOutput)); // output should be true
	}

}
