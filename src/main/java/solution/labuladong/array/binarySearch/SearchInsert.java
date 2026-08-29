package solution.labuladong.array.binarySearch;

public class SearchInsert {
    /* 35. 搜索插入位置 */
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                return mid;
            }
        }
        return left;
    }

    public static void main(String[] args){
        SearchInsert solution = new SearchInsert();
        int[] nums = {1,3,5,6};
        int target = 5;
        System.out.println(solution.searchInsert(nums, target));
    }
}
