package solution.labuladong.array.stackAndQueue;

import java.util.*;

public class NextGreaterElement {
    /* 496. 下一个更大元素 I */
    public int[] nextGreaterElement(int[] nums1, int[]nums2){
        int[] greater = calculateNextGreaterElement(nums2);
        Map<Integer, Integer> numToGreater = new HashMap<>();
        for(int i=0; i<nums2.length; i++){
            numToGreater.put(nums2[i], greater[i]);
        }
        int[] res = new int[nums1.length];
        for(int i=0; i<nums1.length; i++){
            res[i] = numToGreater.get(nums1[i]);
        }
        return res;
    }

    private int[] calculateNextGreaterElement(int[] nums){
        int n = nums.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i=n-1; i>=0; i--){
            while(!stack.isEmpty() && nums[i] >= stack.peek()){
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek(); // 第一个比nums[i]大的元素
            stack.push(nums[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        NextGreaterElement solution = new NextGreaterElement();
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
        int[] res = solution.nextGreaterElement(nums1, nums2);
        System.out.println(Arrays.toString(res));
    }
}
