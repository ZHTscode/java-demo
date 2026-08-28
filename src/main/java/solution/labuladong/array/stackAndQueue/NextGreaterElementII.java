package solution.labuladong.array.stackAndQueue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class NextGreaterElementII {
    /* 503. 下一个更大元素 II */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i=2*n-1; i>=0; i--){
            while(!stack.isEmpty() && nums[i%n] >= stack.peek()){
                stack.pop();
            }
            res[i%n] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i%n]);
        }
        return res;
    }

    public static void main(String[] args) {
        NextGreaterElementII solution = new NextGreaterElementII();
        int[] nums = {1,2,3,4,3};
        int[] res = solution.nextGreaterElements(nums);
        System.out.println(Arrays.toString(res));
    }
}
