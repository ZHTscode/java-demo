package solution;

// 33. 搜索旋转排序数组

public class Search {
    /* 33. 搜索旋转排序数组 */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid; // 找到目标
            // 判断哪一半是有序的
            if (nums[left] <= nums[mid]) { // 左半部分有序 [left...mid]
                if (nums[left] <= target && target < nums[mid])     right = mid - 1; // 目标在左半有序区间
                else                                                left = mid + 1;  // 目标在右半无序区间
            }
            else { // 右半部分有序 [mid...right]
                if (nums[mid] < target && target <= nums[right])    left = mid + 1;  // 目标在右半有序区间
                else                                                right = mid - 1; // 目标在左半无序区间
            }
        }
        return -1; // 未找到
    }

    public static void main(String[] args) {
        Search s = new Search();
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        int count = s.search(nums, target);
        System.out.println(count);
    }
}
