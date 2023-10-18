package leetcode.graph;

import java.util.ArrayList;

public class GraphValidTree {
    public static boolean isValidTree = true;
    public boolean validTree(int n, int[][] edges) {
        int len = edges.length;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0 ; i < n ; i++){
            adj.add(i, new ArrayList<Integer>());
        }
        for(int i = 0 ; i < len ; i++){
            int u = edges[i][0];
            int v = edges[i][1];
            adj.get(u).add(v);
        }
        boolean [] visited = new boolean[n];
        //for(int i = 0 ; i < n ; i++){
            dfs(0, visited, adj);
        //}
        return isValidTree;
    }

    void dfs(int v, boolean [] visited, ArrayList<ArrayList<Integer>> adj){
        visited[v] = true;
        for (int ver : adj.get(v)) {
            if (isValidTree && !visited[ver]){
                dfs(ver, visited, adj);
            }else{
                isValidTree =  false;
                return;
            }
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int [][] arr = {
                {0,1},
                {0,2},
                {2,3},
                {2,4}
        };
        System.out.println(new GraphValidTree().validTree(n, arr));
    }
}
