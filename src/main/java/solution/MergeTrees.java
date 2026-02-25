package solution;

import basic.TreeNode;
import utils.TreeNodeUtil;

public class MergeTrees {
    /* 617.合二叉树 */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    public static void main(String[] args) {
        MergeTrees mergeTrees = new MergeTrees();
        TreeNode t1 = TreeNodeUtil.buildTree(new Integer[]{1,3,2,5});
        TreeNode t2 = TreeNodeUtil.buildTree(new Integer[]{2,1,3,-1,4,-1,7});
        TreeNode res = mergeTrees.mergeTrees(t1, t2);
        TreeNodeUtil.printTreeShape(res);
    }
}
