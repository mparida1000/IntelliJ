package com.scaler.tree;

public class TreFromPreAndInOrder {
	static int preOrderIndex = 0;
	public static Node buildTree(int inorder[], int preorder[], int n)
    {
        return  buildTree(inorder, preorder, 0, n-1);
    }
    
    private static Node buildTree(int inorder[], int preorder[], int ins, int ine){
        if(ins > ine) return null;
        Node root = new Node(preorder[preOrderIndex++]);
        int index = getRootIndex(inorder, root.data, ins, ine);
        root.left = buildTree(inorder, preorder, ins, index - 1);
        root.right = buildTree(inorder, preorder, index + 1, ine);
        return root;
    }
    static int getRootIndex (int inorder[], int val, int ins, int ine){
        int index =  ins;
        for(int i = ins ; i <= ine ; i++){
            if(inorder[i] == val){
                index = i;
                break;
            }
        }
        return index;
    }
    static void inOrder(Node root) {
		if(root==null) return;
		inOrder(root.left);
		System.out.print(root.data+" ");
		inOrder(root.right);
	}
    
    public static void main(String[] args) {
    	int [] A = {4,2,5,1,6,3,7};
		int [] B = {1,2,4,5,3,6,7};
		Node root = TreFromPreAndInOrder.buildTree(A, B, 7);
		inOrder(root);
	}
}
