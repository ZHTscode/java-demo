package solution.labuladong.array.binarySearch;

public class FindMin {
    /* 153. 寻找旋转排序数组中的最小值 */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) { // 右边有序
                right = mid; // 最小值在左半边（包含 mid）
            } else {
                left = mid + 1; // 最小值在右半边
            }
        }
        return nums[left]; // left 和 right 最终相遇在最小值位置
    }

    public static void main(String[] args) {
        FindMin solution = new FindMin();
        int[] nums = {3,4,5,1,2};
        System.out.println(solution.findMin(nums));
    }
}
