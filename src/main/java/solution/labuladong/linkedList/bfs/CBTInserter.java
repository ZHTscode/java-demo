package solution.labuladong.linkedList.bfs;

import basic.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class CBTInserter {
    /* 919. 完全二叉树插入器 */
    // 记录完全二叉树底部可以进行插入的节点
    private Deque<TreeNode> q = new LinkedList<>();
    private TreeNode root;

    public CBTInserter(TreeNode root) { // 根据完全二叉树输入，预处理出队列q
        this.root = root;
        // 进行普通的 BFS，目的是找到底部可插入的节点
        Deque<TreeNode> temp = new LinkedList<>();
        temp.offer(root);
        while (!temp.isEmpty()) {
            TreeNode cur = temp.poll();
            if (cur.left != null) temp.offer(cur.left);
            if (cur.right != null) temp.offer(cur.right);
            if (cur.right == null || cur.left == null) {
                // 找到完全二叉树底部可以进行插入的节点
                q.offer(cur);
            }
        }
    }

    public int insert(int val) {
        TreeNode node = new TreeNode(val);
        TreeNode cur = q.peek();
        // 进行插入
        if (cur.left == null) {
            cur.left = node;
        } else if (cur.right == null) {
            cur.right = node;
            q.poll();
        }
        // 新节点的左右节点也是可以插入的
        q.offer(node);
        return cur.val;
    }

    public TreeNode get_root() {
        return root;
    }
}
