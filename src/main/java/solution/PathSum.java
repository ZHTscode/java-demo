package solution;

import basic.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class PathSum {
    // 方法一：前缀和 + DFS + 回溯 （最优）
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefixMap = new HashMap<>();
        // 前缀和：从根节点到当前节点的路径上，所有节点值的累加和
        prefixMap.put(0L, 1); // key = 前缀和，value = 该前缀和出现的次数
        return dfs(root, 0L, targetSum, prefixMap);
    }
    private int dfs(TreeNode node, long currSum, int target, Map<Long, Integer> prefixMap) {
        if (node == null) return 0;
        currSum += node.val;
        int count = prefixMap.getOrDefault(currSum - target, 0); // 获取前缀和为 currSum - target 的路径数
        prefixMap.put(currSum, (prefixMap.getOrDefault(currSum, 0) + 1)); // 更新前缀和出现的次数
        count += dfs(node.left, currSum, target, prefixMap); // 递归遍历左子树
        count += dfs(node.right, currSum, target, prefixMap); // 递归遍历右子树
        // 左子树遍历完后，不能影响右子树
        prefixMap.put(currSum, prefixMap.get(currSum) - 1); // 回溯：删除当前节点的前缀和
        // 最终返回的是：以当前节点为终点的合法路径数 + 当前子树中的所有合法路径数
        return count;
    }
    /* 官方题解
    * private int dfs(TreeNode root, int targetSum, Map<Long, Integer> pre, long curr) {
        if (root == null) return 0;
        int res = 0;
        curr += root.val;
        res = pre.getOrDefault(curr - targetSum, 0);
        pre.put(curr, pre.getOrDefault(curr, 0) + 1);
        res += dfs(root.left, targetSum, pre, curr);
        res += dfs(root.right, targetSum, pre, curr);
        pre.put(curr, pre.getOrDefault(curr, 0) - 1);
        return res;
    }*/

    // 方法二：双重递归
    public int pathSum2(TreeNode root, int targetSum) {
        if (root == null) return 0;
        // “以当前节点为端点的路径数” + “左子树中所有合法路径数” + “右子树中所有合法路径数”
        return dfs2(root, targetSum) +
                pathSum2(root.left, targetSum) +
                pathSum2(root.right, targetSum);
    }
    // 从 node 开始向下找，路径和为 targetSum 的路径数量
    private int dfs2(TreeNode node, long targetSum) {
        if (node == null) return 0;
        int count = 0;
        if (node.val == targetSum) count++;
        count += dfs2(node.left, targetSum - node.val);
        count += dfs2(node.right, targetSum - node.val);
        return count;
    }

    public static void main(String[] args) {
        PathSum solution = new PathSum();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
//        root.right = new TreeNode(-3);
        root.right = new TreeNode(5);
//        root.left.left = new TreeNode(3);
//        root.left.right = new TreeNode(2);
//        root.left.left.left = new TreeNode(3);
//        root.left.left.right = new TreeNode(-2);
//        root.left.right.right = new TreeNode(1);
//        root.right.right = new TreeNode(11);
//        int targetSum = 8;
        int targetSum = 15;
        int result = solution.pathSum(root, targetSum);
        System.out.println(result);
    }
}