package solution.labuladong.preSum;

import java.util.Arrays;

public class ProductExceptSelf {
    /* 238. 除自身以外数组的乘积 */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        // 从左到右的前缀积，prefix[i]：前i个元素乘积（包含i）
        int[] prefix = new int[n+1];
        prefix[0] = 1; // 前0个元素的乘积为1
        for(int i=1; i<=n; i++){
            prefix[i] = prefix[i-1] * nums[i-1];
        }
        // 从右到左的后缀积，suffix[i]：从下标i乘到最后一个元素的结果（包含i）
        int[] suffix = new int[n];
        suffix[n-1] = nums[n-1];
        for(int i=n-2; i>=0; i--){
            suffix[i] = suffix[i+1] * nums[i];
        }

        int[] res = new int[n];
        res[0] = suffix[1];
        res[n-1] = prefix[n-1];
        for(int i=1; i<n-1; i++){
            res[i] = prefix[i] * suffix[i+1];
        }
        return res;
    }

    public static void main(String[] args){
        ProductExceptSelf p = new ProductExceptSelf();
        int[] nums = {1,2,3,4};
        System.out.println(Arrays.toString(p.productExceptSelf(nums)));
    }
}
