package solution.labuladong.array.slideWindow;

public class MinSubArrayLen {
    /* 209. 长度最小的子数组 */
    public int minSubArrayLen(int s, int[] nums) {
        int left = 0, right = 0;
        int windowSum = 0;
        int res = Integer.MAX_VALUE;
        while (right < nums.length) {
            windowSum += nums[right];
            right++;
            while (windowSum >= s && left < right) {
                res = Math.min(res, right - left);
                windowSum -= nums[left];
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MinSubArrayLen solution = new MinSubArrayLen();
        int[] nums = {2,3,1,2,4,3};
        int s = 7;
        System.out.println(solution.minSubArrayLen(s, nums));
    }
}
