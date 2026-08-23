package solution.labuladong.array.twoPtrArray;

import java.util.Arrays;

public class TwoSum {
    /* 167. 两数之和 II - 输入有序数组 */
    public int[] twoSum(int[] numbers, int target){
        int left = 0, right = numbers.length - 1;
        while(left < right){
            int sum = numbers[left] + numbers[right];
            if(sum == target){
                return new int[]{left + 1, right +1};
            } else if(sum < target){
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        TwoSum solution = new TwoSum();
        int[] numbers = {2,7,11,15};
        int target = 9;
        int[] result = solution.twoSum(numbers, target);
        System.out.println(Arrays.toString(result));
    }

}
