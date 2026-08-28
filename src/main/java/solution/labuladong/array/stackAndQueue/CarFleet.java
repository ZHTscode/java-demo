package solution.labuladong.array.stackAndQueue;

import java.util.Arrays;

public class CarFleet {
    /* 853. 车队$$ */
    // 如果车 x 排在 车 y 后面，且 x 到达终点所需时间比 y 少，则 x 必然会被 y 卡住，形成车队
    // 先根据每辆车的起始位置 position 排序，然后计算出时间数组 time
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        int[][] cars = new int[n][2];
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }
        Arrays.sort(cars, (a, b) -> a[0] - b[0]); // 按位置从小到大排序
        // 计算每辆车到达终点的时间
        double[] time = new double[n];
        for (int i = 0; i < n; i++) {
            time[i] = (double) (target - cars[i][0]) / cars[i][1]; // 路程除以速度等于时间
        }
        // 计算车队数量
        int res = 0;
        double maxTime = 0;
        for (int i = n - 1; i >= 0; i--) { // 从后向前遍历
            if (time[i] > maxTime) { // 如果当前车的时间大于最大时间，则形成新的车队
                maxTime = time[i]; // 更新最大时间
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        CarFleet cf = new CarFleet();
        int target = 12;
        int[] position = {10, 8, 0, 5, 3};
        int[] speed = {2, 4, 1, 1, 3};
        int res = cf.carFleet(target, position, speed);
        System.out.println(res);
    }
}
