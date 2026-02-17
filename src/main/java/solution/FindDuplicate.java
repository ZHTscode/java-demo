package solution;

// 只有一个重复数字：除了重复数字，其他数字都只出现一次
// nums[]: 1 <= nums[i] <= n + 1
// nums.length = n + 1

public class FindDuplicate {
    // 核心：快慢指针找环入口下标，即为重复元素
    // 重复数字指向同一个下标，形成环
    public int findDuplicate(int[] nums) {
        // nums[] 不可能为 0 ：从头节点开始，肯定能跳出去，避免在头节点循环
        int slow = nums[0];
        int fast = nums[0];
        do {
            slow = nums[slow]; // 慢指针移动一步
            fast = nums[nums[fast]]; // 快指针移动两步
        } while (slow != fast);
        int ptr1 = nums[0]; // 指针1从头节点开始
        int ptr2 = slow; // 指针2从相遇节点开始
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1]; // 指针1移动一步
            ptr2 = nums[ptr2]; // 指针2移动一步
        }
        return ptr1;
    }
    public int findDuplicate2(int[] nums) {
        boolean[] flag = new boolean[nums.length];
        for(int num: nums) {
            if(flag[num]) return num;
            flag[num] = true;
        }
        return -1;
    }

    public static void main(String[] args) {
        FindDuplicate fd = new FindDuplicate();
        int[] nums = {1,3,4,2,2};
        System.out.println(fd.findDuplicate(nums));
    }
}
