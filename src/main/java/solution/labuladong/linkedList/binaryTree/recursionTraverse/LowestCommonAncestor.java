package solution.labuladong.linkedList.binaryTree.recursionTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

public class LowestCommonAncestor {
    /* 236. 二叉树的最近公共祖先 */
    private int found_count = 0; // 找到节点后立刻停止遍历
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q || found_count == 2){
            if(root == p || root == q) found_count++;
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        if(found_count == 2) return left; //提前返回
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{3,5,1,6,2,0,8,-1,-1,7,4});
        TreeNode p = TreeNodeUtil.findNode(root, 6);
        TreeNode q = TreeNodeUtil.findNode(root, 7);
        TreeNodeUtil.printTreeShape(root);
        LowestCommonAncestor lca = new LowestCommonAncestor();
        TreeNode res = lca.lowestCommonAncestor(root, p, q);
        System.out.println(res.val);
    }
}
