package solution.labuladong.linkedList.binaryTree;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.LinkedList;
import java.util.Queue;

public class MinDepth {
    /* 111. 二叉树的最小深度 */
    public int minDepth(TreeNode root) { // 不能提前退出，不推荐
        if(root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);

        if(left == 0) return right + 1; // 左为空 → 只能走右边
        if(right == 0) return left + 1; // 右为空 → 只能走左边

        return Math.min(left, right) + 1; // 左右都存在，取最小
    }

    public int minDepth2(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 1;
        while(!q.isEmpty()){
            int sz = q.size();
            for(int i=0;i<sz;i++){
                TreeNode cur = q.poll();
                // 找到第一个叶子，直接返回depth
                if(cur.left == null && cur.right == null){
                    return depth;
                }
                if(cur.left != null) q.offer(cur.left);
                if(cur.right != null) q.offer(cur.right);
            }
            depth++;
        }
        return depth;
    }

    public static void main(String[] args) {
        MinDepth minDepth = new MinDepth();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{3,9,-1,7,20,-1,15});
        TreeNodeUtil.printTreeShape(root);
        System.out.println(minDepth.minDepth(root));
        System.out.println(minDepth.minDepth2(root));
    }
}
