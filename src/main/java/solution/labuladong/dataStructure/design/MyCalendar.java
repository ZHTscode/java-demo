package solution.labuladong.dataStructure.design;

import java.util.Map;
import java.util.TreeMap;

public class MyCalendar {
    /* 729. 我的日程安排表I */
    // 记录日程，键是日程的开始时间，值是日程的结束时间
    private TreeMap<Integer, Integer> calendar = new TreeMap<>();

    public MyCalendar() {
    }

    public boolean book(int start, int end) {
        Integer earlier = calendar.floorKey(start); // 最小的比 start 大的键
        Integer later = calendar.ceilingKey(start); // 最大的比 start 小的键

        if (later != null && end > later) {
            // 本次日程还没结束，下个日程就开始了
            return false;
        }

        if (earlier != null && start < calendar.get(earlier)) {
            // 上个日程还没结束，本次日程就开始了
            return false;
        }
        // 成功安排日程
        calendar.put(start, end);
        return true;
    }
}
