package solution;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.LinkedList;
import java.util.Queue;

public class InverTree {
    // 先序遍历--从顶向下交换
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) return null;
        // 保存右子树
        TreeNode rightTree = root.right;
        // 交换左右子树的位置
        root.right = invertTree1(root.left);
        root.left = invertTree1(rightTree);
        return root;
    }
    // 中序遍历
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;
        invertTree2(root.left); // 交换左子树
        TreeNode rightNode= root.right; // 保存右节点
        root.right = root.left;
        root.left = rightNode;
        // 此时的右节点为root.left
        invertTree2(root.left);
        return root;
    }
    // 后序遍历-- 从下向上交换
    public TreeNode invertTree3(TreeNode root) {
        if (root == null) return null;
        TreeNode leftNode = invertTree3(root.left);
        TreeNode rightNode = invertTree3(root.right);
        root.right = leftNode;
        root.left = rightNode;
        return root;
    }
    // 层次遍历--直接左右交换即可
    public TreeNode invertTree4(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            TreeNode rightTree = node.right;
            node.right = node.left;
            node.left = rightTree;
            if (node.left != null)  queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return root;
    }

    public static void main(String[] args) {
        InverTree inverTree = new InverTree();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{4,2,7,1,3,6,9});
        TreeNodeUtil.printTreeShape(root);
        TreeNode result1 = inverTree.invertTree1(root);
        System.out.println(result1);
        TreeNodeUtil.printTreeShape(result1);
        TreeNode result2 = inverTree.invertTree2(root);
        System.out.println(result2);
        TreeNodeUtil.printTreeShape(result2);
        TreeNode result3 = inverTree.invertTree3(root);
        System.out.println(result3);
        TreeNodeUtil.printTreeShape(result3);
        TreeNode result4 = inverTree.invertTree4(root);
        System.out.println(result4);
        TreeNodeUtil.printTreeShape(result4);
        // 根节点内存地址始终不变，但根节点的左右子树会不断变化
    }
}
