package solution;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.LinkedList;
import java.util.Queue;

public class InverTree {
    /* 226.二叉树 */
    /* 解法一：递归 从上向下按层交换 */
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) return null;
        // 交换左右子树
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        root.left = rightNode;
        root.right = leftNode;
        // 递归交换左右子树
        invertTree1(leftNode);
        invertTree1(rightNode);
        return root;
    }
    /* 解法二：迭代 BFS 层序遍历 */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root); // 根节点入队
        while (!queue.isEmpty()){
            TreeNode node = queue.poll(); // 出队
            // 交换当前节点的左右子树 (根节点的内存地址不变，改变的是根节点的左右子树)
            TreeNode rightTree = node.right;
            node.right = node.left;
            node.left = rightTree;
            // 左右节点入队
            if (node.left != null)  queue.offer(node.left); // 左节点入队
            if (node.right != null) queue.offer(node.right); // 右节点入队
        }
        return root;
    }

    public static void main(String[] args) {
        InverTree inverTree = new InverTree();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{3,9,20,-1,-1,15,7});
        TreeNodeUtil.printTreeShape(root);
        TreeNode result = inverTree.invertTree2(root);
        System.out.println(result);
        TreeNodeUtil.printTreeShape(result);
    }
}
