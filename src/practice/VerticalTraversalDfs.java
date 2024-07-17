package practice;

import java.util.*;

public class VerticalTraversalDfs {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        dfs(root, 0, 0, map);
        for(TreeMap<Integer, PriorityQueue<Integer>> rows : map.values()){
            List<Integer> column = new ArrayList<>();
            for(PriorityQueue<Integer> pq : rows.values()){
                while(!pq.isEmpty()){
                    column.add(pq.poll());
                }
            }
            result.add(column);
        }
        return result;
    }
    public void dfs(TreeNode node, int row, int col, TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map){
        if(node == null){
            return;
        }
        map.putIfAbsent(col, new TreeMap<>());
        map.get(col).putIfAbsent(row, new PriorityQueue<>());
        map.get(col).get(row).offer(node.val);

        dfs(node.left, row+1, col-1, map);
        dfs(node.right, row+1, row+1, map);
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        VerticalTraversal vt = new VerticalTraversal();
        System.out.println(vt.verticalTraversal(root));

    }
}
