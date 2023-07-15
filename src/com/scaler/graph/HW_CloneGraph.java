package com.scaler.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class HW_CloneGraph {

	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        
        // Use a hashmap to keep track of the nodes that have already been cloned
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        UndirectedGraphNode cloneNode = new UndirectedGraphNode(node.label);
        map.put(node, cloneNode);
        
        // Use a queue to do a breadth-first search of the original graph
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(node);
        
        while (!queue.isEmpty()) {
            UndirectedGraphNode curNode = queue.poll();
            UndirectedGraphNode curCloneNode = map.get(curNode);
            
            // Clone all neighbors of current node
            for (UndirectedGraphNode neighbor : curNode.neighbors) {
                UndirectedGraphNode cloneNeighbor = map.get(neighbor);
                if (cloneNeighbor == null) {
                    cloneNeighbor = new UndirectedGraphNode(neighbor.label);
                    map.put(neighbor, cloneNeighbor);
                    queue.offer(neighbor);
                }
                curCloneNode.neighbors.add(cloneNeighbor);
            }
        }
        
        return cloneNode;
    }
	
	
	public static void main(String[] args) {
		UndirectedGraphNode node0 = new UndirectedGraphNode(0);
		UndirectedGraphNode node1 = new UndirectedGraphNode(1);
		UndirectedGraphNode node2 = new UndirectedGraphNode(2);
		UndirectedGraphNode node3 = new UndirectedGraphNode(3);

		node0.neighbors.add(node1);
		node0.neighbors.add(node2);
		node1.neighbors.add(node0);
		node1.neighbors.add(node3);
		node2.neighbors.add(node0);
		node2.neighbors.add(node3);
		node3.neighbors.add(node1);
		node3.neighbors.add(node2);

		HW_CloneGraph solution = new HW_CloneGraph();
		UndirectedGraphNode clonedNode0 = solution.cloneGraph(node0);

		System.out.println(clonedNode0.label); // should output 0
		System.out.println(clonedNode0.neighbors.size()); // should output 2

		UndirectedGraphNode clonedNode1 = clonedNode0.neighbors.get(0);
		System.out.println(clonedNode1.label); // should output 1
		System.out.println(clonedNode1.neighbors.size()); // should output 2

		UndirectedGraphNode clonedNode2 = clonedNode0.neighbors.get(1);
		System.out.println(clonedNode2.label); // should output 2
		System.out.println(clonedNode2.neighbors.size()); // should output 2

		UndirectedGraphNode clonedNode3 = clonedNode1.neighbors.get(1);
		System.out.println(clonedNode3.label); // should output 3
		System.out.println(clonedNode3.neighbors.size()); // should output 2

	}
}
	class UndirectedGraphNode {
	    int label;
	    List<UndirectedGraphNode> neighbors;
	    UndirectedGraphNode(int x) { 
	  	  label = x; 
	  	  neighbors = new ArrayList<UndirectedGraphNode>(); 
	    }
	}
