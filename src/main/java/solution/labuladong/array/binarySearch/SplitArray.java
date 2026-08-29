package solution.labuladong.array.binarySearch;

public class SplitArray {
    /* 410. 分割数组的最大值 */
    int splitArray(int[] nums, int m) {
        int left = 0, right = 0;
        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canSplit(nums, m, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    boolean canSplit(int[] nums, int m, int limit) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum > limit) {
                m--;
                sum = num; // 重新开始
            }
        }
        return m > 0;
    }

    public static void main(String[] args) {
        SplitArray solution = new SplitArray();
        int[] nums = {7,2,5,10,8};
        int m = 2;
        System.out.println(solution.splitArray(nums, m));
    }
}
