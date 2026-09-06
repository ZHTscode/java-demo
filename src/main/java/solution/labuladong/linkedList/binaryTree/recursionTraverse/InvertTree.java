package solution.labuladong.linkedList.binaryTree.recursionTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

public class InvertTree {
    /* 226. 翻转二叉树 */
    // 遍历思维
    public TreeNode invertTree(TreeNode root) {
        traverse(root);
        return root;
    }
    private void traverse(TreeNode root){
        if(root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        traverse(root.left);
        traverse(root.right);
    }

    // 分解问题思维
    public TreeNode invertTree2(TreeNode root) {
        if(root == null) return null;
        TreeNode left = invertTree2(root.left);
        TreeNode right = invertTree2(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public static void main(String[] args) {
        InvertTree invertTree = new InvertTree();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{4,2,7,1,3,6,9});
        TreeNodeUtil.printTreeShape(root);
        invertTree.invertTree(root);
        TreeNodeUtil.printTreeShape(root);
        invertTree.invertTree2(root);
        TreeNodeUtil.printTreeShape(root);
    }
}
