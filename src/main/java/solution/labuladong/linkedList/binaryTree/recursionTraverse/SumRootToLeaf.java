package solution.labuladong.linkedList.binaryTree.recursionTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

public class SumRootToLeaf {
    /* 1022. 从根到叶的二进制数之和 */
    int path = 0;
    int res = 0;
    public int sumRootToLeaf(TreeNode root) {
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) return;
        // 前序位置
        path = path << 1 | root.val;

        if (root.left == null && root.right == null) { // 叶子节点
            res += path;
        }

        traverse(root.left);
        traverse(root.right);
        // 后序位置
        path = path >> 1; // 右移一位，等价 path / 2，回溯撤销
    }

    public static void main(String[] args) {
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{1,0,1,0,1,0,1});
        SumRootToLeaf sumRootToLeaf = new SumRootToLeaf();
        System.out.println(sumRootToLeaf.sumRootToLeaf(root));
    }
}
