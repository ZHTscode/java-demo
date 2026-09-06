package solution.labuladong.linkedList.recursion;

import basic.ListNode;
import utils.ListNodeUtil;

import java.util.List;

public class ReverseBetween {
    /* 92. 反转链表 II */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left == 1) return reverseN(head, right);
        ListNode pre = head;
        for(int i = 1; i < left - 1; i++){
            pre = pre.next;
        }
        pre.next = reverseN(pre.next, right - left + 1);
        return head;
    }
    public ListNode reverseN(ListNode head, int n) {
        if(head == null || head.next == null) return head;
        ListNode pre = null, cur = head, nxt = cur.next;
        while(n > 0){
            cur.next = pre;
            pre = cur;
            cur = nxt;
            if(nxt != null) nxt = nxt.next;
            n--;
        }
        head.next = cur;
        return pre;
    }

    public ListNode reverseBetween2(ListNode head, int m, int n) {
        if (m == 1) { // base case
            return reverseN(head, n);
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween2(head.next, m - 1, n - 1);
        return head;
    }
    // 后驱节点
    ListNode successor = null;
    // 反转以 head 为起点的 n 个节点，返回新的头结点
    ListNode reverseN2(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        ListNode last = reverseN2(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }

    public static void main(String[] args) {
        ReverseBetween reverseBetween = new ReverseBetween();
        ListNode head = ListNodeUtil.buildList(new int[]{1, 2, 3, 4, 5});
        ListNodeUtil.printList(reverseBetween.reverseBetween2(head, 2, 4));
    }
}
