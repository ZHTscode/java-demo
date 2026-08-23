package solution.labuladong.array.randomSelect;

import basic.ListNode;

import java.util.Random;

public class GetRandomLinkedListNode {
    /* 382. 链表随机节点 */
    private ListNode head;
    private Random rand;

    public GetRandomLinkedListNode(ListNode head) {
        this.head = head;
        rand = new Random();
    }

    public int getRandom() {
        ListNode cur = head;
        int res = 0;
        int i = 1;
        while(cur != null){ // 遍历到第i个节点时，以1/i概率选择当前节点覆盖结果，否则保留旧结果
            if(rand.nextInt(i) == 0){ // [0,i‑1] 随机，等于0的概率 1/i
                res = cur.val; // 选中当前节点
            }
            cur = cur.next; // 移动到下一个节点
            i++;
        }
        return res;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        GetRandomLinkedListNode obj = new GetRandomLinkedListNode(head);
        int param_1 = obj.getRandom();
        System.out.println(param_1);
    }
}
