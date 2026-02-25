package solution;

import basic.ListNode;
import utils.ListNodeUtil;

public class RemoveNthFromEnd {
    /* 19. 删除链表的倒数第 N 个结点 */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // 移动first指针，使得first和second指针之间间隔n个节点
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // 移动first到链表末尾，保持first和second之间的距离为n
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNodeUtil.buildList(new int[]{1, 2, 3, 4, 5});
        RemoveNthFromEnd r = new RemoveNthFromEnd();
        ListNode result = r.removeNthFromEnd(head, 2);
        ListNodeUtil.printList(result);
    }
}
