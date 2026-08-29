package solution.labuladong.array.twoPtrArray;

public class MoveZeroes {
    /* 283.移动零 */
    public void moveZeroes(int[] nums) {
        int slow = 0, fast = 0;
        while(fast < nums.length) {
            if (nums[fast] != 0) {
                // fast 指向的不是0就跟slow指向的交换
                int temp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = temp;

                slow++; // 要么指向0，要么指向fast
            }
            fast++;
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
