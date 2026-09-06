package solution.labuladong.linkedList.binaryTree.recursionTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

public class LowestCommonAncestorBST {
    /* 235. 二叉搜索树的最近公共祖先 */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (p.val > q.val) {
            // 保证 p.val <= q.val，便于后续情况讨论
            return lowestCommonAncestor(root, q, p);
        }
        if (root.val >= p.val && root.val <= q.val) {
            // p <= root <= q
            // 即 p 和 q 分别在 root 的左右子树，那么 root 就是 LCA
            return root;
        }
        if (root.val > q.val) {
            // p 和 q 都在 root 的左子树，那么 LCA 在左子树
            return lowestCommonAncestor(root.left, p, q);
        } else {
            // p 和 q 都在 root 的右子树，那么 LCA 在右子树
            return lowestCommonAncestor(root.right, p, q);
        }
    }

    public static void main(String[] args) {
        LowestCommonAncestorBST lca = new LowestCommonAncestorBST();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{6,2,8,0,4,7,9});
        TreeNodeUtil.printTreeShape(root);
        TreeNode p = TreeNodeUtil.findNode(root, 6);
        TreeNode q = TreeNodeUtil.findNode(root, 4);
        TreeNode res = lca.lowestCommonAncestor(root, p, q);
        System.out.println(res.val);
    }
}
