package solution.labuladong.math;

public class FirstMissingPositive {
    /* 41. 缺失的第一个正数 */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // 第一步：把无效数字（非正数或超范围）替换成哨兵
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = n + 1;
            }
        }
        // 第二步：用符号位标记 x 出现过，把 nums[x-1] 取反
        for (int i = 0; i < n; i++) {
            int x = Math.abs(nums[i]);
            if (x <= n) {
                nums[x - 1] = -Math.abs(nums[x - 1]);
            }
        }
        // 第三步：第一个正数所在下标 i，说明 i+1 缺失
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    public static void main(String[] args) {
        FirstMissingPositive solution = new FirstMissingPositive();
        int[] nums = {3, 4, -1, 1};
        int result = solution.firstMissingPositive(nums);
        System.out.println(result); // 输出 2
    }
}