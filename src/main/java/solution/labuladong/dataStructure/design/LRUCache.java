package solution.labuladong.dataStructure.design;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    // 双向链表节点
    class DNode {
        public int key, val;
        public DNode next, prev;
        public DNode(int k, int v) {
            this.key = k;
            this.val = v;
        }
    }

    // 双向链表
    class DList {
        private DNode head, tail; // 头尾虚节点
        private int size; // 链表元素数
        public DList() {
            // 初始化双向链表的数据
            head = new DNode(0, 0);
            tail = new DNode(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }
        // 链表尾部添加节点 x
        public void addLast(DNode x) {
            x.prev = tail.prev;
            x.next = tail;
            tail.prev.next = x;
            tail.prev = x;
            size++;
        }
        // 删除链表中的 x 节点（x 一定存在）
        public void remove(DNode x) {
            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
        }
        // 删除链表中第一个节点并返回
        public DNode removeFirst() {
            if (head.next == tail) return null;
            DNode first = head.next;
            remove(first);
            return first;
        }
        // 链表长度
        public int size() {
            return size;
        }
    }

    // key -> Node(key, val)
    private Map<Integer, DNode> map;
    // Node(k1, v1) <-> Node(k2, v2)...
    private DList cache;
    // 最大容量
    private int cap;

    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DList();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        makeRecently(key); // 设为最近使用
        return map.get(key).val;
    }

    public void put(int key, int val) {
        if (map.containsKey(key)) {
            deleteKey(key); // 删除 key
            addRecently(key, val); // 重新添加 key 并设为最近使用
            return;
        }
        if (cap == cache.size()) {
            removeLeastRecently(); // 删除最久未使用的元素
        }
        addRecently(key, val); // 添加并设为最近使用
    }
    // 设为最近使用
    private void makeRecently(int key) {
        DNode x = map.get(key);
        cache.remove(x); // 从链表中删除
        cache.addLast(x); // 重新插到队尾
    }
    // 添加并设为最近使用
    private void addRecently(int key, int val) {
        DNode x = new DNode(key, val);
        // 链表尾部就是最近使用的元素
        cache.addLast(x);
        // 在 map 中添加 key 的映射
        map.put(key, x);
    }
    // 删除 key
    private void deleteKey(int key) {
        DNode x = map.get(key);
        cache.remove(x); // 从链表中删除
        map.remove(key); // 从 map 中删除
    }
    // 删除最久未使用的元素
    private void removeLeastRecently() {
        // 链表头节点是最久未使用的
        DNode deletedNode = cache.removeFirst();
        // 从 map 中删除 key
        int deletedKey = deletedNode.key;
        map.remove(deletedKey);
    }
}