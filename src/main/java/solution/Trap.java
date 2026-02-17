package solution;

// 核心：对于任意位置 i，它能接的雨水量 = min(左侧最大高度, 右侧最大高度) - height[i]

public class Trap {
    public int trap(int[] height) {
        // 边界条件：如果数组为空或长度为0，直接返回0
        if (height == null || height.length == 0) return 0;
        int left = 0, right = height.length - 1;  // 左右指针
        int leftMax = 0, rightMax = 0;            // 记录左侧和右侧已遍历部分的最大高度
        int ans = 0;                              // 累计雨水量
        // 总是选择较矮的一侧处理：min(左侧最大高度, 右侧最大高度)
        while (left < right) {
            if (height[left] < height[right]) { // 左侧较矮，处理左边
                if (height[left] >= leftMax) {
                    // 当前高度不小于左侧最大值：不能积水，更新左侧最大值
                    leftMax = height[left];
                } else {
                    // 当前高度小于左侧最大值：可以积水，积水量 = 左侧最大值 - 当前高度
                    ans += leftMax - height[left];
                }
                left++; // 左指针右移
            }
            else { // 右侧较矮，处理右边
                if (height[right] >= rightMax) {
                    // 当前高度不小于右侧最大值：不能积水，更新右侧最大值
                    rightMax = height[right];
                }
                else {
                    // 当前高度小于右侧最大值：可以积水，积水量 = 右侧最大值 - 当前高度
                    ans += rightMax - height[right];
                }
                right--; // 右指针左移
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Trap solution = new Trap();
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int result = solution.trap(height);
        System.out.println(result);
    }
}
