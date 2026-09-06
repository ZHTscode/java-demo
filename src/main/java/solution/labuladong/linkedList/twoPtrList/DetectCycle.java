package solution.labuladong.linkedList.twoPtrList;

import basic.ListNode;
import utils.ListNodeUtil;

import java.util.List;

public class DetectCycle {
    /* 142. 环形链表 II */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) { // 快慢指针寻找相遇点
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }
        if (fast == null || fast.next == null) return null;
        ListNode ptr1 = head;
        ListNode ptr2 = slow;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        return ptr1;
    }

    public static void main(String[] args) {
        DetectCycle sol = new DetectCycle();
        // 案例1：有环链表 [3,2,0,-4]，环在节点2(index=1)
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(0);
        ListNode n4 = new ListNode(-4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n2; //‑‑‑‑‑形成环，‑4指向2
        ListNode cycleNode = sol.detectCycle(n1);
        if (cycleNode != null) {
            System.out.println("有环，环入口节点val = " + cycleNode.val); //预期输出2
        } else {
            System.out.println("无环");
        }
        //案例2：无环链表 1‑>2‑>null
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        a1.next = a2;
        ListNode res2 = sol.detectCycle(a1);
        System.out.println(res2 == null ? "无环" : "有环"); //预期无环
    }
}
