package solution.labuladong.linkedList.twoPtrList;

import basic.ListNode;
import utils.ListNodeUtil;

import java.util.PriorityQueue;

public class MergeKLists {
    /* 23. 合并K个升序链表 */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        // 虚拟头结点
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        // 优先级队列，最小堆
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                lists.length, (a, b)->(a.val - b.val));
        // 将 k 个链表的头结点加入最小堆
        for (ListNode head : lists) {
            if (head != null)
                pq.add(head);
        }
        while (!pq.isEmpty()) {
            // 获取最小节点，接到结果链表中
            ListNode node = pq.poll();
            p.next = node;
            if (node.next != null) {
                pq.add(node.next);
            }
            // p 指针不断前进
            p = p.next;
        }
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
        ListNode mergedList = new MergeKLists().mergeKLists(lists);
        ListNodeUtil.printList(mergedList);
    }
}
