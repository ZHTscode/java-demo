package solution;

import basic.TreeNode;

public class MaxPathSum {
    private int maxSum;
    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxSum;
    }
    // 计算从当前节点出发的最大路径和
    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        // 递归计算左右子树的最大路径和，取正值部分
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        // 计算通过当前节点的最大路径和
        int currentPathSum = node.val + left + right;
        // 更新全局最大值
        maxSum = Math.max(maxSum, currentPathSum);
        // 返回从当前节点向上的最大路径和（只能选择左或右子树）
        return node.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        MaxPathSum solution = new MaxPathSum();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(solution.maxPathSum(root));
    }
}