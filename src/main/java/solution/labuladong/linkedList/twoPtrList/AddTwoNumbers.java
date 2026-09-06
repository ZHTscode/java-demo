package solution.labuladong.linkedList.twoPtrList;

import basic.ListNode;
import utils.ListNodeUtil;

public class AddTwoNumbers {
    /* 2. 两数相加 */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 在两条链表上的指针
        ListNode p1 = l1, p2 = l2;
        // 虚拟头结点（构建新链表时的常用技巧）
        ListNode dummy = new ListNode(-1);
        // 指针 p 负责构建新链表
        ListNode p = dummy;
        // 记录进位
        int carry = 0;
        // 开始执行加法，两条链表走完且没有进位时才能结束循环
        while (p1 != null || p2 != null || carry > 0) {
            // 先加上上次的进位
            int val = carry;
            if (p1 != null) {
                val += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                val += p2.val;
                p2 = p2.next;
            }
            // 处理进位情况
            carry = val / 10;
            val = val % 10;
            // 构建新节点
            p.next = new ListNode(val);
            p = p.next;
        }
        // 返回结果链表的头结点（去除虚拟头结点）
        return dummy.next;
    }
    public static void main(String[] args) {
        AddTwoNumbers sol = new AddTwoNumbers();
        ListNode l1 = ListNodeUtil.buildList(new int[]{2, 4, 3});
        ListNode l2 = ListNodeUtil.buildList(new int[]{5, 6, 4});
        ListNode res = sol.addTwoNumbers(l1, l2);
        ListNodeUtil.printList(res);
    }
}
