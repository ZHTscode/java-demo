package solution.labuladong.array.binarySearch;

public class SearchIII {
    /* 81. 搜索旋转排序数组 II */
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            // 跳过重复元素
            while (left < right && nums[left] == nums[left + 1]) {
                left++;
            }
            while (left < right && nums[right] == nums[right - 1]) {
                right--;
            }
            // 以下同 33. 搜索旋转排序数组
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[left] <= nums[mid]) { // 左边有序
                if (nums[left] <= target && target < nums[mid]) { // 目标在左半边
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // 右边有序
                if (nums[mid] < target && target <= nums[right]) { // 目标在右半边
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SearchIII solution = new SearchIII();
        int[] nums = {2,5,6,0,0,1,2};
        int target = 0;
        System.out.println(solution.search(nums, target));
    }
}
