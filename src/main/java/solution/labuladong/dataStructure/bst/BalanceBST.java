package solution.labuladong.dataStructure.bst;

import basic.TreeNode;

import java.util.ArrayList;

public class BalanceBST {
    /* 1382. 将二叉搜索树变平衡 */
    public TreeNode balanceBST(TreeNode root) {
        // 中序遍历获取有序数组
        ArrayList<Integer> nums = traverse(root);
        // 从有序数组构建 BST
        return build(nums, 0, nums.size() - 1);
    }

    // 返回中序遍历结果
    ArrayList<Integer> traverse(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        res.addAll(traverse(root.left));
        res.add(root.val);
        res.addAll(traverse(root.right));
        return res;
    }

    // BST 构造函数，见 108. 将有序数组转换为二叉搜索树
    TreeNode build(ArrayList<Integer> nums, int lo, int hi) {
        if (lo > hi) return null;

        int mid = lo + (hi - lo) / 2;
        TreeNode root = new TreeNode(nums.get(mid));

        root.left = build(nums, lo, mid - 1);
        root.right = build(nums, mid + 1, hi);

        return root;
    }
}