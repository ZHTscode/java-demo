package solution.labuladong.array.binarySearch;

public class ShipWithinDays {
    /* 1011. 在 D 天内送达包裹的能力 */
    int shipWithinDays(int[] weights, int D) {
        int left = 0, right = 0;
        for (int weight : weights) {
            left = Math.max(left, weight); // 最小运力
            right += weight; // 最大运力
        }
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canShip(weights, D, mid)) { // 运力是 mid 时，可以在 D 天内送达
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    boolean canShip(int[] weights, int D, int capacity) {
        int days = 1, currentLoad = 0;
        for (int weight : weights) {
            if (currentLoad + weight > capacity) {
                days++;
                currentLoad = 0;
            }
            currentLoad += weight;
        }
        return days <= D;
    }

    public static void main(String[] args) {
        ShipWithinDays solution = new ShipWithinDays();
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int D = 5;
        System.out.println(solution.shipWithinDays(weights, D));
    }
}
