package contest;

import java.util.ArrayList;
import java.util.Stack;

public class TopoSortDFS {

    public ArrayList<Integer> topoSortDFS(ArrayList<ArrayList<Integer>> adj, int V) {
        boolean[] visited = new boolean[V];
        ArrayList<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (!visited[i])
                dfs(adj, visited, i, stack);
        }
        int i = 0;
        while (i < V) {
            if (!stack.isEmpty()) {
                result.add(stack.pop());
            }
            i++;
        }
        return result;
    }

    public void dfs(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int v, Stack<Integer> stack) {
        visited[v] = true;
            for (int ver : adj.get(v)) {
                if (!visited[ver])
                    dfs(adj, visited, ver, stack);
            }
        stack.push(v);
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int V = 7;
        for (int i = 0; i < V; i++) {
            adj.add(i, new ArrayList<Integer>());
        }
        adj.get(0).add(1);
        adj.get(0).add(2);
        adj.get(0).add(3);
        adj.get(1).add(2);
        adj.get(2).add(4);
        adj.get(2).add(5);
        adj.get(4).add(5);
        adj.get(3).add(5);
        adj.get(2).add(6);

        ArrayList<Integer> ans = new TopoSortDFS().topoSortDFS(adj, V);
        for (Integer i : ans) {
            System.out.print(i + " ");
        }
    }
}