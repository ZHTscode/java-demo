package solution.labuladong.dataStructure.bst;

import basic.TreeNode;
import utils.TreeNodeUtil;

public class KthSmallest {
    /* 230. 二叉搜索树中第K小的元素 */
    private int res;
    private int rank; // 当前元素的排名
    public int kthSmallest(TreeNode root, int k) {
        res = 0;
        rank = 0;
        // BST 的中序遍历特性
        traverse(root, k);
        return res;
    }

    private void traverse(TreeNode root, int k) {
        if (root == null) return;
        traverse(root.left, k);
        // 中序代码位置
        rank++;
        if (rank == k) { // 找到第 k 小的元素
            res = root.val;
            return;
        }
        traverse(root.right, k);
    }

    public static void main(String[] args) {
        KthSmallest kthSmallest = new KthSmallest();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{3, 1, 4, -1, 2});
        TreeNodeUtil.printTreeShape(root);
        System.out.println(kthSmallest.kthSmallest(root, 1));
    }
}