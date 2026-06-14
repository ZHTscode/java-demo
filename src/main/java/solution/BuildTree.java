package solution;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/* 核心：前序定根，中序分左右
   前序[0] 是当前树的根节点
   在中序中找到根节点的位置 idx
   中序[0...idx-1] 是左子树，[idx+1...end] 是右子树
   根据左子树大小，划分前序序列
   递归构建左右子树 */

public class BuildTree {
    /* 105. 从前序与中序遍历序列构造二叉树 */
    /* 解法一：递归
       用哈希表存储中序遍历的值
       索引映射，加速查找 */
    private Map<Integer, Integer> inorderMap = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 1. 建立中序遍历的索引映射
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i); // 值 → 索引
        }
        // 2. 递归构建
        return build(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }
    // 传入两个数组和左右边界
    private TreeNode build(int[] preorder, int preLeft, int preRight,
                           int[] inorder, int inLeft, int inRight) {
        // 3. 递归终止条件
        if (preLeft > preRight || inLeft > inRight) return null; // 左右指针矛盾
        // 4. 前序的第一个元素是根节点
        int rootVal = preorder[preLeft]; // 根节点值
        TreeNode root = new TreeNode(rootVal);
        // 5. 在中序中找到根节点的位置
        int inRootIdx = inorderMap.get(rootVal); // 根节点在中序数组中的位置
        // 6. 计算左子树的大小
        int leftSize = inRootIdx - inLeft; // 左子树的大小
        /* 前序数组：[根][左子树...][右子树...]
                       ↑         ↑
                    preLeft   preRight
                       ←leftSize→
           左子树结束位置 = preLeft + leftSize */
        // 7. 递归构建左子树
        root.left = build(preorder, preLeft + 1, preLeft + leftSize,
                inorder, inLeft, inRootIdx - 1);
        // 8. 递归构建右子树
        root.right = build(preorder, preLeft + leftSize + 1, preRight,
                inorder, inRootIdx + 1, inRight);
        return root;
    }
    /* 解法二：迭代
       前序数组为主体，中序数组为参考，用栈保存节点
       栈顶 = 中序当前数 时，说明栈顶节点的左子树走完了，开始处理栈顶的右子树
       前序 [3 9 1 2 5 6 20 15 7]
       中序 [1 9 5 2 6 3 15 20 7] */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        TreeNode root = new TreeNode(preorder[0]); // 根节点
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root); // 根节点入栈
        int inorderIdx = 0; // 中序遍历索引

        for (int i = 1; i < preorder.length; i++) { // i：先序遍历索引
            TreeNode node = stack.peek(); // 获取栈顶元素
            int val = preorder[i]; // 前序当前节点值

            if (node.val != inorder[inorderIdx]) { // 题目限定无重复元素，向左走到底
                node.left = new TreeNode(val); // 左子树节点
                stack.push(node.left); // 左子树节点入栈
            }
            // 此时栈顶元素 = 中序当前节点，说明栈顶节点已无左子树，开始处理栈顶的右子树
            else { // 到右子树
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIdx]) {
                    node = stack.pop(); // 栈顶元素出栈
                    inorderIdx++; // 中序索引右移，循环判断
                }
                node.right = new TreeNode(val); // 栈顶元素的右子树节点
                stack.push(node.right); // 右子树节点入栈
            }
        }
        return root;
    }

    public static void main(String[] args) {
        BuildTree bt = new BuildTree();
        int[] preorder = {3,9,1,2,5,6,20,15,7};
        int[] inorder = {1,9,5,2,6,3,15,20,7};
        TreeNode root = bt.buildTree2(preorder, inorder);
        TreeNodeUtil.printTreeShape(root);
    }

}
