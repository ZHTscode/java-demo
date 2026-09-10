package solution.labuladong.linkedList.binaryTree.recursionTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

public class SmallestFromLeaf {
    /* 988. 从叶结点开始的最小字符串 */
    StringBuilder path = new StringBuilder();
    String res = null;

    public String smallestFromLeaf(TreeNode root) {
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) return;
        // 前序位置
        path.append((char) ('a' + root.val));

        if (root.left == null && root.right == null) { // 找到叶子结点
            path.reverse(); // 结果字符串是从叶子向根，翻转
            String s = path.toString();
            if (res == null || res.compareTo(s) > 0) { // s字典序更小，更新 res
                res = s;
            }
            path.reverse(); // 翻转恢复
        }

        traverse(root.left);
        traverse(root.right);

        // 后序位置
        path.deleteCharAt(path.length() - 1); // 撤销选择
    }

    public static void main(String[] args) {
        SmallestFromLeaf smallestFromLeaf = new SmallestFromLeaf();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{2,2,1,-1,1,0,-1,0});
        System.out.println(smallestFromLeaf.smallestFromLeaf(root));
    }
}
