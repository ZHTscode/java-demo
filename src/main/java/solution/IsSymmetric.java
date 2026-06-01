package solution;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.LinkedList;
import java.util.Queue;

public class IsSymmetric {
    /* 101. 对称二叉树 */
    /* 解法一：递归 */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }
    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null || right == null) return left == right;
        return left.val == right.val
                && isSymmetric(left.left, right.right)
                && isSymmetric(left.right, right.left);
    }
    /* 解法二：迭代 BFS */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left); // 左节点入队
        queue.offer(root.right); // 右节点入队
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll(); // 左节点出队
            TreeNode right = queue.poll(); // 右节点出队
            if (left == null && right == null) continue;
            if (left == null || right == null || left.val != right.val) return false;
            queue.offer(left.left); // 左节点的左节点入队
            queue.offer(right.right); // 右节点的右节点入队
            queue.offer(left.right); // 左节点的右节点入队
            queue.offer(right.left); // 右节点的左节点入队
        }
        return true;
    }

    public static void main(String[] args) {
        IsSymmetric is = new IsSymmetric();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{1,2,2,3,4,4,3});
        System.out.println(is.isSymmetric2(root));
    }
}
