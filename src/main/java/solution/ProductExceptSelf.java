package solution;

public class ProductExceptSelf {
    /* 238. 除自身以外数组的乘积 */
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];
        int[] result = new int[nums.length]; // 结果数组
        result[0] = 1;
        System.out.println("result[0] = " + result[0]);
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
            System.out.println("result[" + i + "] = " + result[i]);
        }// 至此， result[i] 为 nums 前 i 个数的乘积
        int rightProduct = 1; // 右侧乘积
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] *= rightProduct; // 结果数组乘以右侧乘积
            rightProduct *= nums[i]; // 更新右侧乘积
        }
        return result;
    }
    public static void main(String[] args) {
        ProductExceptSelf productExceptSelf = new ProductExceptSelf();
        int[] nums = {1, 2, 3, 4};
        System.out.println("Input: " + java.util.Arrays.toString(nums));
        int[] result = productExceptSelf.productExceptSelf(nums);
        System.out.println("Output: " + java.util.Arrays.toString(result));
    }
}
