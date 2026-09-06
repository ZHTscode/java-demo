package solution.labuladong.linkedList.binaryTree.levelOrderTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.Deque;
import java.util.LinkedList;

public class IsEvenOddTree {
    /* 1609. 奇偶树 */
    public boolean isEvenOddTree(TreeNode root){
        if(root == null) return true;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean even = true; // 当前是否为偶数层
        while(!queue.isEmpty()){
            int size = queue.size();
            int pre = even ? Integer.MIN_VALUE : Integer.MAX_VALUE; // 每一层开始，重置pre
            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                if(even){ // 偶数层
                    if(cur.val % 2 == 0 || cur.val <= pre) return false;
                } else{ // 奇数层
                    if(cur.val % 2 != 0 || cur.val >= pre) return false;
                }
                pre = cur.val;
                if(cur.left != null) queue.offer(cur.left);
                if(cur.right != null) queue.offer(cur.right);
            }
            even = !even; // 到下一层，交换奇偶性
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{1,10,4,3,-1,7,9,12,8,6,-1,-1,2});
        TreeNodeUtil.printTreeShape(root);
        IsEvenOddTree solution = new IsEvenOddTree();
        System.out.println(solution.isEvenOddTree(root));
    }
}
