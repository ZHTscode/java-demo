package solution.labuladong.linkedList.divideConquer;

import basic.ListNode;
import utils.ListNodeUtil;

import java.util.PriorityQueue;

public class MergeKListsII {
    /* 23. 合并K个升序链表 */
    // 用分治算法合并 k 个有序链表
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return mergeKListsHelper(lists, 0, lists.length - 1);
    }
    // 定义：合并 lists[start..end] 为一个有序链表
    ListNode mergeKListsHelper(ListNode[] lists, int start, int end) {
        if (start == end) return lists[start];
        int mid = start + (end - start) / 2;
        // 合并左半边 lists[start..mid] 为一个有序链表
        ListNode left = mergeKListsHelper(lists, start, mid);
        // 合并右半边 lists[mid+1..end] 为一个有序链表
        ListNode right = mergeKListsHelper(lists, mid + 1, end);
        // 合并左右两个有序链表
        return mergeTwoLists(left, right);
    }
    // 双指针技巧合并两个有序链表
    ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1), p = dummy;
        ListNode p1 = l1, p2 = l2;
        while (p1 != null && p2 != null) {
            if (p1.val > p2.val) {
                p.next = p2;
                p2 = p2.next;
            } else {
                p.next = p1;
                p1 = p1.next;
            }
            p = p.next;
        }
        if (p1 != null) p.next = p1;
        if (p2 != null) p.next = p2;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[3];
        ListNode l1 = ListNodeUtil.buildList(new int[]{1, 4, 5});
        ListNode l2 = ListNodeUtil.buildList(new int[]{1, 3, 4});
        ListNode l3 = ListNodeUtil.buildList(new int[]{2, 6});
        lists[0] = l1;
        lists[1] = l2;
        lists[2] = l3;
        ListNode mergedList = new MergeKListsII().mergeKLists(lists);
        ListNodeUtil.printList(mergedList);
    }
}
