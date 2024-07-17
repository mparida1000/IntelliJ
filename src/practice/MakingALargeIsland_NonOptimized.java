package practice;
/*
You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
Return the size of the largest island in grid after applying this operation.
An island is a 4-directionally connected group of 1s.
Example 1:
Input: grid = [[1,0],[0,1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
*/
public class MakingALargeIsland_NonOptimized {
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        int maxSizeIsland = 0;

        // Calculate the size of all islands without changing any 0 to 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    maxSizeIsland = Math.max(maxSizeIsland, dfs(grid, visited, i, j));
                }
            }
        }

        // Try changing each 0 to 1 and calculate the new island size
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    grid[i][j] = 1; // Change 0 to 1
                    visited = new boolean[n][n]; // Reset visited array
                    int size = dfs(grid, visited, i, j);
                    maxSizeIsland = Math.max(maxSizeIsland, size);
                    grid[i][j] = 0; // Change back to 0
                }
            }
        }
        return maxSizeIsland;
    }

    private boolean isValidDir(int x, int y, boolean[][] visited) {
        return x >= 0 && x < visited.length && y >= 0 && y < visited[0].length && !visited[x][y];
    }

    private int dfs(int[][] grid, boolean[][] visited, int row, int col) {
        if (grid[row][col] == 0 || visited[row][col]) {
            return 0;
        }

        visited[row][col] = true;
        int size = 1;
        for (int i = 0; i < 4; i++) {
            int x = row + dx[i];
            int y = col + dy[i];
            if (isValidDir(x, y, visited)) {
                size += dfs(grid, visited, x, y);
            }
        }
        return size;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 0, 1, 0},
                {1, 0, 1, 1},
                {1, 0, 0, 0},
                {0, 0, 1, 0}
        };
        System.out.println(new MakingALargeIsland_NonOptimized().largestIsland(grid)); // Expected output: 7
    }
}