package solution.labuladong.linkedList.binaryTree.levelOrderTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LargestValues {
    // BFS 解法
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);
        // while 循环控制从上向下一层层遍历
        while (!q.isEmpty()) {
            int sz = q.size();
            // 记录这一层的最大值
            int levelMax = Integer.MIN_VALUE;
            // for 循环控制每一层从左向右遍历
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                levelMax = Math.max(levelMax, cur.val);
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            res.add(levelMax);
        }
        return res;
    }

    // DFS 解法
    // 用 array 存储，因为要用索引随机访问
    List<Integer> res = new ArrayList<>();
    public List<Integer> largestValues2(TreeNode root) {
        if (root == null) return res;
        traverse(root, 0);
        return res;
    }
    void traverse(TreeNode root, int depth) {
        if (root == null) return;
        if (depth >= res.size()) { // 当前节点的深度第一次到达
            res.add(root.val); // 添加当前层的最大值
        } else { // 记录当前行的最大值
            res.set(depth, Math.max(res.get(depth), root.val)); // 记录当前行的最大值
        }
        traverse(root.left, depth + 1);
        traverse(root.right, depth + 1);
    }

    public static void main(String[] args) {
        LargestValues largestValues = new LargestValues();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{1,3,2,5,3,-1,9});
        TreeNodeUtil.printTreeShape(root);
        System.out.println(largestValues.largestValues(root));
        System.out.println(largestValues.largestValues2(root));
    }
}
