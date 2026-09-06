package solution.labuladong.linkedList.recursion;

import basic.ListNode;
import utils.ListNodeUtil;

public class ReverseKGroup {
    /* 25. K 个一组翻转链表 */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null) return null;
        ListNode a = head, b = head;
        for(int i=0; i<k; i++){
            if(b == null) return head;
            b = b.next;
        } // b 指向下一组的头节点
        ListNode newHead = reverseN(a, k);
        a.next = reverseKGroup(b, k);
        return newHead;
    }
    ListNode reverseN(ListNode head, int n) {
        if(head == null || head.next == null) return head;
        ListNode pre = null, cur = head, nxt = cur.next;
        while( n > 0){
            cur.next = pre;
            pre = cur;
            cur = nxt;
            if(nxt != null) nxt = nxt.next;
            n--;
        }
        head.next = cur;
        return pre;
    }

    public static void main(String[] args) {
        ReverseKGroup reverseKGroup = new ReverseKGroup();
        ListNode head = ListNodeUtil.buildList(new int[]{1, 2, 3, 4, 5});
        ListNodeUtil.printList(reverseKGroup.reverseKGroup(head, 2));
    }
}
