package solution;

public class MaxProduct {
    public int maxProduct(int[] nums) {
        // dpMax[i] 表示以 nums[i] 结尾的子数组的最大乘积
        // dpMin[i] 表示以 nums[i] 结尾的子数组的最小乘积
        int[] dpMax = new int[nums.length];
        int[] dpMin = new int[nums.length];
        // 初始化：单个元素时最大和最小都是它自己
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        int maxProduct = nums[0];  // 全局最大乘积
        /* 关键逻辑：每个位置有三种可能
           1. 从当前元素重新开始（比如遇到0后）
           2. 前一个最大乘积 * 当前元素
           3. 前一个最小乘积 * 当前元素（负负得正的情况）*/
        for (int i = 1; i < nums.length; i++) {
            int candidate1 = dpMax[i-1] * nums[i]; // 延续之前的乘积
            int candidate2 = dpMin[i-1] * nums[i]; // 负负得正的情况
            int currentNum = nums[i];             // 独立成子数组
            // 三者中取最大和最小
            dpMax[i] = Math.max(currentNum, Math.max(candidate1, candidate2));
            dpMin[i] = Math.min(currentNum, Math.min(candidate1, candidate2));
            // 更新全局最大值
            maxProduct = Math.max(maxProduct, dpMax[i]);
        }
        return maxProduct;
    }

    public int maxProduct2(int[] nums) {
        int len = nums.length;
        int res = nums[0];
        // 从左到右遍历数组，计算乘积
        int product = 1;
        for (int i = 0; i < len; i++) {
            product *= nums[i];
            res = Math.max(res, product);
            if (product == 0) product = 1;
        }
        // 从右到左遍历数组，计算乘积
        product = 1;
        for(int i = len - 1; i >= 0; i--){
            product *= nums[i];
            res=Math.max(res, product);
            if (product == 0) product = 1;
        }
        return res;
    }
    /*
    * 考虑数组 nums = [-1, -2, -3]
    * 只从左到右遍历的问题：
    * i=0: product = -1, res = max(-1, -1) = -1
    * i=1: product = -1 * -2 = 2, res = max(-1, 2) = 2
    * i=2: product = 2 * -3 = -6, res = max(2, -6) = 2
    * 得到的结果是2（子数组[-1, -2]）
    * 但从右到左遍历会发现更好的结果：
    * i=2: product = -3, res = max(2, -3) = 2
    * i=1: product = -3 * -2 = 6, res = max(2, 6) = 6
    * i=0: product = 6 * -1 = -6, res = max(6, -6) = 6
    * 从右到左遍历发现了真正的最大乘积6（子数组[-2, -3]）
    * 当数组中包含负数时，不同方向的遍历可能会产生不同的结果，双向遍历可以确保找到全局最优解。
    */

    public static void main(String[] args) {
        MaxProduct mp = new MaxProduct();
        int[] nums = {1, 2, 3, -2, 4};
        System.out.println(mp.maxProduct(nums));
    }
}

