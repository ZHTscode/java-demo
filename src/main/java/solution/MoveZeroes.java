package solution;

public class MoveZeroes {
    /* 283.移动零 */
    public void moveZeroes(int[] nums) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) { // nums[j] 不为 0 时
                // 交换 nums[i] 和 nums[j]
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                // i 加 1
                i++;
            }
        }
    }
    public static void main(String[] args) {
        MoveZeroes m = new MoveZeroes();
        int[] nums = {0,1,0,3,12};
        m.moveZeroes(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
