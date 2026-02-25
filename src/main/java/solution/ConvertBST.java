package solution;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.Stack;

public class ConvertBST {
    /* 538.把叉搜索树转换为累加树
       核心：反向中序
       要求：每个节点 = 原值 + 所有比它大的节点值之和
       如果从大到小遍历，维护一个累加和 sum
       每次访问节点时：node.val += sum，然后更新 sum = node.val */
    // 解法一：递归
    private int sum = 0; // 累加和
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        // 1. 先遍历右子树（更大的值）
        convertBST(root.right);
        // 2. 处理当前节点
        sum += root.val;
        root.val = sum;
        // 3. 再遍历左子树（更小的值）
        convertBST(root.left);
        return root;
    }
    // 解法二：迭代（用栈模拟递归）
    public TreeNode convertBST2(TreeNode root) {
        if (root == null) return null;
        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<>();
        int sum = 0;
        while (curr != null || !stack.isEmpty()) {
            // 1. 一直往右走
            while (curr != null) {
                stack.push(curr);
                curr = curr.right;
            }
            // 2. 处理节点
            curr = stack.pop();
            sum += curr.val;
            curr.val = sum;
            // 3. 转向左子树
            curr = curr.left;
        }
        return root;
    }
    // 解法三：Morris遍历
    public TreeNode convertBST3(TreeNode root) {
        TreeNode curr = root;
        int sum = 0;
        while (curr != null) {
            if (curr.right == null) {
                // 无右子树，处理当前节点，转向左
                sum += curr.val;
                curr.val = sum;
                curr = curr.left;
            } else {
                // 找右子树的最左节点（后继）
                TreeNode successor = getSuccessor(curr);
                if (successor.left == null) {
                    // 第一次访问，建立线索
                    successor.left = curr;
                    curr = curr.right;
                } else {
                    // 第二次访问，恢复树结构
                    successor.left = null;
                    sum += curr.val;
                    curr.val = sum;
                    curr = curr.left;
                }
            }
        }
        return root;
    }
    private TreeNode getSuccessor(TreeNode node) {
        TreeNode successor = node.right;
        while (successor.left != null && successor.left != node) {
            successor = successor.left;
        }
        return successor;
    }

    public static void main(String[] args) {
        ConvertBST cbst = new ConvertBST();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{10,5,15,2,7,12,18});
        TreeNode result = cbst.convertBST2(root);
        TreeNodeUtil.printTreeShape(result);
    }
}
