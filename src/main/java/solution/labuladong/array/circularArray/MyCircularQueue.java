package solution.labuladong.array.circularArray;

public class MyCircularQueue {
    ArrayQueue<Integer> q;
    int maxCap;

    public MyCircularQueue(int k) {
        q = new ArrayQueue<>(k);
        maxCap = k;
    }

    public boolean enQueue(int value) {
        if (q.size() == maxCap) return false;
        q.enqueue(value);
        return true;
    }

    public boolean deQueue() {
        if (q.isEmpty()) return false;
        q.dequeue();
        return true;
    }

    public int Front() {
        if (q.isEmpty()) return -1;
        return q.peekFirst();
    }

    public int Rear() {
        if (q.isEmpty()) return -1;
        return q.peekLast();
    }

    public boolean isEmpty() {
        return q.isEmpty();
    }

    public boolean isFull() {
        return q.size() == maxCap;
    }
}
