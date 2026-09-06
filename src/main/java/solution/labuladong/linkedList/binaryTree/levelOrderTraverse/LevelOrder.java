package solution.labuladong.linkedList.binaryTree.levelOrderTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LevelOrder {
    /* 102. 二叉树的层序遍历 */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if(root == null) return res;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> level = new LinkedList<>();
            for(int i=0; i<size; i++){
                TreeNode cur = queue.poll();
                level.add(cur.val);
                if(cur.left != null) queue.offer(cur.left);
                if(cur.right != null) queue.offer(cur.right);
            }
            res.add(level);
        }
        return res;
    }

    public static void main(String[] args) {
        LevelOrder solution = new LevelOrder();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{3,9,20,-1,-1,15,7});
        TreeNodeUtil.printTreeShape(root);
        List<List<Integer>> res = solution.levelOrder(root);
        System.out.println(res);
    }
}
