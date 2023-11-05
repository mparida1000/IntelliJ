package leetcode.graph;

import java.util.*;

public class CloneGraph {
    public Node cloneGraph(Node node) {
        Map<Integer, ArrayList<Node>> map = new HashMap<>();
        Set<Integer> visitedAllNeighSet = new HashSet<>();
        dfs(node, map, visitedAllNeighSet);
        return  null;
    }

    Node dfs(Node node, Map<Integer, ArrayList<Node>> map, Set<Integer> visitedAllNeighSet){
        if(!map.containsKey(node.val)){
            map.put(node.val, new ArrayList<>());
        }
        for(Node nd : node.neighbors){
            map.get(node.val).add(nd);
            if(!visitedAllNeighSet.contains(node.val)){
                dfs(nd, map, visitedAllNeighSet);
            }

        }
        visitedAllNeighSet.add(node.val);
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        ArrayList<Node> nodeList = new ArrayList<>();
        nodeList.add(node2);nodeList.add(node4);
        node1.neighbors = nodeList;

        nodeList = new ArrayList<>();
        nodeList.add(node1);nodeList.add(node3);
        node2.neighbors = nodeList;

        nodeList = new ArrayList<>();
        nodeList.add(node2);nodeList.add(node4);
        node3.neighbors = nodeList;

        nodeList = new ArrayList<>();
        nodeList.add(node1);nodeList.add(node3);
        node4.neighbors = nodeList;

        new CloneGraph().cloneGraph(node1);

    }
}
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}