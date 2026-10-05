package solution.labuladong.dataStructure.bst;

import basic.TreeNode;
import utils.TreeNodeUtil;

public class InsertIntoBST {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 找到空位置插入新节点
        if (root == null) return new TreeNode(val);
        if (val > root.val) // 插在右子树
            root.right = insertIntoBST(root.right, val);
        if (val < root.val) // 插在左子树
            root.left = insertIntoBST(root.left, val);
        return root;
    }

    public static void main(String[] args) {
        InsertIntoBST insertIntoBST = new InsertIntoBST();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{4, 2, 7, 1, 3});
        TreeNodeUtil.printTreeShape(root);
        TreeNode result = insertIntoBST.insertIntoBST(root, 5);
        System.out.println(result.val);
    }
}
