package solution.labuladong.linkedList.binaryTree.recursionTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DelNodes {
    /* 1110. 删点成林 */
    Set<Integer> delSet = new HashSet<>(); // 需要删除的节点集合
    List<TreeNode> res = new LinkedList<>(); // 记录森林中每棵树的根节点

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if (root == null) return new LinkedList<>();
        for (int d : to_delete) {
            delSet.add(d);
        }
        doDelete(root, false);
        return res;
    }

    // 定义：输入一棵二叉树，删除 delSet 中的节点，返回删除完成后的根节点
    private TreeNode doDelete(TreeNode root, boolean hasParent) {
        if (root == null) return null;
        // 判断是否需要被删除
        boolean deleted = delSet.contains(root.val);
        if (!deleted && !hasParent) {
            // 没有父节点且不需要被删除，就是一个新的根节点
            res.add(root);
        }
        // 去左右子树进行删除
        root.left = doDelete(root.left, !deleted);
        root.right = doDelete(root.right, !deleted);
        // 如果需要被删除，返回 null 给父节点
        return deleted ? null : root;
    }

    public static void main(String[] args) {
        DelNodes solution = new DelNodes();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{1,2,3,4,5,6,7});
        TreeNodeUtil.printTreeShape(root);
        List<TreeNode> res = solution.delNodes(root, new int[]{3,5});
        for (TreeNode node : res) {
            TreeNodeUtil.printTreeShape(node);
        }
    }
}
