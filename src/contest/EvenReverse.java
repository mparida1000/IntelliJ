package contest;
/*
Given a LinkedList, reverse the order of all nodes at even position
 */
public class EvenReverse {
    public ListNode solve(ListNode A){
        int count = 0;
        ListNode slowPtr = A;
        ListNode fastPtr = A;
        while(fastPtr!=null && fastPtr.next!=null){
            slowPtr=slowPtr.next;
            fastPtr=fastPtr.next.next;
            count++;
        }
        ListNode next = null;
        ListNode prev = null;
        ListNode curr = slowPtr;
        while(curr!=null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count++;
        }
        ListNode B = prev;
        ListNode backEvenNode;
        if(count%2==0){

            backEvenNode = B;
        }else{
            backEvenNode = B.next;
        }
        ListNode frontEvenNode = A.next;
        int temp;
        while(frontEvenNode!=null && backEvenNode!=null && frontEvenNode != backEvenNode){//&& frontEvenNode.next!=backEvenNode.next
            temp = frontEvenNode.val;
            frontEvenNode.val = backEvenNode.val;
            backEvenNode.val = temp;
            frontEvenNode = frontEvenNode.next.next;
            backEvenNode = backEvenNode.next.next;
        }
        curr = B;
        next = null;
        prev = null;
        while(curr!=null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return A;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(2);
        node.next = new ListNode(7);
        node.next.next = new ListNode(6);
        /*node.next.next.next = new ListNode(6);
        node.next.next.next.next = new ListNode(7);
        node.next.next.next.next.next = new ListNode(9);*/
        ListNode res = new EvenReverse().solve(node);
    }
}
