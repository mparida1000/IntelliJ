package Top150;

public class ReverseNodeInKGroups {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode currGrTail = dummy;
        while(true){
            ListNode currGrEnd = currGrTail;
            for(int i = 0 ; i < k && currGrEnd != null ; i++){
                currGrEnd = currGrEnd.next;
            }
            if(currGrEnd == null)break;
            ListNode currGrStart = currGrTail.next;
            ListNode nextGrStart = currGrEnd.next;
            reverseGroup(currGrStart, currGrEnd);
            currGrStart.next = nextGrStart;
            currGrTail.next = currGrEnd;
            currGrTail = currGrStart;
        }
        return dummy.next;

    }
    private void reverseGroup(ListNode startNode, ListNode endNode) {
        ListNode previousNode = null;
        ListNode currentNode = startNode;
        ListNode nextNode;

        while (previousNode != endNode) { // End becomes the new head
            nextNode = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
    }
    public static void main(String[] args) {
        // Example usage
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);

        ReverseNodeInKGroups sol = new ReverseNodeInKGroups();
        ListNode result = sol.reverseKGroup(head, 4);

        // Print the resulting list
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
}
