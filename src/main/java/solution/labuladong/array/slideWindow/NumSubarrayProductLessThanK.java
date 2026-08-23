package solution.labuladong.array.slideWindow;

public class NumSubarrayProductLessThanK {
    /* 713. 乘积小于 K 的子数组 */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int left = 0, right = 0;
        int windowProduct = 1;
        int count = 0;
        while(right < nums.length){
            windowProduct *= nums[right];
            right++;
            while(windowProduct >= k && left < right){
                windowProduct /= nums[left];
                left++;
            }
            count += left < right? right - left : 0; // right - left 就是子数组的个数
        }
        return count;
    }

    public static void main(String[] args) {
        NumSubarrayProductLessThanK numSubarrayProductLessThanK = new NumSubarrayProductLessThanK();
        int[] nums = {1,2,3};
        int k = 0;
        System.out.println(numSubarrayProductLessThanK.numSubarrayProductLessThanK(nums, k));
    }
}