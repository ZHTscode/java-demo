package solution.labuladong.linkedList.binaryTree.recursionTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.HashSet;
import java.util.Set;

public class LowestCommonAncestorIV {
    /* 1676. 二叉树的最近公共祖先 IV */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        Set<Integer> set = new HashSet<>();
        for(TreeNode node : nodes){
            set.add(node.val);
        }
        return lca(root, set);
    }
    private TreeNode lca(TreeNode root, Set<Integer> set){
        // 遇到一个就返回，因为祖先只可能比它位置更浅
        if(root == null || set.contains(root.val)) return root;
        TreeNode left = lca(root.left, set);
        TreeNode right = lca(root.right, set);
        if(left == null) return right;
        if(right == null) return left;
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{3,5,1,6,2,0,8,-1,-1,7,4});
        TreeNodeUtil.printTreeShape(root);
        TreeNode[] nodes = {
                TreeNodeUtil.findNode(root, 5),
                TreeNodeUtil.findNode(root, 1),
                TreeNodeUtil.findNode(root, 4)
        };
        LowestCommonAncestorIV lca = new LowestCommonAncestorIV();
        TreeNode res = lca.lowestCommonAncestor(root, nodes);
        System.out.println(res.val);
    }
}
