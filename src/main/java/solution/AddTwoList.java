package solution;

import basic.ListNode;

import static utils.ListNodeUtil.buildList;
import static utils.ListNodeUtil.printList;

public class AddTwoList {
    /* 2. 两数相加 */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0); // 虚拟头节点（简化链表构建）
        ListNode cur = dummyHead; // 当前指针，用于构建结果链表
        int carry = 0; // 进位（0或1，加法最多进位1）
        // 循环条件：l1未遍历完 或 l2未遍历完 或 还有进位（避免末尾进位遗漏）
        while (l1 != null || l2 != null || carry != 0) {
            // 取当前节点值（链表为空则取0，不影响加法）
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;
            // 计算当前位总和（包含上一轮进位）
            int sum = val1 + val2 + carry;
            carry = sum / 10; // 更新进位（sum≥10则为1，否则为0）
            int currentVal = sum % 10; // 当前位结果（余数）
            // 构建结果节点，指针后移
            cur.next = new ListNode(currentVal);
            cur = cur.next;
            // 原链表指针后移（不为空才移动，避免空指针）
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return dummyHead.next; // 跳过虚拟头节点，返回真实结果链表头
    }

    public static void main(String[] args) {
        // 测试用例1：常规情况（l1=[2,4,3], l2=[5,6,4] → 结果[7,0,8]）
        ListNode l1 = buildList(new int[]{2, 4, 3});
        ListNode l2 = buildList(new int[]{5, 6, 4});

        // 测试用例2：末尾进位（l1=[9,9], l2=[9,9] → 结果[8,9,1]）
        // ListNode l1 = buildList(new int[]{9, 9});
        // ListNode l2 = buildList(new int[]{9, 9});

        // 测试用例3：sum=0（l1=[0], l2=[0] → 结果[0]）
        // ListNode l1 = buildList(new int[]{0});
        // ListNode l2 = buildList(new int[]{0});

        // 测试用例4：链表长度不同（l1=[1,2], l2=[3,4,5] → 结果[4,6,5]）
        // ListNode l1 = buildList(new int[]{1, 2});
        // ListNode l2 = buildList(new int[]{3, 4, 5});

        // 执行加法
        AddTwoList addTwoList = new AddTwoList();
        ListNode result = addTwoList.addTwoNumbers(l1, l2);

        // 打印结果
        System.out.print("l1: ");
        printList(l1);
        System.out.print("l2: ");
        printList(l2);
        System.out.print("结果: ");
        printList(result);
    }


}
