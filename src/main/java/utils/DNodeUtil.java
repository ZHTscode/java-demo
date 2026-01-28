package utils;

import basic.DNode;

public class DNodeUtil {
    // 将节点插入到虚拟头节点后
    public static void addToHead(DNode node, DNode head) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    // 从链表中删除指定节点
    public static void removeNode(DNode node) {
        node.prev.next = node.next; // 相比于单链表，需要遍历链表寻找前驱节点
        node.next.prev = node.prev;
    }
    // 将节点移到头部（先删后插）
    public static void moveToHead(DNode node, DNode head) {
        removeNode(node);
        addToHead(node, head);
    }
    // 删除尾节点前的节点，返回该节点
    public static DNode removeTail(DNode tail) {
        DNode lruNode = tail.prev; // 真正的尾节点
        removeNode(lruNode);
        return lruNode;
    }
}

