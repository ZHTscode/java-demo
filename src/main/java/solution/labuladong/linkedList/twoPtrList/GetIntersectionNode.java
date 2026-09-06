package solution.labuladong.linkedList.twoPtrList;

import basic.ListNode;

public class GetIntersectionNode {
    /* 160. 相交链表 */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        while(pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    public static void main(String[] args) {
        GetIntersectionNode sol = new GetIntersectionNode();
        // 案例1：相交链表 [4,1,8,4,5]和[5,0,1,8,4,5]
        ListNode n1 = new ListNode(4);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(8);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(5);
        ListNode n7 = new ListNode(0);
        ListNode n8 = new ListNode(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n6.next = n7;
        n7.next = n8;
        n8.next = n3;
        ListNode intersectionNode = sol.getIntersectionNode(n1, n6);
        if (intersectionNode != null) {
            System.out.println("相交节点val = " + intersectionNode.val); //预期输出8
        } else {
            System.out.println("无相交节点");
        }
    }

}
