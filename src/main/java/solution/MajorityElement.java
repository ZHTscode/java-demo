package solution;

public class MajorityElement {
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int count = 1; // 初始化计数器为1
        int candidate = nums[0]; // 初始化候选众数为第一个元素
        // 第一阶段：使用摩尔投票法找到候选者
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i]; // 更新候选众数
                count = 1; // 重置计数器为1
            }
            else if (candidate == nums[i]) count++; // 增加计数器
            else count--; // 减少计数器
        }
        System.out.println("Candidate: " + candidate);
        // 第二阶段：验证候选者是否真的是多数元素
        count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            }
        }
        // 如果候选者出现次数超过一半，则返回它；否则返回0（或根据题目要求处理）
        return count > nums.length / 2 ? candidate : 0;
    }

    public static void main(String[] args) {
        MajorityElement majorityElement = new MajorityElement();
        int[] nums = {6, 6, 5, 5, 6, 5, 7, 7};
        System.out.println(majorityElement.majorityElement(nums));
    }
}
