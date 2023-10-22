package leetcode.graph;

public class LongestIncreasingPathInMatrix {
    private int [][] dirs = {{1,0},{0,-1},{0,1},{-1,0}};
    private int n,m;
    public int longestIncreasingPath(int[][] matrix) {
        int maxLen = 0;
        n = matrix.length;
        m = matrix[0].length;
        int [][] dp = new int [n][m];
        for(int i = 0 ; i < n ; i++){
            for (int j = 0 ; j < m ; j++){
                maxLen = Math.max(maxLen, dfs(matrix, i, j, dp));
            }
        }
        return maxLen;
    }

    public int dfs(int [][] matrix, int x, int y, int [][] dp){
        if(dp[x][y] != 0) return dp[x][y];
        for(int i = 0 ; i < dirs.length ; i++){
            int dx = x + dirs[i][0];
            int dy = y + dirs[i][1];
            if(dx >= 0 && dx < n && dy >= 0 && dy < m && matrix[dx][dy] > matrix[x][y]){
                dp[x][y] = Math.max(dp[x][y], dfs(matrix, dx, dy, dp));
            }
        }
        return ++dp[x][y];
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,2},
                {3,4}
        };
        System.out.println(new LongestIncreasingPathInMatrix().longestIncreasingPath(matrix));
    }

}
