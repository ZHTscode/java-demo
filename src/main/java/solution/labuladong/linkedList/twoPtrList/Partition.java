package solution.labuladong.linkedList.twoPtrList;

import basic.ListNode;
import utils.ListNodeUtil;

public class Partition {
    /* 86. 分隔链表 */
    public ListNode partition(ListNode head, int x) {
        // 存放小于 x 的链表的虚拟头结点
        ListNode dummy1 = new ListNode(-1);
        // 存放大于等于 x 的链表的虚拟头结点
        ListNode dummy2 = new ListNode(-1);
        // p1, p2 指针负责生成结果链表
        ListNode p1 = dummy1, p2 = dummy2;
        // p 负责遍历原链表，类似合并两个有序链表的逻辑
        // 这里是将一个链表分解成两个链表
        ListNode p = head;
        while (p != null) {
            if (p.val >= x) {
                p2.next = p;
                p2 = p2.next;
            } else {
                p1.next = p;
                p1 = p1.next;
            }
            ListNode temp = p.next;
            p.next = null; // 断开原链表中的每个节点的 next 指针，避免成环
            p = temp;
        }
        // 链接两个链表
        p1.next = dummy2.next;

        return dummy1.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNodeUtil.buildList(new int[]{1, 4, 3, 2, 5, 2});
        ListNode listNode = new Partition().partition(head, 3);
        ListNodeUtil.printList(listNode);
    }
}
