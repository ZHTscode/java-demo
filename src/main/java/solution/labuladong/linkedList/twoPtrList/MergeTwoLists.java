package solution.labuladong.linkedList.twoPtrList;

import basic.ListNode;

public class MergeTwoLists {
    /* 21. 合并两个有序链表 */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        MergeTwoLists mergeTwoLists = new MergeTwoLists();
        ListNode listNode = mergeTwoLists.mergeTwoLists(l1, l2);
        System.out.println(listNode);
    }
}
