package solution.labuladong.dataStructure.heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class MedianFinder {
    /* 295. 数据流的中位数 */
    private Queue<Integer> large;
    private Queue<Integer> small;

    public MedianFinder() {
        // 小顶堆
        large = new PriorityQueue<>();
        // 大顶堆
        small = new PriorityQueue<>((a, b) -> b - a);
    }

    public double findMedian() {
        // 如果元素不一样多，多的那个堆的堆顶元素就是中位数
        if (large.size() < small.size()) {
            return small.peek();
        } else if (large.size() > small.size()) {
            return large.peek();
        }
        // 如果元素一样多，两个堆顶元素的平均数是中位数
        return (large.peek() + small.peek()) / 2.0;
    }

    public void addNum(int num) {
        if (small.size() >= large.size()) {
            small.offer(num);
            large.offer(small.poll()); // 将小顶堆的堆顶元素移动到大顶堆
        } else {
            large.offer(num);
            small.offer(large.poll()); // 将大顶堆的堆顶元素移动到小顶堆
        }
    }
}