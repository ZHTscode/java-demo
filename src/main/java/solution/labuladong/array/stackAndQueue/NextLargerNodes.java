package solution.labuladong.array.stackAndQueue;

import basic.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NextLargerNodes {
    /* 1019. 链表中的下一个更大节点 */
    public int[] nextLargerNodes(ListNode head) {
        // 把单链表转化成数组，方便通过索引访问
        List<Integer> nums = new ArrayList<>();
        for (ListNode p = head; p != null; p = p.next) {
            nums.add(p.val);
        }
        // 存放答案的数组
        int[] res = new int[nums.size()];
        Stack<Integer> stk = new Stack<>();
        // 单调栈模板，求下一个更大元素，从后往前遍历
        for (int i = nums.size() - 1; i >= 0; i--) {
            while (!stk.isEmpty() && stk.peek() <= nums.get(i)) {
                stk.pop();
            }
            // 本题要求没有下一个更大元素时返回 0
            res[i] = stk.isEmpty() ? 0 : stk.peek();
            stk.push(nums.get(i));
        }
        return res;
    }

    public static void main(String[] args) {
        NextLargerNodes solution = new NextLargerNodes();
        ListNode head = new ListNode(2);
        head.next = new ListNode(7);
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(5);
        int[] res = solution.nextLargerNodes(head);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}