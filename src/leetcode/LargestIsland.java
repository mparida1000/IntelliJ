package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LargestIsland {
    private static final int [][] dirs = {{1, 0},{0, 1},{-1, 0},{0, -1}};
    public static void main(String[] args) {
        int[][] grid1 = {{1, 0, 1, 0}, {1, 0, 1, 1}, {1, 0, 0, 0}, {0, 0, 1, 0}};
        System.out.println(largestIsland(grid1)); // Output: 3
    }
    public static int largestIsland(int[][] grid) {
        int n = grid.length;
        int islandId = 2;
        int maxArea = 0;
        Map<Integer, Integer> areaMap = new HashMap<>();
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(i, j, grid, islandId);
                    areaMap.put(islandId, area);
                    maxArea = Math.max(maxArea, area);
                    islandId++;
                }
            }
        }

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                if(grid[i][j] == 0){
                    Set<Integer> islands = new HashSet<>();
                    int currArea = 1;
                    for(int []  dir : dirs){
                        int dx = i + dir[0];
                        int dy = j + dir[1];
                        if(dx >= 0 && dx < n && dy >= 0 && dy < n && grid[dx][dy] > 1){
                            islands.add(grid[dx][dy]);
                        }
                    }
                    for(Integer areaId : islands){
                        currArea+=areaMap.get(areaId);
                    }
                    maxArea = Math.max(maxArea, currArea);
                }
            }
        }
        return maxArea;
    }

    public static int dfs(int row, int col, int [][] grid, int islandId){
        if(row < 0 || row >= grid.length || col < 0 || col >= grid.length || grid[row][col] != 1){
            return 0;
        }
        int area = 1;
        grid[row][col] = islandId;
        for(int [] dir : dirs){
            int dx = row+dir[0];
            int dy = col+dir[1];
            area += dfs(dx,dy,grid,islandId);
        }
        return area;
    }
}
