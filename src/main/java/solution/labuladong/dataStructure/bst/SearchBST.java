package solution.labuladong.dataStructure.bst;

import basic.TreeNode;
import utils.TreeNodeUtil;

public class SearchBST {
    /* 700. 二叉搜索树中的搜索 */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        return root.val > val ? searchBST(root.left, val) : searchBST(root.right, val);
    }

    public static void main(String[] args){
        SearchBST searchBST = new SearchBST();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{4, 2, 7, 1, 3});
        TreeNodeUtil.printTreeShape(root);
        TreeNode result = searchBST.searchBST(root, 2);
        System.out.println(result.val);
    }
}
