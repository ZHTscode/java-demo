package solution.labuladong.linkedList.recursion;

import basic.ListNode;
import utils.ListNodeUtil;

public class ReverseList {
    /* 206. 反转链表 */
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode pre = null, cur = head, nxt = cur.next;
        while(cur != null){
            cur.next = pre;
            pre = cur;
            cur = nxt;
            if(cur != null) nxt = cur.next;
        }
        return pre;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    public static void main(String[] args) {
        ReverseList reverseList = new ReverseList();
        ListNode head = ListNodeUtil.buildList(new int[]{1, 2, 3, 4, 5});
        ListNodeUtil.printList(reverseList.reverseList(head));
    }
}
