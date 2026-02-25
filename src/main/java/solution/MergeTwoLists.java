package solution;

import basic.ListNode;
import utils.ListNodeUtil;

// 与 SortList.java 相同，都使用归并排序的思想
// 将链表从中间断开，分别对两部分进行排序，然后合并两个有序链表

public class MergeTwoLists {
    /* 21.合两个有序链表 */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0); // 虚拟头节点
        ListNode cur = head;
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
        cur.next = l1 == null ? l2 : l1;
        return head.next;
    }

    public static void main(String[] args) {
        MergeTwoLists mergeTwoLists = new MergeTwoLists();
        ListNode l1 = ListNodeUtil.buildList(new int[]{1, 2, 4});
        ListNode l2 = ListNodeUtil.buildList(new int[]{1, 3, 4});
        ListNode head = mergeTwoLists.mergeTwoLists(l1, l2);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}
