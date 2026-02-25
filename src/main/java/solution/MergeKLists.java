package solution;

import basic.ListNode;
import utils.ListNodeUtil;

public class MergeKLists {
    /* 23.合K个升序链表 */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeKListsHelper(lists, 0, lists.length - 1);
    }
    private ListNode mergeKListsHelper(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        int mid = left + (right - left) / 2;
        ListNode l1 = mergeKListsHelper(lists, left, mid);
        ListNode l2 = mergeKListsHelper(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {
        MergeKLists mergeKLists = new MergeKLists();
        ListNode l1 = ListNodeUtil.buildList(new int[]{1, 4, 5});
        ListNode l2 = ListNodeUtil.buildList(new int[]{1, 3, 4});
        ListNode l3 = ListNodeUtil.buildList(new int[]{2, 6});
        ListNode[] lists = {l1, l2, l3};
        ListNode result = mergeKLists.mergeKLists(lists);
        ListNodeUtil.printList(result);
    }
}
