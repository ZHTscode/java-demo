package solution.labuladong.dataStructure.design;

import java.util.LinkedList;
import java.util.List;

public class FrontMiddleBackQueue {
    /* 1670. 设计前中后队列 */
    // 用两个列表表示队列的左右两部分，一遍从中间操作元素
    List<Integer> left = new LinkedList<>();
    List<Integer> right = new LinkedList<>();
    // 设计原则：如果是奇数个元素，维护左边少右边多
    // 1、如果有偶数个元素时，pushMiddle 优先向右边添加
    // 2、如果有奇数个元素时，popMiddle 优先从右边删除
    // 3、如果只有 1 个元素，popFront 的时候，要去右边删除

    // 维护左边少右边多的状态，每次增删元素之后都要执行一次
    private void balance() {
        // 右边最多只能比左边多一个元素
        if (right.size() > left.size() + 1) {
            // 右边多，匀一个给左边
            left.add(right.remove(0));
        }
        if (left.size() > right.size()) {
            // 左边多，匀一个给右边
            right.add(0, left.remove(left.size() - 1));
        }
    }

    public void pushFront(int val) {
        left.add(0, val);
        balance();
    }

    public void pushMiddle(int val) {
        if (size() % 2 == 0) {
            // 如果有偶数个元素时，pushMiddle 优先向右边添加
            right.add(0, val);
        } else {
            left.add(val);
        }
        balance();
    }

    public void pushBack(int val) {
        right.add(val);
        balance();
    }

    public int popFront() {
        if (size() == 0) {
            return -1;
        }
        if (size() == 1) {
            // 如果只有 1 个元素，popFront 的时候，要去右边删除
            return right.remove(0);
        }
        int e = left.remove(0);
        balance();
        return e;
    }

    public int popMiddle() {
        if (size() == 0) {
            return -1;
        }
        int e;
        if (size() % 2 == 0) {
            e = left.remove(left.size() - 1);
        } else {
            // 如果有奇数个元素时，popMiddle 优先从右边删除
            e = right.remove(0);
        }
        balance();
        return e;
    }

    public int popBack() {
        if (size() == 0) {
            return -1;
        }
        int e = right.remove(right.size() - 1);
        balance();
        return e;
    }

    public int size() {
        return left.size() + right.size();
    }
}