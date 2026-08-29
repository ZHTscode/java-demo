package solution.labuladong.array.twoPtrArray;

import basic.ListNode;

public class DeleteDuplicates {
    /* 83. 删除排序链表中的重复元素 */
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;
        ListNode slow = head, fast = head;
        while(fast != null){
            if(fast.val != slow.val){
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        slow.next = null;
        return head;
    }

    public static void main(String[] args) {
        DeleteDuplicates deleteDuplicates = new DeleteDuplicates();
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);
        ListNode res = deleteDuplicates.deleteDuplicates(head);
        while(res != null){
            System.out.print(res.val + " ");
            res = res.next;
        }
    }
}
