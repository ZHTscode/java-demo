package solution.labuladong.linkedList.binaryTree.recursionTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

public class InsertIntoMaxTree {
    /* 998. 最大二叉树II$$ */
    // 选数组最大值作为根；最大值左边子数组，构造左子树；最大值右边子数组，构造右子树
    // 把 val 添加到原数组的末尾，然后重新构造最大树，返回新树
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (root.val < val) {
            // 如果 val 是整棵树最大的，那么这棵树是 val 节点的左子树
            TreeNode temp = root;
            root = new TreeNode(val);
            root.left = temp;
        } else {
            // val 比当前根小，去右子树递归插入
            root.right = insertIntoMaxTree(root.right, val);
        }
        return root;
    }

    public static void main(String[] args) {
        InsertIntoMaxTree solution = new InsertIntoMaxTree();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{4,1,3,-1,-1,2});
        TreeNode res = solution.insertIntoMaxTree(root, 5);
        TreeNodeUtil.printTreeShape(res);
    }
}
