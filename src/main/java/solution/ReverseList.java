package solution;

import basic.ListNode;

import java.util.Stack;

import static utils.ListNodeUtil.printList;

public class ReverseList {
    // 迭代写法
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next; // 暂存后继节点
            cur.next = pre;           // 核心反转指针
            pre = cur;                // pre指针后移
            cur = temp;               // cur指针后移
        }
        return pre;
    }
    // 递归写法
    public static ListNode reverseList2(ListNode head) {
        // 1. 递归终止的边界条件
        if (head == null || head.next == null) return head;
        // 2. 递归调用：反转当前节点的后续链表，拿到反转后的新头节点
        ListNode newHead = reverseList2(head.next);
        printList(newHead);
        // 3. 核心反转逻辑：让当前节点的下一个节点 指向自己
        head.next.next = head;
        // 4. 断开当前节点的原指向，避免链表成环
        head.next = null;
//        printList(newHead);
        // 5. 返回反转后的链表头节点
        return newHead;
    }
    // 栈写法
    public static ListNode reverseList3(ListNode head) {
        if(head==null) return null;
        Stack<ListNode> stack=new Stack<>();
        ListNode temp=head;
        // 将链表节点全部压入栈中
        while(temp!=null){
            stack.push(temp);
            temp=temp.next;
        }
        ListNode m=stack.peek(); // 取出栈顶节点
        temp=stack.pop();
        while(!stack.isEmpty()){
            temp.next=stack.peek();
            temp=stack.pop();
        }
        temp.next=null;
        return m;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode cur = head;
        for (int i = 2; i <= 10; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        ListNode newHead = reverseList3(head);
        while (newHead != null) {
            System.out.print(newHead.val + " ");
            newHead = newHead.next;
        }
    }
}
