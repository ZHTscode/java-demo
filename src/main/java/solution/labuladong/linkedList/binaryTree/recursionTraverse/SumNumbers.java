package solution.labuladong.linkedList.binaryTree.recursionTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

public class SumNumbers {
    /* 129. 求根节点到叶节点数字之和 */
    StringBuilder path = new StringBuilder();
    int res = 0;

    public int sumNumbers(TreeNode root) {
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) return;
        // 前序遍历位置，记录节点值
        path.append(root.val);

        if (root.left == null && root.right == null) {
            res += Integer.parseInt(path.toString()); // 到达叶子节点，累加路径和
        }

        // 递归遍历左右子树
        traverse(root.left);
        traverse(root.right);

        // 后续遍历位置，撤销节点值
        path.deleteCharAt(path.length() - 1);
    }

    public static void main(String[] args) {
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{1,2,3,-1,5});
        TreeNodeUtil.printTreeShape(root);
        SumNumbers sumNumbers = new SumNumbers();
        System.out.println(sumNumbers.sumNumbers(root));
    }
}
