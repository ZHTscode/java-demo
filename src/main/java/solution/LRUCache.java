package solution;

import basic.DNode;
import utils.DNodeUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {
    /* 146. LRU缓 */
    // 核心成员变量
    private Map<Integer, DNode> map; // 哈希表：key -> 节点
    private DNode head; // 虚拟头节点（不存数据）
    private DNode tail; // 虚拟尾节点（不存数据）
    private int capacity; // 缓存最大容量
    private int size; // 当前缓存元素个数
    // 初始化LRU缓存
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.map = new HashMap<>();
        // 初始化虚拟头尾节点
        this.head = new DNode(-1, -1);
        this.tail = new DNode(-1, -1);
        // 连接头尾节点
        head.next = tail;
        tail.prev = head;
    }
    // 核心方法：get获取值
    public int get(int key) {
        DNode node = map.get(key);
        if (node == null) return -1; // 键不存在，返回-1
        DNodeUtil.moveToHead(node, head); // 访问后移到头部，标记为最近使用
        return node.value;
    }
    // 核心方法：put插入/更新值
    public void put(int key, int value) {
        DNode node = map.get(key);
        if (node == null) {
            // 情况1：键不存在，新建节点
            DNode newNode = new DNode(key, value); // node的key与map的key相等
            map.put(key, newNode); // 哈希表添加映射
            DNodeUtil.addToHead(newNode, head); // 链表插入头部
            size++; // 容量+1
            // 容量满，淘汰最少使用的元素
            if (size > capacity) {
                DNode lruNode = DNodeUtil.removeTail(tail); // 删除链表尾节点
                map.remove(lruNode.key); // 删除哈希表映射
                size--; // 容量-1
            }
        } else {
            // 情况2：键存在，更新值并移到头部
            node.value = value;
            DNodeUtil.moveToHead(node, head);
        }
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2); // 容量2
        lRUCache.put(1, 1); // 缓存：{1=1}（1为最近使用）
        lRUCache.put(2, 2); // 缓存：{1=1, 2=2}（2为最近使用，1为最少）
        System.out.println(lRUCache.get(1)); // 返回1，缓存：{2=2, 1=1}（1移为最近使用）
        lRUCache.put(3, 3); // 容量满，淘汰2，缓存：{1=1, 3=3}（3为最近使用）
        System.out.println(lRUCache.get(2));    // 返回-1（已淘汰）
        lRUCache.put(4, 4); // 容量满，淘汰1，缓存：{3=3, 4=4}（4为最近使用）
        System.out.println(lRUCache.get(1));    // 返回-1（已淘汰）
        System.out.println(lRUCache.get(3));    // 返回3，缓存：{4=4, 3=3}（3移为最近使用）
        System.out.println(lRUCache.get(4));    // 返回4，缓存：{3=3, 4=4}（4移为最近使用）
    }
}

/*class LRUCache2 extends LinkedHashMap<Integer, Integer> {
    private int capacity; // 缓存最大容量
    // 初始化：指定容量，加载因子0.75（默认），访问顺序排序
    public LRUCache2(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }
    // 重写核心方法：判断是否淘汰最旧元素
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        // 大小超过容量时，自动淘汰最旧（最近最少使用）的元素
        return size() > capacity;
    }
    // 简化get方法：父类已有，不存在返回-1
    public int get(int key) {
        return super.getOrDefault(key, -1);
    }
    // 简化put方法：父类已有，自动维护访问顺序
    public void put(int key, int value) {
        super.put(key, value);
    }
}*/
