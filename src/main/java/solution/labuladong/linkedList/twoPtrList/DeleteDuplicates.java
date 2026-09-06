package solution.labuladong.linkedList.twoPtrList;

import basic.ListNode;
import utils.ListNodeUtil;

public class DeleteDuplicates {
    /* 82. 删除排序链表中的重复元素|| */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            // 判断后面两个节点值是否相等
            if (cur.next.val == cur.next.next.val) {
                int val = cur.next.val;
                // 把所有等于val的全部跳过
                while (cur.next != null && cur.next.val == val) {
                    cur.next = cur.next.next;
                }
            } else {
                // 不重复，cur往后走
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        DeleteDuplicates sol = new DeleteDuplicates();
        ListNode head = ListNodeUtil.buildList(new int[]{1, 2, 3, 3, 4, 4, 5});
        ListNodeUtil.printList(sol.deleteDuplicates(head));
    }
}
