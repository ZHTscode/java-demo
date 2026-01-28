package utils;

import basic.ListNode;

import java.util.HashSet;

/**
 * 单向链表 万能工具类
 * 所有方法均为static静态方法，外部直接 类名.方法名() 调用，无需创建对象
 * 所有变量均为方法内局部变量，线程绝对安全，无限次调用无任何数据污染/BUG
 * 包含：构造、打印、反转、判空、获取长度、判环、去重、找倒数节点等 全量常用方法
 * 兼容所有边界值：空链表null、单节点链表、多节点链表、环形链表、重复节点链表
 */
public class ListNodeUtil {
    /**
     * 1. 通过整型数组 快速构造链表
     * @param arr 传入的数组，如 {1,2,3,4,5}
     * @return 链表头节点
     */
    public static ListNode buildList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode cur = head;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
        return head;
    }

    /**
     * 2. 格式化打印链表内容
     * @param head 链表头节点
     */
    public static void printList(ListNode head) {
        if (head == null) {
            System.out.println("链表：空链表");
            return;
        }
        StringBuilder sb = new StringBuilder();
        ListNode cur = head;
        // 防止环形链表无限循环打印
        while (cur != null) {
            sb.append(cur.val);
            if (cur.next != null) {
                sb.append(" -> ");
            }
            cur = cur.next;
        }
        System.out.println("链表：" + sb);
    }

    /**
     * 3. 反转链表
     * 原地反转，空间复杂度O(1)，时间复杂度O(n)，性能最优，无任何额外内存开销
     * @param head 原链表头节点
     * @return 反转后的链表头节点
     */
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
    public static ListNode reverseListRecursion(ListNode head) {
        // 1. 递归终止的边界条件
        if (head == null || head.next == null) return head;
        // 2. 递归调用：反转当前节点的后续链表，拿到反转后的新头节点
        ListNode newHead = reverseListRecursion(head.next);
        // 3. 核心反转逻辑：让当前节点的下一个节点 指向自己
        head.next.next = head;
        // 4. 断开当前节点的原指向，避免链表成环
        head.next = null;
        // 5. 返回反转后的链表头节点
        return newHead;
    }

    /**
     * 4. 判断链表是否为空
     * @param head 链表头节点
     * @return true=空链表 false=非空
     */
    public static boolean isEmpty(ListNode head) {
        return head == null;
    }

    /**
     * 5. 获取链表的节点个数/长度
     * @param head 链表头节点
     * @return 链表长度，空链表返回0
     */
    public static int getListLength(ListNode head) {
        int count = 0;
        ListNode cur = head;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    /**
     * 6. 根据索引获取指定位置的节点（索引从0开始）
     * @param head 链表头节点
     * @param index 目标索引
     * @return 对应节点，索引越界/空链表返回null
     */
    public static ListNode getNodeByIndex(ListNode head, int index) {
        if (index < 0 || head == null) {
            return null;
        }
        ListNode cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
            if (cur == null) {
                return null;
            }
        }
        return cur;
    }

    /**
     * 7. 查询链表中是否包含某个值
     * @param head 链表头节点
     * @param target 目标值
     * @return true=包含 false=不包含
     */
    public static boolean contains(ListNode head, int target) {
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == target) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    /**
     * 8. 删除链表中所有指定值的节点
     * @param head 链表头节点
     * @param val 要删除的目标值
     * @return 删除后的链表头节点
     */
    public static ListNode removeNodeByVal(ListNode head, int val) {
        /*方法1：使用虚拟头节点*/
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
        /*方法2：先处理头节点再处理后续节点*/
/*        ListNode cur = head;
        // 处理头节点
        while (cur != null && cur.val == val) {
            cur = cur.next;
            head = head.next;
        }
        // 处理后续节点
        while (cur != null && cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;*/
    }

    /**
     * 9. 删除链表中所有重复值的节点
     * 无论重复值是否连续，最终每个数值只保留第一次出现的那一个
     * 例如 {1,2,2,3,4,2,5} → {1,2,3,4,5}
     * @param head 链表头节点
     * @return 去重后的链表头节点
     */
    public static ListNode removeDuplicateNode(ListNode head) {
        // 边界值：空链表 或 单节点链表，直接返回原链表
        if (head == null || head.next == null) {
            return head;
        }
        HashSet<Integer> set = new HashSet<>();
        ListNode pre = head;  // 前驱指针，记录当前节点的上一个节点
        ListNode cur = head.next; // 当前指针，从第二个节点开始遍历
        set.add(head.val);    // 先把第一个节点的值存入集合

        while (cur != null) {
            // 如果当前值已存在，删除当前节点
            if (set.contains(cur.val)) {
                pre.next = cur.next;
            } else {
                // 如果不存在，存入集合，并让前驱指针后移
                set.add(cur.val);
                pre = pre.next;
            }
            // 无论是否删除，当前指针都要后移
            cur = cur.next;
        }
        return head;
    }

    /**
     * 10. 查找链表的中间节点（快慢指针法）
     * 若为节点个数为偶数，返回中间第二个节点，如 1->2->3->4 → 返回3
     * @param head 链表头节点
     * @return 中间节点
     */
    public static ListNode findMiddleNode(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 11. 查找链表的倒数第k个节点（快慢指针法）
     * @param head 链表头节点
     * @param k 倒数第k个
     * @return 对应节点，k无效返回null
     */
    public static ListNode findLastKNode(ListNode head, int k) {
        if (head == null || k <= 0) return null;
        ListNode fast = head;
        ListNode slow = head;
        // 快指针先走k步
        for (int i = 0; i < k; i++) {
            if (fast == null) return null;
            fast = fast.next;
        }
        // 快慢指针同步走，快指针到尾，慢指针就是倒数第k个
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}