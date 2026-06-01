package solution;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.Deque;

public class MaxDepth {
    /* 104. 二叉树的最大深度 */
    /* 解法一：递归 */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = maxDepth(root.left); // 左子树高度
        int rightHeight = maxDepth(root.right); // 右子树高度
        return Math.max(leftHeight, rightHeight) + 1; // 左右子树高度的最大值 + 1
    }
    /* 解法二：BFS 层序遍历 每处理完一层深度 + 1 */
    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        int depth = 0;
        Deque<TreeNode> deque = new java.util.ArrayDeque<>();
        deque.offer(root); // 根节点入队
        while (!deque.isEmpty()) {
            int size = deque.size(); // 当前层节点数
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.poll(); // 节点出队
                if (node.left != null) deque.offer(node.left); // 左子节点入队
                if (node.right != null) deque.offer(node.right); // 右子节点入队
            }
            depth++;
        }
        return depth;
    }

    public static void main(String[] args) {
        MaxDepth m = new MaxDepth();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{3,9,20,-1,-1,15,7});
        TreeNodeUtil.printTreeShape(root);
        System.out.println(m.maxDepth(root));
    }
}
