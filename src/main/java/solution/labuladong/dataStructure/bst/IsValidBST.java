package solution.labuladong.dataStructure.bst;

import basic.TreeNode;
import utils.TreeNodeUtil;

public class IsValidBST {
    /* 98. 验证二叉搜索树 */
    public boolean isValidBST(TreeNode root){
        return isValidBST(root, null, null);
    }
    private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max){
        if(root == null) return true;
        if(min != null && root.val <= min.val) return false;
        if(max != null && root.val >= max.val) return false;
        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }

    public static void main(String[] args) {
        IsValidBST isValidBST = new IsValidBST();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{2,1,3});
        System.out.println(isValidBST.isValidBST(root));
    }

}
