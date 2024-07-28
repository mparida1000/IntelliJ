package practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CountUnreachablePairsOfNodesInGraph {

    public long countPairs(int n, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i = 0 ; i < n ; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0 ; i < edges.length ; i++){
            adjList.get(edges[i][0]).add(edges[i][1]);
            adjList.get(edges[i][1]).add(edges[i][0]);
        }
        boolean [] isVisited = new boolean[n];
        List<Integer> sizes  =new ArrayList<>();
        for(int i = 0 ; i < n ; i++){
            if(!isVisited[i]) {
                int size = bfs(adjList, isVisited, i);
                sizes.add(size);
            }
        }
        long totPairs = (long)n*(n-1)/2;
        long reachablePairs = 0;
        for(int size : sizes){
            reachablePairs += (long)size*(size-1)/2;
        }
        return (int) (totPairs - reachablePairs);
    }
    private int bfs(List<List<Integer>> graph, boolean[] visited, int edge) {
        int size = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(edge);
        visited[edge] = true;
        while(!queue.isEmpty()){
            int ver = queue.poll();
            for(int neigh : graph.get(ver)){
                if(!visited[neigh]){
                    queue.offer(neigh);
                    size++;
                    visited[neigh] = true;
                }
            }
        }
        return size;
    }

    public static void main(String[] args) {
        int n = 7;
        int[][] edges = {
                {0, 2},
                {0, 5},
                {2, 4},
                {1, 6},
                {5, 4}
        };
        CountUnreachablePairsOfNodesInGraph solution = new CountUnreachablePairsOfNodesInGraph();
        System.out.println(solution.countPairs(7, edges));
    }
}
