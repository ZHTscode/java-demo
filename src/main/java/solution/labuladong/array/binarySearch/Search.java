package solution.labuladong.array.binarySearch;

public class Search {
    /* 704. 二分查找 */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Search s = new Search();
        int[] nums = {-1,0,3,5,9,12};
        int target = 9;
        System.out.println(s.search(nums, target));
    }
}
