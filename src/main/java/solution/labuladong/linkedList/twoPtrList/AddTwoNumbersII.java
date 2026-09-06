package solution.labuladong.linkedList.twoPtrList;

import basic.ListNode;
import utils.ListNodeUtil;

import java.util.ArrayDeque;
import java.util.Deque;

public class AddTwoNumbersII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 把链表元素转入栈中
        Deque<Integer> stk1 = new ArrayDeque<>();
            while (l1 != null) {
            stk1.push(l1.val);
            l1 = l1.next;
        }
        Deque<Integer> stk2 = new ArrayDeque<>();
            while (l2 != null) {
            stk2.push(l2.val);
            l2 = l2.next;
        }

        // 虚拟头结点（构建新链表时的常用技巧）
        ListNode dummy = new ListNode(-1);

        // 记录进位
        int carry = 0;
        // 开始执行加法，两条链表走完且没有进位时才能结束循环
            while (!stk1.isEmpty() || !stk2.isEmpty() || carry > 0) {
            // 先加上上次的进位
            int val = carry;
            if (!stk1.isEmpty()) {
                val += stk1.pop();
            }
            if (!stk2.isEmpty()) {
                val += stk2.pop();
            }
            // 处理进位情况
            carry = val / 10;
            val = val % 10;
            // 构建新节点，直接接在 dummy 后面
            ListNode newNode = new ListNode(val);
            newNode.next = dummy.next;
            dummy.next = newNode;
        }
        // 返回结果链表的头结点（去除虚拟头结点）
            return dummy.next;
    }

    public static void main(String[] args) {
        AddTwoNumbersII solution = new AddTwoNumbersII();
        ListNode l1 = ListNodeUtil.buildList(new int[]{1, 2, 3});
        ListNode l2 = ListNodeUtil.buildList(new int[]{2, 3, 4});
        ListNode result = solution.addTwoNumbers(l1, l2);
        ListNodeUtil.printList(result);
    }
}
