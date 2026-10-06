package solution.labuladong.math;

public class SingleNumber {
    /* 136. 只出现一次的数字 */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int n : nums) {
            res ^= n; // 异或找出只出现一次的数字
        }
        return res;
    }

    public static void main(String[] args) {
        SingleNumber solution = new SingleNumber();
        int[] nums = {2, 2, 3, 4, 4, 5, 5};
        int res = solution.singleNumber(nums);
        System.out.println(res);
    }
}
