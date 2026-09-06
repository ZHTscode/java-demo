package solution.labuladong.linkedList.binaryTree.recursionTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

public class ConstructMaxBinaryTree {
    /* 654. 最大二叉树 */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums,0,nums.length-1);
    }
    private TreeNode build(int[] nums, int left, int right){
        if(left > right) return null;
        int index = -1, max = Integer.MIN_VALUE;
        for(int i=left; i<=right; i++){
            if(nums[i] > max){
                index = i;
                max = nums[i];
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = build(nums, left, index-1);
        root.right = build(nums, index+1, right);
        return root;
    }

    public static void main(String[] args) {
        ConstructMaxBinaryTree solution = new ConstructMaxBinaryTree();
        int[] nums = {3,2,1,6,0,5};
        TreeNode root = solution.constructMaximumBinaryTree(nums);
        TreeNodeUtil.printTreeShape(root);
    }
}
