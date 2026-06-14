package solution;

import java.util.Arrays;

public class MoveZeroes {
    /* 283.移动零 */
    public void moveZeroes(int[] nums) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) { // 快指针右移遍历，遇到0跳过
            if (nums[j] != 0) { // 遇到非0，快慢指针指向元素交换，慢指针右移
                if(i != j){
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
                System.out.println("i = " + i + ", j = " + j);
                System.out.println(Arrays.toString(nums));
                i++;
            }
        }
    }

    public static void main(String[] args) {
        MoveZeroes m = new MoveZeroes();
        int[] nums = {1,3,0,1,0,3,12};
        m.moveZeroes(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
