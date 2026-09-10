package solution.labuladong.linkedList.bfs;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.*;

public class DistanceK {
    /* 863. 二叉树中所有距离为 K 的结点 */
    // 记录父节点：node.val -> parentNode
    // 所有节点值唯一，可以用 node.val 代表 TreeNode
    Map<Integer, TreeNode> parent = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // 遍历所有节点，记录每个节点的父节点
        traverse(root, null); // 递归遍历
        // 从 target 节点出发 BFS ，找到距离为 k 的节点
        Deque<TreeNode> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.offer(target);
        visited.add(target.val);
        int dist = 0; // 离 target 的距离
        List<Integer> res = new LinkedList<>();
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                if (dist == k) res.add(cur.val); // 找到
                // 向父节点、左右子节点扩散
                TreeNode parentNode = parent.get(cur.val);
                if (parentNode != null && !visited.contains(parentNode.val)) {
                    visited.add(parentNode.val);
                    q.offer(parentNode);
                }
                if (cur.left != null && !visited.contains(cur.left.val)) {
                    visited.add(cur.left.val);
                    q.offer(cur.left);
                }
                if (cur.right != null && !visited.contains(cur.right.val)) {
                    visited.add(cur.right.val);
                    q.offer(cur.right);
                }
            }
            dist++; // 向外扩展一圈
        }
        return res;
    }

    private void traverse(TreeNode root, TreeNode parentNode) {
        if (root == null) {
            return;
        }
        parent.put(root.val, parentNode);
        // 二叉树递归框架
        traverse(root.left, root);
        traverse(root.right, root);
    }

    public static void main(String[] args) {
        DistanceK solution = new DistanceK();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{3,5,1,6,2,0,8,-1,-1,7,4});
        TreeNode target = TreeNodeUtil.findNode(root, 5);
        System.out.println(solution.distanceK(root, target, 2));
    }
}