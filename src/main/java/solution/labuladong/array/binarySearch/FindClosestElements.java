package solution.labuladong.array.binarySearch;

import java.util.ArrayList;
import java.util.List;

public class FindClosestElements {
    /* 658. 找到 K 个最接近的元素 */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int p = leftBound(arr, x); // 找到第一个大于等于 x 的位置
        int left = p - 1, right = p; // 左开右开，初始时窗口为空
        List<Integer> res = new ArrayList<>();
        while (res.size() < k) {
            if (left >= 0 && (right >= arr.length || x - arr[left] <= arr[right] - x)) {
                // right >= arr.length：右边没数了，只能取左边
                res.add(0, arr[left]); // 在结果列表的头部添加 arr[left]
                left--;
            } else {
                res.add(arr[right]); // 在结果列表的尾部添加 arr[right]
                right++;
            }
        }
        return res;
    }

    private int leftBound(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] == target) {
                return mid;
            }
        }
        return left; // left 是第一个大于等于 target 的位置
    }

    public static void main(String[] args) {
        FindClosestElements solution = new FindClosestElements();
        int[] arr = {1, 2, 3, 4, 5};
        int k = 4;
        int x = 3;
        System.out.println(solution.findClosestElements(arr, k, x));
    }
}
