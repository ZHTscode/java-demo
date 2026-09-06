package solution.labuladong.linkedList.recursion;

import basic.ListNode;
import utils.ListNodeUtil;

public class IsPalindrome {
    /* 234. 回文链表 */
    public boolean isPalindrome(ListNode head) {
        ListNode slow, fast;
        slow = fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        } // 节点数为奇数时，slow 指向中间节点；为偶数时，指向中间靠右的节点
        ListNode left = head;
        ListNode right = reverse(slow); // 反转后半部分链表
        while (right != null) {
            if (left.val != right.val)
                return false;
            left = left.next;
            right = right.next;
        }
        return true;
    }
    ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        IsPalindrome isPalindrome = new IsPalindrome();
        ListNode head = ListNodeUtil.buildList(new int[]{1, 2, 3, 3, 2, 1});
        System.out.println(isPalindrome.isPalindrome(head));
    }
}
