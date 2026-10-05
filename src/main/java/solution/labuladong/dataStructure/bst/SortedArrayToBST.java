package solution.labuladong.dataStructure.bst;

import basic.TreeNode;
import utils.TreeNodeUtil;

public class SortedArrayToBST {
    /* 108. 将有序数组转换为二叉搜索树 */
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    // 将闭区间 [left, right] 中的元素转化成 BST，返回根节点
    TreeNode build(int[] nums, int left, int right) {
        if (left > right) return null;
        // 构造根节点
        // BST 节点左小右大，中间的元素就是根节点
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        // 递归构建左子树
        root.left = build(nums, left, mid - 1);
        // 递归构造右子树
        root.right = build(nums, mid + 1, right);
        return root;
    }

    public static void main(String[] args) {
        SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
        int[] nums = {1,2,3,4,5,6,7,8,9};
        TreeNode root = sortedArrayToBST.sortedArrayToBST(nums);
        TreeNodeUtil.printTreeShape(root);
    }
}