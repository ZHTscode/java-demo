package solution;

import basic.TreeNode;
import utils.TreeNodeUtil;

public class IsSymmetric {
    /* 101. 对称二叉树 */
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

    public static void main(String[] args) {
        IsSymmetric is = new IsSymmetric();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{1,2,2,3,4,4,3});
        System.out.println(is.isSymmetric(root));
    }
}
