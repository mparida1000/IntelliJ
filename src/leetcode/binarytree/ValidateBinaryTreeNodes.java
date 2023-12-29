package leetcode.binarytree;

import java.util.*;

/**
 *
You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i],
return true if and only if all the given nodes form exactly one valid binary tree.

If node i has no left child then leftChild[i] will equal -1, similarly for the right child.

Note that the nodes have no values and that we only use the node numbers in this problem.
Approach: Find root and then run BFS
    to find root, you can add all nodes to set and which ever node not present is set is t he root
    or use in-degree to find root, root in-degree will be zero, > 1 with zero in-degree, return false;
 */
public class ValidateBinaryTreeNodes {

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int root = findRoot(n, leftChild, rightChild);
        if(root == -1) return false;
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(root);
        visited.add(root);
        while(!queue.isEmpty()){
            int node = queue.poll();
            if(visited.contains(leftChild[node])) return false;
            if(leftChild[node] != -1){
                queue.offer(leftChild[node]);
                visited.add(leftChild[node]);
            }
            if(visited.contains(rightChild[node])) return false;
            if(rightChild[node] != -1){
                queue.offer(rightChild[node]);
                visited.add(rightChild[node]);
            }
        }
        return visited.size() == n;
    }

    private int findRoot(int n, int [] leftChild, int [] rightChild){
        Set<Integer> childSet = new HashSet<>();
        for(int i : leftChild){
            childSet.add(i);
        }
        for(int i : rightChild){
            childSet.add(i);
        }
        for(int i = 0 ; i < n ; i++){
            if(!childSet.contains(i)) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
//        int[] leftChild = {1,-1,3,-1};
//        int[] rightChild = {2,3,-1,-1};

        int[] leftChild = {1,3,-1,4,-1,-1,7,-1,-1,-1};
        int[] rightChild = {2,5,6,9,-1,-1,-1,8,-1,-1};//true

//        int[] leftChild = {1,-1,-1,4,-1,-1};
//        int[] rightChild = {2,-1,-1,5,-1,-1};//false

//        int[] leftChild = {3,-1,1,-1};
//        int[] rightChild = {-1,-1,0,-1};//true
//        int[] leftChild = {1,0};
//        int[] rightChild = {-1,-1};//false
//        int[] leftChild = {1,2,0,-1};
//        int[] rightChild = {-1,-1,-1,-1};//false
        System.out.println(new ValidateBinaryTreeNodes().validateBinaryTreeNodes(10, leftChild, rightChild));
    }
}
