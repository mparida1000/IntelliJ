package contest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ToSoBFS {
    public ArrayList<Integer> tso(ArrayList<ArrayList<Integer>> adj, int V){
        int [] indeg = new int[V];
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for(ArrayList<Integer> list : adj){
            for(Integer v : list){
                indeg[v]++;
            }
        }
        for(int i = 0 ; i < V; i++){
            if(indeg[i] == 0){
                q.offer(i);
            }
        }
        while(!q.isEmpty()){
            int v = q.poll();
            ans.add(v);
            for(int neigh : adj.get(v)){
                if(--indeg[neigh] == 0){
                    q.offer(neigh);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int V  = 7;
        for(int i = 0 ; i < V ; i++){
            adj.add(i,new ArrayList<Integer>());
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

        ArrayList<Integer> ans = new ToSoBFS().tso(adj, V);
        for(Integer i : ans){
            System.out.print(i+" ");
        }

    }
}

