package solution.labuladong.linkedList.binaryTree;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.LinkedList;
import java.util.Queue;

public class MaxDepth {
    /* 104. 二叉树的最大深度 */
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    public int maxDepth2(TreeNode root) { // 不能提前退出，不推荐
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            // 把当前层所有节点出队
            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                if(cur.left != null) queue.offer(cur.left);
                if(cur.right != null) queue.offer(cur.right);
            }
            depth++; //一层处理完毕，深度加1
        }
        return depth;
    }

    public static void main(String[] args) {
        MaxDepth maxDepth = new MaxDepth();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{3,9,20,15,7});
        TreeNodeUtil.printTreeShape(root);
        System.out.println(maxDepth.maxDepth(root));
    }
}
