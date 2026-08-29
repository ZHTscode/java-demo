package solution.labuladong.array.randomSelect;

import java.util.Arrays;
import java.util.Random;

public class DisruptArray {
    /* 384. 打乱数组 */
    private int[] nums;
    private Random rand = new Random();

    public DisruptArray(int[] nums) {
        this.nums = nums;
    }

    public int[] reset() {
        return nums;
    }

    public int[] shuffle() {
        int n = nums.length;
        int[] copy = Arrays.copyOf(nums, n);
        for(int i=0; i<n; i++){
            int r = i + rand.nextInt(n-i); // 生成[i, n-1]之间的随机数
            int temp = copy[i];
            copy[i] = copy[r];
            copy[r] = temp;
        }
        return copy;
    }

    public static void main(String[] args) {
        DisruptArray solution = new DisruptArray(new int[]{1,2,3});
        System.out.println(Arrays.toString(solution.shuffle()));
        System.out.println(Arrays.toString(solution.reset()));
    }
}

