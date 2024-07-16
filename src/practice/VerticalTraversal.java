package practice;

import java.util.*;

public class VerticalTraversal {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0,0));
        while(!queue.isEmpty()){
            Pair p = queue.poll();
            TreeNode node = p.node;
            int row = p.row;
            int col = p.col;
            map.putIfAbsent(col, new TreeMap<>());
            map.get(col).putIfAbsent(row, new PriorityQueue<>());
            map.get(col).get(row).offer(node.val);
            if(node.left != null){
                queue.offer(new Pair(node.left, row+1, col-1));
            }
            if(node.right != null){
                queue.offer(new Pair(node.right, row+1, col+1));
            }
        }
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

class Pair{
    TreeNode node;
    int row;
    int col;
    public Pair(TreeNode node, int row, int col){
        this.node = node;
        this.row = row;
        this.col = col;
    }
}
