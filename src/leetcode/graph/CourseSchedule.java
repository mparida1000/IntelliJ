package leetcode.graph;

import java.util.ArrayList;
import java.util.Stack;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int n = prerequisites.length;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0 ; i < numCourses; i++){
            adj.add(i, new ArrayList<Integer>());
        }
        for(int i = 0; i < n ; i++){
            int u = prerequisites[i][1];
            int v = prerequisites[i][0];
            adj.get(u).add(v);
        }
        Stack<Integer> stack = new Stack<Integer>();
        boolean [] visited = new boolean[numCourses];
        for(int i = 0 ; i < numCourses ; i++){
            if(!visited[i]){
                dfs(i, visited, adj, stack);
            }
        }
        int i = 0;
        while (i < numCourses) {
            if (!stack.isEmpty()) {
                System.out.println(stack.pop());
            }
            i++;
        }
        return stack.size() == numCourses;
    }

    void dfs(int v, boolean [] visited, ArrayList<ArrayList<Integer>> adj, Stack<Integer> stack){
        visited[v] = true;
        for(int ver : adj.get(v)){
            if(!visited[ver]){
                dfs(ver, visited, adj, stack);
            }
        }
        stack.push(v);
    }


}
