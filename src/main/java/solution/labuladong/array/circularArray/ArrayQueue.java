package solution.labuladong.array.circularArray;

import java.util.NoSuchElementException;

// 底层用数组实现队列
public class ArrayQueue<E> {
    private int size;
    private E[] data;
    private final static int INIT_CAP = 2;

    private int first, last;

    public ArrayQueue(int initCap) {
        size = 0;
        data = (E[]) new Object[initCap];
        first = last = 0;
    }

    public ArrayQueue() {
        // 不传参数，默认大小为 INIT_CAP
        this(INIT_CAP);
    }

    // 增
    public void enqueue(E e) {
        if (size == data.length) { // 队列满
            resize(size * 2); // 扩容
        }
        data[last] = e;
        last++;
        if (last == data.length) {
            last = 0;
        }
        size++;
    }

    // 删
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (size == data.length / 4) { // 当前元素个数是数组长度的 1/4
            resize(data.length / 2); // 缩容
        }
        E oldVal = data[first];
        data[first] = null;
        first++;
        if (first == data.length) {
            first = 0;
        }
        size--;
        return oldVal;
    }

    private void resize(int newCap) {
        E[] temp = (E[]) new Object[newCap];
        // size：队列中实际元素个数
        for (int i = 0; i < size; i++) {
            temp[i] = data[(first + i) % data.length]; // 将元素复制到新数组
        }
        first = 0;
        last = size;
        data = temp;
    }

    // 查
    public E peekFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data[first];
    }

    public E peekLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (last == 0) return data[data.length - 1];
        return data[last - 1];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}