package solution.labuladong.linkedList.binaryTree.recursionTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

public class PseudoPalindromicPaths {
    /* 1457. 二叉树中的伪回文路径 */
    // 计数数组，题目说了 1 <= root.val <= 9
    int[] count = new int[10];
    int res = 0;
    public int pseudoPalindromicPaths(TreeNode root) {
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) return;
        // 前序位置
        count[root.val]++;

        if (root.left == null && root.right == null) { // 遇到叶子节点，判断路径是否是伪回文串
            // 路径上出现奇数次的数字个数大于 1，则不可能组成回文串，反之则可以组成回文串
            int odd = 0;
            for (int n : count) {
                if (n % 2 == 1) odd++;
            }
            if (odd <= 1) res++;
        }

        // 二叉树遍历框架
        traverse(root.left);
        traverse(root.right);

        // 后序位置
        count[root.val]--;
    }

    public static void main(String[] args) {
        PseudoPalindromicPaths pseudoPalindromicPaths = new PseudoPalindromicPaths();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{2,3,1,3,1,-1,1});
        System.out.println(pseudoPalindromicPaths.pseudoPalindromicPaths(root));
    }
}
