package solution;

public class CanJump {
    /* 55. 跳跃游戏
       数组中的每个元素代表在该位置可以跳跃的最大长度
       核心：贪心算法
       维护一个最远距离 rightmost，如果当前索引 i <= rightmost → 可以跳到该索引
       rightmost 到达或超过数组末尾 → 返回 true
       遍历结束仍未到达末尾 → 返回 false */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CanJump c = new CanJump();
        int[] nums = {2,3,1,1,4};
        System.out.println(c.canJump(nums));
    }
}
