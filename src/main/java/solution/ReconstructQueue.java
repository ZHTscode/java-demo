package solution;

import java.util.*;

public class ReconstructQueue {
    /* 406.根据身高重建队列 */
    // 解法一：排序 + 插入
    public int[][] reconstructQueue(int[][] people) {
        // 排序：身高降序，k 升序
        Arrays.sort(people, (a, b) -> {
            if (a[0] != b[0]) return b[0] - a[0]; // 返回正数 → a 排在 b 后面（高的在前）
            else return a[1] - b[1]; // 返回正数 → a 排在 b 后面 （前面比自己高/相等的人少的在前）
        });
//        System.out.println(Arrays.deepToString(people));
        // 用 LinkedList 或 ArrayList 插入
        List<int[]> result = new ArrayList<>();
        for (int[] p : people) {
            // 高的先排，矮的插到 k 位置（会把前面高的自动挤到后面）
            result.add(p[1], p); // 把 p 插入到位置 p[1] 即 k
        }
        return result.toArray(new int[people.length][]);
    }
    // 解法二：优先队列 + 插入（最优）
    public int[][] reconstructQueue2(int[][] people) {
        PriorityQueue<int[]> queue=new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] m,int[] n){
                if(m[0]==n[0]){
                    return m[1]-n[1];
                }
                return n[0]-m[0];
            }
        });
        for(int[] n:people){
            queue.add(n);
        }
        List<int[]> list=new ArrayList<>();
        while(!queue.isEmpty()){
            int[] n=queue.remove();
            list.add(n[1],n);
        }
        return list.toArray(new int[][]{});
    }

    public static void main(String[] args) {
        ReconstructQueue reconstructQueue = new ReconstructQueue();
        int[][] people = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        int[][] queue = reconstructQueue.reconstructQueue(people);
        for (int[] person : queue) {
            System.out.println(Arrays.toString(person));
        }
    }

}
