package solution.labuladong.array.binarySearch;

public class FindPeakElement {
    /* 162. 寻找峰值 */
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                // mid 是峰值或峰值在左半边
                right = mid;
            } else {
                // 峰值在右半边
                left = mid + 1;
            }
        }
        return left; // left == right，任意返回一个
    }

    public static void main(String[] args) {
        FindPeakElement solution = new FindPeakElement();
        int[] nums = {1,8,3,4,5,6,7}; // 7也是峰值
        System.out.println(solution.findPeakElement(nums));
    }
}
