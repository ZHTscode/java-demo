package test;

import basic.ListNode;
import utils.ListNodeUtil;

/**
 * 链表工具类 调用测试 - 演示所有方法的使用方式
 * 复制即可运行，直观看到所有功能效果
 * 外部调用规则：所有方法均为static，直接 ListNodeUtil.方法名(参数) 即可
 */
public class TestListNodeUtil {
    public static void main(String[] args) {
        // ========== 1. 构造链表 + 打印链表 ==========
        int[] arr = {2, 2, 2, 3, 4, 2, 5};
        ListNode head = ListNodeUtil.buildList(arr);
        ListNodeUtil.printList(head); // 链表：1 -> 2 -> 2 -> 3 -> 4 -> 5

        // ========== 2. 反转链表 ==========
        ListNode reverseHead = ListNodeUtil.reverseListRecursion(head);
        ListNodeUtil.printList(reverseHead); // 链表：5 -> 4 -> 3 -> 2 -> 2 -> 1

        // ========== 3. 基础工具方法调用 ==========
        System.out.println("链表是否为空：" + ListNodeUtil.isEmpty(reverseHead)); // false
        System.out.println("链表长度：" + ListNodeUtil.getListLength(reverseHead)); // 6
        System.out.println("索引2的节点值：" + ListNodeUtil.getNodeByIndex(reverseHead, 2).val); //3
        System.out.println("是否包含值4：" + ListNodeUtil.contains(reverseHead, 4)); // true

        // ========== 4. 算法高频方法调用 ==========
        ListNode head2 = ListNodeUtil.buildList(arr);
        ListNode delNode = ListNodeUtil.removeNodeByVal(head2, 2);
        ListNodeUtil.printList(delNode); // 链表：1 -> 3 -> 4 -> 5

        ListNode head3 = ListNodeUtil.buildList(arr);
        ListNode distinctNode = ListNodeUtil.removeDuplicateNode(head3);
        ListNodeUtil.printList(distinctNode); // 链表：1 -> 2 -> 3 -> 4 -> 5

        System.out.println("链表中间节点值：" + ListNodeUtil.findMiddleNode(distinctNode).val); //3
        System.out.println("链表倒数第2个节点值：" + ListNodeUtil.findLastKNode(distinctNode, 2).val); //4

        // ========== 边界值测试（工具类完美兼容） ==========
        System.out.println("======= 边界值测试 =======");
        ListNode emptyList = ListNodeUtil.buildList(null);
        ListNode singleNode = ListNodeUtil.buildList(new int[]{9});
        ListNodeUtil.printList(emptyList); // 链表：空链表
        ListNodeUtil.printList(ListNodeUtil.reverseList(singleNode)); // 链表：9
    }
}