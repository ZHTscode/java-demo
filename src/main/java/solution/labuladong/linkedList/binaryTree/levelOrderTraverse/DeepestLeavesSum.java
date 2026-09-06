package solution.labuladong.linkedList.binaryTree.levelOrderTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.Deque;
import java.util.LinkedList;

public class DeepestLeavesSum {
    public int deepestLeavesSum(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int sum = 0;
        while (!q.isEmpty()) {
            sum = 0; // 进入新的一层，重置sum
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                // 累加一层的节点之和
                sum += cur.val;
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
        }
        // 现在就是最后一层的节点值和
        return sum;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{1,2,3,4,-1,6,7});
        TreeNodeUtil.printTreeShape(root);
        DeepestLeavesSum deepestLeavesSum = new DeepestLeavesSum();
        System.out.println(deepestLeavesSum.deepestLeavesSum(root));
    }
}
