package contest;

public class MoveStraightLine {
    static int maxLen = Integer.MIN_VALUE;
    final static int [] X = {1,1,0,-1,-1,-1,0,1};
    final static int [] Y = {0,-1,-1,-1,0,1,1,1};
    public int maxDist(int [][] A){
        int n = A.length;
        int m = A[0].length;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(A[i][j] == 1){
                    dfs(A, 0, i, j, 0, 0);
                }
            }
        }
        return maxLen;
    }

    public void dfs(int [][] A, int len, int i, int j, int x, int y){
        int l = 0;
        if(A[i][j] == 1){
            l = len+1;
            maxLen = Math.max(maxLen, l);
        }
        if(x == 0 && y == 0){
            for(int k = 0 ; k < 8 ; k++){
                if(isValid(i+X[k], j+Y[k], A)){
                    dfs(A, l, i+X[k], j+Y[k], X[k], Y[k]);
                }
            }
        }else{
            if(isValid(i+x, j+y, A) && A[i+x][j+y] == 1){
                dfs(A, l, i+x, j+y, x, y);
            }else{
                return;
            }
        }
    }
    public boolean isValid(int x, int y, int [][] A){
        return (x >= 0 && x < A.length && y >= 0 && y < A[0].length);
    }

    public static void main(String[] args) {
        /*int [][] A = {
                {0,1,0,1},
                {1,1,1,1},
                {1,0,1,1}
        };*/
        /*int [][] A = {
                {1,1,0,0},
                {0,1,1,0},
                {1,0,0,0},
                {1,1,1,0}
        };*/
        int [][] A = {
                {0,0},
                {1,1},
                {0,1}
        };

        System.out.println(new MoveStraightLine().maxDist(A));

    }

}
