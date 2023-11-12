package contest;

public class OddEvenLinkedList {
    public ListNode solve(ListNode A) {
        ListNode oddPtr = A;
        ListNode evenPtr = A.next;
        ListNode resultHead = oddPtr;
        ListNode evenStr = evenPtr;

        while(oddPtr.next!=null && evenPtr.next!=null){
            if(oddPtr.next.next != null){
                ListNode tempOdd = oddPtr.next.next;
                oddPtr.next = tempOdd;
                oddPtr = oddPtr.next;
            }
            if(evenPtr.next.next != null ||  evenPtr.next != null){
                ListNode tempEven = evenPtr.next.next;
                evenPtr.next = tempEven;
                evenPtr = evenPtr.next;
            }
        }
        oddPtr.next = evenStr;
        return resultHead;
    }
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);

        ListNode head1 = new OddEvenLinkedList().solve(head);
        ListNode curr = head;
        while (curr!=null){
            System.out.print(curr.val+" ");
            curr = curr.next;
        }

    }
}