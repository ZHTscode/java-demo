package solution.labuladong.array.circularArray;

import java.util.NoSuchElementException;

public class MyArrayDeque<E> {
    private int size;
    private E[] data;
    private final static int INIT_CAP = 2;

    private int first, last;

    public MyArrayDeque(int initCap) {
        size = 0;
        data = (E[]) new Object[initCap];
        // last 是下一次应该添加元素的索引, [first, last)
        // 比如 first = 1，last = 3，size = 2
        first = last = 0;
    }

    public MyArrayDeque() {
        this(INIT_CAP); // 不传参数，默认大小为 INIT_CAP
    }

    // 从头部获取元素
    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data[first];
    }

    // 从尾部获取元素
    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (last == 0) return data[data.length - 1];
        return data[last - 1];
    }

    // 从头部插入元素
    public void addFirst(E e) {
        if (size == data.length) {
            resize(size * 2);
        }
        // 情况一：first----last
        // 情况二：---last  first---
        // 左移 first，所以 first == 0 是一种特殊情况
        if (first == 0) {
            first = data.length - 1;
        } else {
            first--;
        }
        // 插入元素
        data[first] = e;
        size++;
    }

    // 从尾部插入元素
    public void addLast(E e) {
        if (size == data.length) {
            resize(size * 2);
        }
        // 插入元素
        data[last] = e;
        last++;
        if (last == data.length) {
            last = 0;
        }
        size++;
    }

    // 从头部删除元素
    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (size == data.length / 4) {
            resize(data.length / 2);
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

    // 从尾部删除元素
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (size == data.length / 4) {
            resize(data.length / 2);
        }
        // 情况一：first----last
        // 情况二：---last  first---
        // 左移 last，当 last == 0 的时候是特殊情况
        if (last == 0) {
            last = data.length - 1;
        } else {
            last--;
        }
        E oldVal = data[last];
        // 删除元素
        data[last] = null;
        size--;
        return oldVal;
    }

    private void resize(int newCap) {
        E[] temp = (E[]) new Object[newCap];
        // first ----- last
        // --- last    first ---
        for (int i = 0; i < size; i++) {
            temp[i] = data[(first + i) % data.length];
        }
        first = 0;
        last = size;
        data = temp;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
