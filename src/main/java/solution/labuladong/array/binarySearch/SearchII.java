package solution.labuladong.array.binarySearch;

public class SearchII {
    /* 33. 搜索旋转排序数组 */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] <= nums[mid]) { // 左半边有序
                if (nums[left] <= target && target < nums[mid]) { // 目标在左半边
                    right = mid - 1;
                } else {
                    left = mid + 1; // 目标在右半边
                }
            } else { // 右半边有序
                if (nums[mid] < target && target <= nums[right]) { // 目标在右半边
                    left = mid + 1; // 目标在右半边
                } else {
                    right = mid - 1; // 目标在左半边
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SearchII solution = new SearchII();
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        System.out.println(solution.search(nums, target));
    }
}
