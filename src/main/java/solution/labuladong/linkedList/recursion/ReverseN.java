package solution.labuladong.linkedList.recursion;

import basic.ListNode;
import utils.ListNodeUtil;

public class ReverseN {
    /* 反转链表的前 n 个节点 */
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

    // 后驱节点
    ListNode successor = null;
    // 反转以 head 为起点的 n 个节点，返回新的头结点
    ListNode reverseN2(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN2(head.next, n - 1);
        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        // System.out.println(last.val); // 始终为反转后的头节点
        return last;
    }

    public static void main(String[] args) {
        ReverseN reverseN = new ReverseN();
        ListNode head = ListNodeUtil.buildList(new int[]{1, 2, 3, 4, 5});
        ListNodeUtil.printList(reverseN.reverseN2(head, 3));
    }
}
