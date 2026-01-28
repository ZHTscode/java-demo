package basic;

// 双向链表节点
public class DNode {
    public int key;
    public int value;
    public DNode prev;
    public DNode next;
    // 构造方法
    public DNode(int key, int value) {
        this.key = key;
        this.value = value;
        //this.prev = null;
        //this.next = null;
    }
}
