package solution;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.List;

public class InorderTraversal {
    /* 94. 二叉树的中序遍历 */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new java.util.ArrayList<>();
        inorderHelper(root, result);
        return result;
    }
    private void inorderHelper(TreeNode node, List<Integer> result) {
        if (node == null)   return;
        inorderHelper(node.left, result);
        result.add(node.val);
        inorderHelper(node.right, result);
    }

    public static void main(String[] args) {
        InorderTraversal i = new InorderTraversal();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{1,-1,2,3});
        System.out.println(i.inorderTraversal(root));
    }
}
