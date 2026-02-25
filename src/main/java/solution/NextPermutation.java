package solution;

public class NextPermutation {
    /* 31. 下一个排列 */
    public void nextPermutation(int[] nums) {
        // 步骤 1：找「下降点」i，找到最右边可变大的位置
        int i = nums.length - 2;
        // 从右往左找 第一个 nums[i] < nums[i+1] 的位置
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        // 如果找不到（i = -1），说明数组是完全降序（已是最大排列）
        // 步骤 2：找「交换点」j，让 i 位置刚好变大
        if (i >= 0) {
            int j = nums.length - 1;
            // 从右往左找 第一个 nums[j] > nums[i] 的位置
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            // 交换 nums[i] 和 nums[j]
            swap(nums, i, j);
        }
        // 步骤 3：反转 i+1 到末尾，让右边变成最小排列
        // 反转 nums[i+1] 到 nums[n-1]
        reverse(nums, i + 1);
    }
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        NextPermutation np = new NextPermutation();
        int[] nums = {1, 3, 2};
        np.nextPermutation(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
