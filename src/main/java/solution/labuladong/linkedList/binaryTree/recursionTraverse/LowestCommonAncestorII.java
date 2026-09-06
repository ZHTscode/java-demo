package solution.labuladong.linkedList.binaryTree.recursionTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

public class LowestCommonAncestorII {
    private boolean foundP = false, foundQ = false;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = lca(root, p, q);
        if (foundP && foundQ) {
            return res;
        }
        return null;
    }
    private TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        TreeNode left = lca(root.left, p, q);
        TreeNode right = lca(root.right, p, q);
        if (root == p || root == q) {
            if (root == p) foundP = true;
            if (root == q) foundQ = true;
            return root;
        }
        if(left == null) return right;
        if(right == null) return left;
        return root;
    }

    public static void main(String[] args) {
        LowestCommonAncestorII lca = new LowestCommonAncestorII();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{3,5,1,6,2,0,8,-1,-1,7,4});
        TreeNodeUtil.printTreeShape(root);
        TreeNode p = TreeNodeUtil.findNode(root, 6);
        TreeNode q = TreeNodeUtil.findNode(root, 4);
        TreeNode res = lca.lowestCommonAncestor(root, p, q);
        System.out.println(res.val);
    }
}
