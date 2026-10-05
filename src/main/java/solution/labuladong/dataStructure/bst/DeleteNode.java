package solution.labuladong.dataStructure.bst;

import basic.TreeNode;

public class DeleteNode {
    /* 450. 删除二叉搜索树中的节点$$ */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) {
            // 1 是末端节点或只有一个子节点
            // if (root.left == null && root.right == null) return null;
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            // 2 有两个子节点
            TreeNode minNode = getMin(root.right); // 获得右子树最小的节点
            root.right = deleteNode(root.right, minNode.val); // 删除右子树最小的节点
            // 用右子树最小的节点替换 root 节点
            minNode.left = root.left;
            minNode.right = root.right;
            root = minNode;
        }
        else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    TreeNode getMin(TreeNode node) {
        // BST 最左边的就是最小的
        while (node.left != null){
            node = node.left; // 一路向左
        }
        return node;
    }
}