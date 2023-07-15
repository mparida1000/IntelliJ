package com.scaler.graph;
/*
 * Initialize a boolean array visited[] of size A, and initialize all its elements to false.
For each node i from 1 to A, if it is not visited, do the following:
a. Enqueue the node i into the queue.
b. Mark node i as visited.
c. While the queue is not empty, do the following:
i. Dequeue a node u from the queue.
ii. For each neighbor v of u, if v is not visited, do the following:
1. Enqueue node v into the queue.
2. Mark node v as visited.
3. Add node v to the parent[] array, with parent[v] = u.
4. If node v is already visited and its parent is not u, then a cycle is detected and we can return 1.
If no cycle is detected, return 0.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CycleInGraphBFS {

	public int solve(int A, int[][] B) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < A; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < B.length; i++) {
            adj.get(B[i][0]).add(B[i][1]);
        }
        boolean[] visited = new boolean[A+1];
        Arrays.fill(visited, false);
        int[] parent = new int[A+1];
        Arrays.fill(parent, -1);
        for (int i = 0; i < A; i++) {
            if (!visited[i]) {
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                visited[i] = true;
                while (!queue.isEmpty()) {
                    int u = queue.poll();
                    for (int v : adj.get(u)) {
                        if (!visited[v]) {
                            queue.add(v);
                            visited[v] = true;
                            parent[v] = u;
                        } else if (parent[u] != v) {
                            return 1;
                        }
                    }
                }
            }
        }
        return 0;
    }
	public static void main(String[] args) {
		int [][] B = {
				{1,2},
				{2,4},
				{4,3},
				{3,1},
				{4,5}
				
		};
		System.out.println(new CycleInGraphBFS().solve(5, B));
	}
}
