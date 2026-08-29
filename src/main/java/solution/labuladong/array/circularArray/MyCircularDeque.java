package solution.labuladong.array.circularArray;

public class MyCircularDeque {
    private int cap;
    private MyArrayDeque<Integer> list = new MyArrayDeque<>();

    // Initialize your data structure here. Set the size of the deque to be k.
    public MyCircularDeque(int k) {
        this.cap = k;
    }

    // Adds an item at the front of Deque. Return true if the operation is successful.
    public boolean insertFront(int value) {
        if (list.size() == cap) return false;
        list.addFirst(value);
        return true;
    }

    // Adds an item at the rear of Deque. Return true if the operation is successful.
    public boolean insertLast(int value) {
        if (list.size() == cap) return false;
        list.addLast(value);
        return true;
    }

    // Deletes an item from the front of Deque. Return true if the operation is successful.
    public boolean deleteFront() {
        if (list.isEmpty()) return false;
        list.removeFirst();
        return true;
    }

    // Deletes an item from the rear of Deque. Return true if the operation is successful.
    public boolean deleteLast() {
        if (list.isEmpty()) return false;
        list.removeLast();
        return true;
    }

    // Get the front item from the deque.
    public int getFront() {
        if (list.isEmpty()) return -1;
        return list.getFirst();
    }

    // Get the last item from the deque.
    public int getRear() {
        if (list.isEmpty()) return -1;
        return list.getLast();
    }

    // Checks whether the circular deque is empty or not.
    public boolean isEmpty() {
        return list.isEmpty();
    }

    // Checks whether the circular deque is full or not.
    public boolean isFull() {
        return list.size() == cap;
    }
}
