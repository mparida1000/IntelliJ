package practice;

import java.util.*;

public class LargestIsland {

    public static void main(String[] args) {
        int[][] grid1 = {{1, 0, 1, 0}, {1, 0, 1, 1}, {1, 0, 0, 0}, {0, 0, 1, 0}};
        //int[][] grid2 = {{1, 1}, {1, 0}};
        //int[][] grid3 = {{1, 1}, {1, 1}};

        System.out.println(largestIsland(grid1)); // Output: 3
        //System.out.println(largestIsland(grid2)); // Output: 4
        //System.out.println(largestIsland(grid3)); // Output: 4
    }

    public static int largestIsland(int[][] grid) {
        int n = grid.length;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Map<Integer, Integer> areaMap = new HashMap<>();
        int islandId = 2; // Starting from 2 because 0 and 1 are already used in the grid
        int maxArea = 0;

        // Step 1: Find all islands and their areas
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j, islandId, directions);
                    areaMap.put(islandId, area);
                    maxArea = Math.max(maxArea, area);
                    islandId++;
                }
            }
        }

        // Step 2: Check each zero cell to see if it can connect two or more islands
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> uniqueIslands = new HashSet<>();
                    int potentialArea = 1; // Changing this cell from 0 to 1
                    for (int[] dir : directions) {
                        int ni = i + dir[0], nj = j + dir[1];
                        if (ni >= 0 && ni < n && nj >= 0 && nj < n && grid[ni][nj] > 1) {
                            uniqueIslands.add(grid[ni][nj]);
                        }
                    }
                    for (int id : uniqueIslands) {
                        potentialArea += areaMap.get(id);
                    }
                    maxArea = Math.max(maxArea, potentialArea);
                }
            }
        }

        return maxArea;
    }

    private static int dfs(int[][] grid, int i, int j, int islandId, int[][] directions) {
        int n = grid.length;
        if (i < 0 || i >= n || j < 0 || j >= n || grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = islandId;
        int area = 1;
        for (int[] dir : directions) {
            int ni = i + dir[0], nj = j + dir[1];
            area += dfs(grid, ni, nj, islandId, directions);
        }
        return area;
    }
}

