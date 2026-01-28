package solution;

import basic.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static utils.ListNodeUtil.findMiddleNode;
import static utils.ListNodeUtil.reverseList;

public class IsPalindrome {
    // 方法一：使用数组存储链表节点值，然后使用双指针判断数组是否为回文
    public static boolean isPalindrome(ListNode head){
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null){
            list.add(cur.val);
            cur = cur.next;
        }
        int left = 0;
        int right = list.size()-1;
        while(left < right){
            if(!Objects.equals(list.get(left), list.get(right)))
                return false;
            left++;
            right--;
        }
        return true;
    }
    // 方法二：使用递归判断链表是否为回文
    private static ListNode front;
    public static boolean isPalindrome2(ListNode head){
        front = head;
        return check(head);
    }
    private static boolean check(ListNode cur){
        if(cur != null){
            //check(cur.next);
            if(!check(cur.next)) return false; //入栈直到尾节点 & 只要栈顶函数返回false，底部函数都返回false
            if(cur.val != front.val) return false; // 判断当前节点值与前驱节点值是否相等
            front = front.next;
        }
        return true;
    }
    // 方法三：使用快慢指针找到链表中点，并反转后半部分链表，然后判断前半部分和反转后的后半部分是否相等
    public static boolean isPalindrome3(ListNode head) {
        // 边界情况：空链表 或 只有一个节点，直接是回文
        if (head == null || head.next == null) {
            return true;
        }
        // 1. 快慢指针找链表的中点
        /*ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow;*/
        ListNode mid = findMiddleNode(head);

        // 2. 反转链表的后半段
        ListNode right = reverseList(mid);
        ListNode left = head;

        // 3. 双指针比较前后两段的值
        boolean res = true;
        while (res && right != null) {
            if (left.val != right.val) {
                res = false;
            }
            left = left.next;
            right = right.next;
        }
        return res;
    }

    public static void main(String[] args) {
        // 测试用例1：偶数长度 回文链表 1->2->2->1
        ListNode head1 = new ListNode(1,new ListNode(2,new ListNode(2,new ListNode(1))));
        System.out.println("链表1->2->2->1 是否是回文：" + isPalindrome2(head1)); // true

        // 测试用例2：奇数长度 回文链表 1->2->3->2->1
        ListNode head2 = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(2,new ListNode(3)))));
        System.out.println("链表1->2->3->2->1 是否是回文：" + isPalindrome2(head2)); // true

        // 测试用例3：非回文链表 1->2->3
        ListNode head3 = new ListNode(1,new ListNode(2,new ListNode(3)));
        System.out.println("链表1->2->3 是否是回文：" + isPalindrome2(head3)); // false

        // 测试用例4：边界场景-单节点链表
        ListNode head4 = new ListNode(5);
        System.out.println("单节点链表5 是否是回文：" + isPalindrome2(head4)); // true
    }


}
