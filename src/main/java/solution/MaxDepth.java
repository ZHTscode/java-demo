package solution;

import basic.TreeNode;
import utils.TreeNodeUtil;

public class MaxDepth {
    /* 104. 二叉树的最大深度 */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1; // 左右子树高度的最大值 + 1
    }

    public static void main(String[] args) {
        MaxDepth m = new MaxDepth();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{3,9,20,-1,-1,15,7});
        TreeNodeUtil.printTreeShape(root);
        System.out.println(m.maxDepth(root));
    }
}
