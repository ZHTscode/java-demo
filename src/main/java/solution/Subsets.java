package solution;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    /* 78. 子集 */
    // 存放符合条件结果的集合（全局变量，两个解法通用）
    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    /* 解法一：位运算迭代法
       用一个整数 mask（掩码）代表一种选择方案
       mask 的二进制每一位对应 nums 的一个元素：
       第 i 位为 1 → 选 nums[i]
       第 i 位为 0 → 不选 nums[i] */
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) { // (1 << n) = 1 * (2 的 n 次方)
            t.clear(); // 清空当前子集
            for (int i = 0; i < n; ++i) { // 遍历每一位，检查是否选中
                if ((mask & (1 << i)) != 0) { // 判断 mask 的第 i 位是否为 1
                    t.add(nums[i]); // 如果是 1，加入该元素
                }
            }
            ans.add(new ArrayList<Integer>(t));
        }
        return ans;
    }
    /* 解法二：递归法实现子集枚举（推荐）
       类似 39.组合总和 解法二 */
    public List<List<Integer>> subsets2(int[] nums) {
        dfs(0, nums);
        return ans;
    }
    public void dfs(int cur, int[] nums) {
        // 1. 终止条件：已经处理完所有元素
        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(t)); // 保存当前路径
            return;
        }
        // 2. 选 nums[cur]
        t.add(nums[cur]); // 做选择
        dfs(cur + 1, nums); // 递归进入下一层
        t.remove(t.size() - 1); // 撤销选择（回溯）
        // 3. 不选 nums[cur]
        dfs(cur + 1, nums); // 直接递归，不加元素
    }

    public static void main(String[] args) {
        Subsets s = new Subsets();
        System.out.println(s.subsets(new int[]{1,2,3}));
    }
}
