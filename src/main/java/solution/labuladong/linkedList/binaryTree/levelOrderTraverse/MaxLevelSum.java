package solution.labuladong.linkedList.binaryTree.levelOrderTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.Deque;
import java.util.LinkedList;

public class MaxLevelSum {
    public int maxLevelSum(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 1; // 记录 BFS 走到的层数
        // 记录元素和最大的层号与最大元素和
        int res = 0, maxSum = Integer.MIN_VALUE;
        while (!q.isEmpty()) {
            int sz = q.size();
            int levelSum = 0;
            // 遍历这一层
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                levelSum += cur.val;
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            if (levelSum > maxSum) {
                // 更新元素和最大的层号与最大元素和
                res = depth;
                maxSum = levelSum;
            }
            depth++;
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{1,2,3,4,5,6,7});
        MaxLevelSum maxLevelSum = new MaxLevelSum();
        System.out.println(maxLevelSum.maxLevelSum(root));
    }
}
