package practice;

public class BSTToCircularDLL {
    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };
    private Node first = null;
    private Node last = null;
    public Node treeToDoublyList(Node root) {
        inorder(root);
        last.right = first;
        first.left = last;
        return first;
    }
    public void inorder(Node node){
        if(node == null) return;
        inorder(node.left);
        if(last!=null){
            last.right = node;
            node.left = last;
        }else{
            first = node; // applicable for only left most node i.e. smallest
        }
        last  = node;
        inorder(node.right);
    }
    public static void main(String[] args) {
        BSTToCircularDLL converter = new BSTToCircularDLL();

        Node root = new Node(4);
        root.left = new Node(2);
        root.right = new Node(5);
        root.left.left = new Node(1);
        root.left.right = new Node(3);

        Node head = converter.treeToDoublyList(root);

        // Print the Circular Doubly-Linked List
        Node current = head;
        do {
            System.out.print(current.val + " ");
            current = current.right;
        } while (current != head);
    }
}
