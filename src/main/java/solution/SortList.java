package solution;

import basic.ListNode;

import static utils.ListNodeUtil.printList;

public class SortList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        // 使用快慢指针找到中点前一个节点
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null; // 断开链表
        return mergeTwoLists(sortList(head), sortList(slow));
//        return mergeTwoLists(head, slow);
    }
    private ListNode mergeTwoLists(ListNode listNode, ListNode listNode1) {
        ListNode dummy = new ListNode(0); // 虚拟头节点
        ListNode cur = dummy; // 当前节点
        while (listNode != null && listNode1 != null) {
            if (listNode.val < listNode1.val) {
                cur.next = listNode;
                listNode = listNode.next;
            } else {
                cur.next = listNode1;
                listNode1 = listNode1.next;
            }
            cur = cur.next;
        }
        cur.next = listNode != null ? listNode : listNode1;
        return dummy.next;
    }

    public ListNode sortList2(ListNode head) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        ListNode cur = head;
        // 找到链表中的最大值和最小值
        while(cur != null){
            if(cur.val > max) max = cur.val;
            if(cur.val < min) min = cur.val;
            cur = cur.next;
        }
        // 创建一个数组来统计每个数字出现的次数
        int[] num = new int[max-min+1];
        cur = head;
        // 统计每个数字出现的次数
        while(cur != null){
            num[cur.val-min]++;
            cur = cur.next;
        }
        cur = head;
        for(int i = 0; i < max-min+1; i++){
            while(num[i] > 0){
                cur.val = i + min;
                cur = cur.next;
                num[i]--;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        SortList sortList = new SortList();
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        ListNode sortedList = sortList.sortList(head);
        printList(sortedList);
    }
}
