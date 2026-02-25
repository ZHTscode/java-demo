package solution;

import java.util.ArrayList;
import java.util.List;

// 与 22.括号生成 类似
public class Permute {
    /* 46.全排列 */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length]; // 标记数字是否已使用
        backtrack(nums, used, path, res);
        return res;
    }
    private void backtrack(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> res) {
        // 1. 递归终止条件
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path)); // 拷贝
            return;
        }
        // 2. 遍历所有选择
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue; // 跳过已使用的数字
            // 3. 做选择
            path.add(nums[i]);
            used[i] = true;
            // 4. 递归探索
            backtrack(nums, used, path, res);
            // 5. 撤销选择（回溯）
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        Permute solution = new Permute();
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = solution.permute(nums);
        System.out.println(result);
    }
}
