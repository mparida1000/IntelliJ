//package com.scaler.tree;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
//public class LeastCommonAncestorInBT {
//
//	public int lca(TreeNode A, int B, int C) {
//		preOrder(A);
//		TreeNode curr = A;
//		while(curr != null) {
//			if(isAncestor(curr.left, B, inTime, outTime ) && isAncestor(curr.left, C, inTime, outTime )) {
//				curr = curr.left;
//			}else if(isAncestor(curr.right, B, inTime, outTime ) && isAncestor(curr.right, C, inTime, outTime )) {
//				curr = curr.right;
//			}else
//				return curr.val;
//		}
//		return A.val;
//    }
//	Map<TreeNodeWrapper, Integer> inTime = new HashMap<>();
//	Map<TreeNodeWrapper, Integer> outTime =  new HashMap<>();
//	int t = 0;
//	public void preOrder(TreeNode root) {
//		if(root == null) {
//			return;
//		}
//
//		if(!inTime.containsKey(root)) {
//			TreeNodeWrapper wrapper = new TreeNodeWrapper(root, t++, 0);
//			inTime.put(wrapper, wrapper.getInTime());
//		}
//		preOrder(root.left);
//		if(!outTime.containsKey(root)) {
//			TreeNodeWrapper wrapper = new TreeNodeWrapper(root, 0, t++);
//			outTime.put(root, t++);
//		}
//		preOrder(root.right);
//	}
//
//	public boolean isAncestor(TreeNode a, int b, Map<TreeNode, Integer> inTime, Map<TreeNode, Integer> outTime) {
//        return inTime.get(a) < inTime.get(new TreeNode(b)) && outTime.get(a) > outTime.get(new TreeNode(b));
//    }
//
//	public static void main(String[] args) {
//		TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.left.left = new TreeNode(4);
//        root.left.right = new TreeNode(5);
//        root.right.left = new TreeNode(6);
//        root.right.right = new TreeNode(7);
//
//        int p = 4; // node 4
//        int q = 7; // node 7
//        LeastCommonAncestorInBT labt = new LeastCommonAncestorInBT();
//        System.out.println(labt.lca(root, p, q));
//	}
//}
//
//class TreeNodeWrapper {
//    TreeNode node;
//    int inTime;
//    int outTime;
//
//    public TreeNodeWrapper(TreeNode node, int inTime, int outTime) {
//        this.node = node;
//        this.inTime = inTime;
//        this.outTime = outTime;
//    }
//
//    public TreeNode getNode() {
//        return node;
//    }
//
//    public int getInTime() {
//        return inTime;
//    }
//
//    public int getOutTime() {
//        return outTime;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(node.val, inTime, outTime);
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null || getClass() != obj.getClass()) {
//            return false;
//        }
//        TreeNodeWrapper other = (TreeNodeWrapper) obj;
//        return Objects.equals(node, other.node)
//            && inTime == other.inTime
//            && outTime == other.outTime;
//    }
//}
