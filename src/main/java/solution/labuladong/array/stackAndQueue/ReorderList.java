package solution.labuladong.array.stackAndQueue;

import basic.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class ReorderList {
    /* 143. 重排链表 */
    public void reorderList(ListNode head) {
        Deque<ListNode> stk = new ArrayDeque<>();
        // 先把所有节点装进栈里，得到倒序结果
        ListNode p = head;
        while (p != null) {
            stk.push(p);
            p = p.next;
        }
        p = head;
        while (p != null) {
            ListNode lastNode = stk.pop(); // 链表尾部的节点
            ListNode next = p.next;
            if (lastNode == next || lastNode.next == next) { // 结束条件，节点数为奇、偶时均适用
                lastNode.next = null;
                break;
            }
            p.next = lastNode;
            lastNode.next = next;
            p = next;
        }
    }

    public static void main(String[] args) {
        ReorderList rl = new ReorderList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        rl.reorderList(head);
        ListNode p = head;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
    }
}
