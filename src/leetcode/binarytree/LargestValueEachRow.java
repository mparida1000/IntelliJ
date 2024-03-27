package leetcode.binarytree;

import java.util.*;

/**
 * LEET #515
 * Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).
 */
public class LargestValueEachRow {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> maxMap = new HashMap<>();
        int level = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);
        int max = Integer.MIN_VALUE;
        int maxL = 0;
        while(!queue.isEmpty()){
            TreeNode cuurNode = queue.poll();
            if(cuurNode == null){
                if(queue.size() > 0) {
                    queue.offer(null);
                    level++;
                    maxL = Math.max(maxL, level);
                    max = Integer.MIN_VALUE;
                }
            }else{
                max = Math.max(max, cuurNode.val);
                maxMap.put(level, max);
                if(cuurNode.left != null){
                    queue.offer(cuurNode.left);
                }
                if(cuurNode.right != null){
                    queue.offer(cuurNode.right);
                }
            }
        }
        for(int i = 0 ; i <= maxL ; i++){
            result.add(maxMap.get(i));
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);

        List<Integer> result = new LargestValueEachRow().largestValues(root);
        for(Integer i : result)
            System.out.print(i  +" ");
    }
}
