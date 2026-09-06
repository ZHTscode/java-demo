package solution.labuladong.linkedList.binaryTree.levelOrderTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.Deque;
import java.util.LinkedList;

public class IsCompleteTree {
    /* 958. 二叉树的完全性检验 */
    public boolean isCompleteTree(TreeNode root) {
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean end = false; // 是否遍历完所有非空节点
        // while 循环控制从上向下一层层遍历
        while (!q.isEmpty()) {
            int sz = q.size();
            // for 循环控制每一层从左向右遍历
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                if (cur == null) {
                    // 第一次遇到 null 时 end 变成 true
                    end = true;
                } else {
                    if (end) {
                        // end 为 true 时遇到非空节点
                        return false;
                    }
                    // 将下一层节点放入队列，不用判断是否非空
                    q.offer(cur.left);
                    q.offer(cur.right);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{1,2,3,-1,4,5,6});
        TreeNodeUtil.printTreeShape(root);
        IsCompleteTree solution = new IsCompleteTree();
        System.out.println(solution.isCompleteTree(root));
    }
}
