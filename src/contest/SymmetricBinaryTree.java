package contest;

import java.util.*;

public class SymmetricBinaryTree {
    public int isSymmetric(TreeNode A) {
        Queue<Pair> queue = new LinkedList<>();
        Map<Integer, Map<Integer,Integer>> hdMap = new HashMap<>();
        queue.offer(new Pair(A, 0));
        queue.offer(null);
        hdMap.put(0, new HashMap<>());
        hdMap.get(0).put(0, A.val);
        int level = 0;
        while(!queue.isEmpty()){
            Pair currPair = queue.poll();
            if(currPair == null){

            }else{

            }
        }
        return 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(3);
        System.out.println(new SymmetricBinaryTree().isSymmetric(root));
    }
}
 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) {
      val = x;
      left=null;
      right=null;
     }
 }

 class Pair{
    TreeNode node;
    int hd;
    public Pair(TreeNode node, int hd){
        this.hd = hd;
        this.node = node;
    }
 }