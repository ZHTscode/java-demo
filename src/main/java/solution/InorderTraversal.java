package solution;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.Deque;
import java.util.List;

public class InorderTraversal {
    /* 94. 二叉树的中序遍历 */
    /* 解法一：递归 */
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
    /* 解法二：迭代 */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new java.util.ArrayList<>();
        Deque<TreeNode> stack = new java.util.ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) { // 左走到空，沿途节点压栈
                stack.push(root);
                root = root.left;
            }
            root = stack.pop(); // 栈顶节点出栈
            result.add(root.val); // 栈顶节点放入结果集
            root = root.right; // 右子树继续左走到空，沿途节点压栈
        }
        return result;
    }

    public static void main(String[] args) {
        InorderTraversal i = new InorderTraversal();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{1,-1,2,3});
        TreeNodeUtil.printTreeShape(root);
        System.out.println(i.inorderTraversal2(root));
    }
}
