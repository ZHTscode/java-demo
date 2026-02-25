package solution;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrder {
    /* 102. 二叉树的层序遍历 */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)   return result; // 边界条件
        Queue<TreeNode> queue = new LinkedList<>(); // 队列用于 BFS
        queue.offer(root); // 根节点入队
        while (!queue.isEmpty()) {
            // 1. 获取当前层的节点数
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();
            // 2. 处理当前层的所有节点
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                // 3. 子节点入队
                if (node.left != null)  queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            // 4. 当前层加入结果
            result.add(level);
        }
        return result;
    }

    public static void main(String[] args) {
        LevelOrder l = new LevelOrder();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{3,9,20,-1,-1,15,7});
        System.out.println(l.levelOrder(root));
    }
}
