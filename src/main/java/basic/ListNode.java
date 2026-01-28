package basic;

public class ListNode {
    public int val; // 不传参默认为0
    public ListNode next; // 不传参默认为null
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}