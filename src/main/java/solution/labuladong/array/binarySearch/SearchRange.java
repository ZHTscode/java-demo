package solution.labuladong.array.binarySearch;

import java.util.Arrays;

public class SearchRange {
    /* 34. 在排序数组中查找元素的第一个和最后一个位置 */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[] {-1, -1};
        res[0] = binarySearch(nums, target, true); // 查找左边界
        res[1] = binarySearch(nums, target, false); // 查找右边界
        return res;
    }
    private int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1;
        int res = -1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] > target) {
                right = mid - 1;
            } else if(nums[mid] < target) {
                left = mid + 1;
            } else if(nums[mid] == target) {
                res = mid;
                if(lower) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SearchRange s = new SearchRange();
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        System.out.println(Arrays.toString(s.searchRange(nums, target)));
    }
}
