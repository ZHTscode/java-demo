package solution;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.HashMap;
import java.util.Map;

public class Rob3 {
    // 力扣官方题解
    Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>(); // 不偷当前节点
    Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>(); // 偷当前节点
    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }
    public void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        dfs(node.right);

        // f.get(node) 表示偷当前节点时，以该节点为根的子树能获得的最大金额
        // g.get(node) 表示不偷当前节点时，以该节点为根的子树能获得的最大金额

        // 当前节点的值 + 不偷左子节点的最大金额 + 不偷右子节点的最大金额
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        // 不偷当前节点时，可以偷左子节点，也可以不偷左子节点，取两种情况的最大值
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }
    // 解法二
    public int rob2(TreeNode root) {
        int[] result = dfs2(root);
        return Math.max(result[0], result[1]); // 返回两种状态的最大值
    }
    private int[] dfs2(TreeNode node) {
        // 递归终止条件
        if (node == null) {
            return new int[]{0, 0}; // [不偷, 偷]
        }
        int[] left = dfs2(node.left); // 递归左子树
        int[] right = dfs2(node.right); // 递归右子树
        // 不偷当前节点：左右子节点可自由选择
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // 偷当前节点：左右子节点不能偷
        int rob = node.val + left[0] + right[0];
        return new int[]{notRob, rob};
    }

    public static void main(String[] args) {
        Rob3 rob3 = new Rob3();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{3,2,3,-1,3,-1,1});
        TreeNodeUtil.printTreeShape(root);
        System.out.println(rob3.rob2(root));
    }
}
