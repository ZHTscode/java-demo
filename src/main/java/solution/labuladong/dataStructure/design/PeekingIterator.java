package solution.labuladong.dataStructure.design;

import java.util.Iterator;

public class PeekingIterator implements Iterator<Integer> {
    /* 284. 窥视迭代器 */
    private Iterator<Integer> iter;
    // 把迭代器的下一个元素提前拿出来并缓存起来
    private Integer nextElem;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.iter = iterator;
        this.nextElem = iterator.next();
    }

    public Integer peek() {
        return nextElem;
    }

    @Override
    public Integer next() {
        Integer res = nextElem;
        // 更新 nextElem
        if (iter.hasNext()) {
            nextElem = iter.next();
        } else {
            nextElem = null;
        }
        return res;
    }

    @Override
    public boolean hasNext() {
        return nextElem != null;
    }
}