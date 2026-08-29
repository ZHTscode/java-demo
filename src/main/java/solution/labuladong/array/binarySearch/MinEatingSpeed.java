package solution.labuladong.array.binarySearch;

public class MinEatingSpeed {
    /* 875. 爱吃香蕉的珂珂 */
    int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = 1000000000; // 吃香蕉的速度
        while(left <= right){
            int mid = left + (right - left) / 2;
            if (canFinish(piles, mid, h)) {
                right = mid - 1; // 可以吃完的情况下，速度越小越好
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    boolean canFinish(int[] piles, int speed, int h) {
        int time = 0;
        for (int pile : piles) {
            time += (pile + speed - 1) / speed; // 向上取整
        }
        return time <= h;
    }

    public static void main(String[] args) {
        MinEatingSpeed solution = new MinEatingSpeed();
        int[] piles = {3, 6, 7, 11};
        int h = 8;
        System.out.println(solution.minEatingSpeed(piles, h));
    }
}
