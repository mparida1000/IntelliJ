package leetcode.binarytree;

import java.util.Arrays;
/*
You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i],
return true if and only if all the given nodes form exactly one valid binary tree.

If node i has no left child then leftChild[i] will equal -1, similarly for the right child.

Note that the nodes have no values and that we only use the node numbers in this problem.
 */
public class ValidateBinaryTreeNodes {

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        boolean [] visited = new boolean[n];
        int [] parent = new int[n];
        Arrays.fill(parent, -1);
        for(int i = 0 ; i < n ; i++){
            if(leftChild[i] != -1 && !visited[leftChild[i]]){
                visited[leftChild[i]] = true;
                parent[leftChild[i]] = i;
                if(parent[i] == leftChild[i]) return false;

            }else if(leftChild[i] != -1 && visited[leftChild[i]]){
                return false;
            }
            if(rightChild[i] != -1 && !visited[rightChild[i]]){
                visited[rightChild[i]] = true;
                parent[rightChild[i]] = i;
                if(parent[i] == rightChild[i]) return false;
            }else if(rightChild[i] != -1 && visited[rightChild[i]]){
                return false;
            }
        }
        int count = 0;
        for(int i = 0 ; i < n ; i++){
            if(!visited[i]){
                count++;
                if(count > 1) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        int[] leftChild = {1,-1,3,-1};
//        int[] rightChild = {2,3,-1,-1};

//        int[] leftChild = {1,3,-1,4,-1,-1,7,-1,-1,-1};
//        int[] rightChild = {2,5,6,9,-1,-1,-1,8,-1,-1};//true

//        int[] leftChild = {1,-1,-1,4,-1,-1};
//        int[] rightChild = {2,-1,-1,5,-1,-1};//false

//        int[] leftChild = {3,-1,1,-1};
//        int[] rightChild = {-1,-1,0,-1};//true
//        int[] leftChild = {1,0};
//        int[] rightChild = {-1,-1};//false
        int[] leftChild = {1,2,0,-1};
        int[] rightChild = {-1,-1,-1,-1};//false
        System.out.println(new ValidateBinaryTreeNodes().validateBinaryTreeNodes(4, leftChild, rightChild));
    }
}
