package solution.labuladong.linkedList.binaryTree.recursionTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

public class Flatten {
    /* 114. 二叉树展开为链表$$ */
    public void flatten(TreeNode root) {
        // base case
        if (root == null) return;
        // 利用定义，把左右子树拉平
        flatten(root.left);
        flatten(root.right);
        // 后序遍历位置
        // 1、左右子树已经被拉平成一条链表
        TreeNode left = root.left;
        TreeNode right = root.right;
        // 2、将左子树作为右子树
        root.left = null;
        root.right = left;
        // 3、将原先的右子树接到当前右子树的末端
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }


    public TreeNode pre = null; // 保存右子树的引用
    public void flatten3(TreeNode root) {
        if (root == null) return;
        // 先右后左
        flatten3(root.right);
        flatten3(root.left);
        root.right = pre;
        root.left = null;
        pre = root; // 记录已经拼好的链表的头部，供上层递归使用
    }


    // 维护一个全局指针指向当前正在遍历的节点，前序遍历把节点追加到指针后面（原创）
    TreeNode prev = null;
    public void flatten2(TreeNode root) {
        if(root == null) return;
        TreeNode leftSave = root.left;
        TreeNode rightSave = root.right;
        // 前序：处理当前节点
        if(prev != null){
            prev.right = root;
            prev.left = null;
        }
        prev = root;
        prev.left = null;
        // 递归遍历原来的左、右（用备份，不能用root.left/root.right，已经被改坏）
        flatten2(leftSave);
        flatten2(rightSave);
    }

    public static void main(String[] args) {
        Flatten flatten = new Flatten();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{1,2,5,3,4,-1,6});
        TreeNodeUtil.printTreeShape(root);
        flatten.flatten(root);
        TreeNodeUtil.printTreeShape(root);
    }
}
