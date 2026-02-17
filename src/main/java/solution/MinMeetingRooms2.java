package solution;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.PriorityQueue;

public class MinMeetingRooms2 {
    // 解法一：最小堆
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        // 1. 按会议开始时间升序排序，小值在前
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        // 2. 最小堆：存储各会议室的结束时间
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 默认小根堆
        for (int[] meeting : intervals) {
            int start = meeting[0];
            int end = meeting[1];
            // 3. 如果最早结束的会议 <= 当前会议开始时间 → 复用会议室
            if (!minHeap.isEmpty() && minHeap.peek() <= start)
                minHeap.poll(); // 释放该会议室
            // 4. 当前会议占用一个会议室（无论新开还是复用）
            minHeap.offer(end);
        }
        // 5. 堆的大小就是所需最少会议室数
        return minHeap.size();
    }
    // 解法二：扫描线（Sweep Line）（最优）
    public int minMeetingRooms2(int[][] intervals) {
        // 拆分开始和结束时间
        int[] start = new int[intervals.length]; // [0, 5, 15, 11]
        int[] end = new int[intervals.length]; // [30, 10, 20, 13]
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0]; // 会议开始时间
            end[i] = intervals[i][1];   // 会议结束时间
        }
        // 关键：先排序
        Arrays.sort(start); // 所有会议按开始时间升序
        System.out.println(Arrays.toString(start)); // [0, 5, 11, 15]
        Arrays.sort(end);   // 所有会议按结束时间升序
        System.out.println(Arrays.toString(end)); // [10, 13, 20, 30]
        int rooms = 0;      // 当前需要的会议室数量（最大并发数）
        int endPtr = 0;     // 指向最早结束的会议
        for (int j : start) {
            System.out.println("=====================");
            System.out.println("当前会议开始时间：" + j);
            System.out.println("最早结束的会议结束时间：" + end[endPtr]);
            // 当前会议的开始时间 < 最早结束的会议的结束时间
            if (j < end[endPtr]) rooms++; // 需要新开一个会议室
            else {
                // 第 j+1 个会议开始时，最早结束的会议已经结束
                endPtr++; // 复用会议室
            }
            System.out.println("当前需要的会议室数量：" + rooms);
        }
        return rooms;
    }

    public static void main(String[] args) {
        MinMeetingRooms2 mmr = new MinMeetingRooms2();
        int[][] intervals = {{0, 30},{5, 10},{15, 20},{11, 13}};
        System.out.println(mmr.minMeetingRooms2(intervals));
    }
}
