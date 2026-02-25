package solution;

public class SingleNumber {
    /* 136.只出现一次的数字 */
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num; // single = single 异或 num
        }
        // 所有成对的数字异或结果为0，0与任意数字异或结果为数字本身
        return single;
    }

    public static void main(String[] args) {
        SingleNumber solution = new SingleNumber();
        int[] nums = {4,1,2,1,2};
        System.out.println(solution.singleNumber(nums));
    }
}
