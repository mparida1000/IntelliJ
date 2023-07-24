package com.scaler.tree;

public class SwapNodeValues {
    public TreeNode swapNodes(TreeNode root){
        invert(root);
        return root;
    }
    public TreeNode invert(TreeNode root){
        if(root == null) return null;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invert(root.left);
        invert(root.right);
        return root;
    }

    void preOrder(TreeNode node){
        if (node == null) {
            return;
        }
        preOrder(node.left);
        System.out.print(node.val + " ");
        preOrder(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        SwapNodeValues sn = new SwapNodeValues();
        sn.preOrder(root);
        System.out.println();
        root = sn.swapNodes(root);
        sn.preOrder(root);
    }
}
