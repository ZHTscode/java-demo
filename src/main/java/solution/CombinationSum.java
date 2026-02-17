package solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
  给定一个无重复元素的正整数数组 candidates 和一个目标值 target
  找出 candidates 中所有可以使数字和为 target 的组合
  同一个数字可以被无限次使用，解集不能包含重复的组合（顺序不同视为相同）
  核心：回溯 + 剪枝
  可重复选择 → 每次递归可以从当前索引开始（不是 i+1）
  避免重复组合 → 按顺序选择（不回头选前面的数）
 */

public class CombinationSum {
    /*
      解法一：循环 + 递归（最优）
      target   当前剩余需要凑出的和
      curSum   当前已选数字的和
      path     当前已选的数字列表
      start    当前可选数字的起始索引（防止重复组合）
      */
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtrack(candidates, target, 0, new ArrayList<>(), 0);
        return ans;
    }
    public void backtrack(int[] candidates, int target, int curSum, List<Integer> path, int start) {
        if (curSum > target) return;
        if (curSum == target) {
            ans.add(new ArrayList<>(path)); // 深拷贝：创建新列表，复制 path 的内容
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            path.add(candidates[i]);
//            System.out.println(path);
            backtrack(candidates, target, curSum + candidates[i], path, i);
            path.remove(path.size() - 1);
        }
    }
    /*
      解法二：双重递归
      i             当前可选数字的起始索引
      left          当前剩余需要凑出的和
      candidates    可选数字列表
      ans           结果集
      path          当前已选的数字列表
      */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(0, target, candidates, ans, path);
        return ans;
    }
    private void dfs(int i, int left, int[] candidates, List<List<Integer>> ans, List<Integer> path) {
        if (left == 0) { // 找到一个合法组合
            ans.add(new ArrayList<>(path));
            return;
        }
        if (i == candidates.length || left < candidates[i]) return;
        // 不选
        dfs(i + 1, left, candidates, ans, path);
        // 选
        path.add(candidates[i]);
        dfs(i, left - candidates[i], candidates, ans, path);
        // 回溯
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        CombinationSum solution = new CombinationSum();
        int[] candidates = {2,3,6,7};
        int target = 7;
        List<List<Integer>> result = solution.combinationSum(candidates, target);
        System.out.println(result);
    }
}
