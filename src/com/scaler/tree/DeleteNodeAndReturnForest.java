package com.scaler.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeleteNodeAndReturnForest {
    List<TreeNode> result = new ArrayList();
    Set<Integer> set = new HashSet();
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        for(int i : to_delete) set.add(i);
        root = dfs(root);
        if(root != null) result.add(root);
        return result;
    }

    private TreeNode dfs(TreeNode node){
        if(node == null) return null;
        node.left = dfs(node.left);
        node.right = dfs(node.right);
        if(set.contains(node.val)){
            if(node.left != null) result.add(node.left);
            if (node.right != null) result.add(node.right);
            node = null;
        }
        return node;
    }
    public static void main(String[] args) {
        DeleteNodeAndReturnForest ep = new DeleteNodeAndReturnForest();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        List<TreeNode> list= ep.delNodes(root, new int[]{3,5});
        for(TreeNode node : list){
            System.out.print(node.val+" ");
        }
    }
}
