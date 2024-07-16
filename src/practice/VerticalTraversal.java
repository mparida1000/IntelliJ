package practice;

import java.util.*;

public class VerticalTraversal {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        Queue<Pair> queue = new LinkedList<>();
        int minL = Integer.MAX_VALUE;
        int maxL = Integer.MIN_VALUE;
        queue.add(new Pair(root, 0, 0));
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            if (!map.containsKey(p.hd)) {
                ArrayList<Integer> lvlList = new ArrayList<>();
                lvlList.add(p.node.val);
                map.put(p.hd, lvlList);
            } else {
                map.get(p.hd).add(p.node.val);
            }
            if (p.node.left != null) {
                queue.add(new Pair(p.node.left, p.hd - 1, p.vd + 1));
            }
            if (p.node.right != null) {
                queue.add(new Pair(p.node.right, p.hd + 1, p.vd + 1));
            }
            minL = Math.min(minL, p.hd);
            maxL = Math.max(maxL, p.hd);
        }

        for (int i = minL; i <= maxL; i++) {
            ArrayList<Integer> al = map.get(i);
            result.add(al);
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
    int hd;
    int vd;
    public Pair(TreeNode node, int hd, int vd){
        this.node = node;
        this.hd = hd;
        this.vd = vd;
    }
}
