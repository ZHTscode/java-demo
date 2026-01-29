package solution;

import basic.ListNode;

public class DetectCycle {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) { // 快慢指针寻找相遇点
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode ptr = head; // 环的入口初始化为头节点
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }// 当ptr和slow相遇时，即为环的入口（画线段图）
                return ptr;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        DetectCycle s = new DetectCycle();
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next;
        System.out.println(s.detectCycle(head).val);
    }
}
