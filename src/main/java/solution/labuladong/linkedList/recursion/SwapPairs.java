package solution.labuladong.linkedList.recursion;

import basic.ListNode;
import utils.ListNodeUtil;

public class SwapPairs {
    /* 24. 两两交换链表中的节点 */
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode newHead = head.next;
        ListNode temp = head.next.next;
        head.next.next = head;
        head.next = swapPairs(temp);
        return newHead;
    }

    public ListNode swapPairs2(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0, head);
        ListNode slowPre = dummy;
        ListNode slow = head;
        ListNode fast = head.next;
        ListNode fastNext = fast.next;

        while(true){
            slowPre.next = fast;
            fast.next = slow;
            slow.next = fastNext;

            slowPre = slow;
            slow = fastNext;
            if(slow == null || slow.next == null) break;
            fast = slow.next;
            fastNext = fast.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNodeUtil.buildList(new int[]{1, 2, 3, 4, 5});
        SwapPairs swapPairs = new SwapPairs();
        ListNodeUtil.printList(swapPairs.swapPairs(head));
    }
}
