package solution.labuladong.array.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomFlip {
    /* 519. 随机翻转矩阵 */
    int m, n;
    int len;
    Map<Integer, Integer> deletedToExist; // 已删除元素到尾部未删除元素的映射
    Random random = new Random();

    public RandomFlip(int m, int n) {
        this.m = m;
        this.n = n;
        len = m * n;
        deletedToExist = new HashMap<>();
    }

    public int[] flip() {
        int rand = random.nextInt(len); // 随机一个下标
        int res = rand;
        if (deletedToExist.containsKey(rand)) // 该下标对应初始元素已被删
            res = deletedToExist.get(rand);
        // 把 rand 置换到数组尾部
        int last = len - 1;
        if (deletedToExist.containsKey(last)) // 该尾部元素对应初始元素已被删
            last = deletedToExist.get(last);
        deletedToExist.put(rand, last); // (已删元素下标, 尾部未删元素下标) 建立映射
        len--; // 把尾部元素删掉（实则未删）
        return new int[]{res / n, res % n}; // 一维坐标转化成二维坐标
    }

    public void reset() {
        this.len = this.m * this.n;
        this.deletedToExist.clear();
    }

}
