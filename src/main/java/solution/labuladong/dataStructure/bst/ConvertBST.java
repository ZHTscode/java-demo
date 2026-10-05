package solution.labuladong.dataStructure.bst;

import basic.TreeNode;
import utils.TreeNodeUtil;

public class ConvertBST {
    /* 538. 把二叉搜索树转换为累加树 */
    private int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        traverse(root);
        return root;
    }

    private void traverse(TreeNode root) {
        if (root == null) return;
        traverse(root.right); // 先右后左
        // 中序位置
        sum += root.val;
        root.val = sum;
        traverse(root.left);
    }

    public static void main(String[] args) {
        ConvertBST convertBST = new ConvertBST();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{4, 1, 6, 0, 2, 5, 7});
        TreeNodeUtil.printTreeShape(root);
        TreeNodeUtil.printTreeShape(convertBST.convertBST(root));
    }
}