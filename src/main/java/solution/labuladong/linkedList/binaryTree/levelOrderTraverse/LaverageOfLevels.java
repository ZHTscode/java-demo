package solution.labuladong.linkedList.binaryTree.levelOrderTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LaverageOfLevels {
    /* 637. 二叉树的层平均值 */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new LinkedList<>();
        if (root == null) return res;
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            // 记录当前层所有节点之和
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
                sum += cur.val;
            }
            res.add(1.0 * sum / size); // 强制变成浮点数除法，避免整数除法直接截断
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{3,9,20,15,7});
        TreeNodeUtil.printTreeShape(root);
        LaverageOfLevels solution = new LaverageOfLevels();
        System.out.println(solution.averageOfLevels(root));
    }
}
