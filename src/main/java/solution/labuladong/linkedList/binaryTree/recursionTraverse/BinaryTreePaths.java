package solution.labuladong.linkedList.binaryTree.recursionTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePaths {
    /* 257. 二叉树的所有路径$$ */
    List<String> path = new ArrayList<>();
    List<String> res = new LinkedList<>();
    public List<String> binaryTreePaths(TreeNode root){
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root){
        if(root == null) return;
        // 前序遍历位置
        path.add(root.val + ""); // 数字转字符串，加入路径

        if(root.right == null && root.left == null){
            res.add(String.join("->", path)); // 用 "->" 将路径加入结果
        }

        // 递归遍历左右子树
        traverse(root.left);
        traverse(root.right);
        // 后序遍历位置
        path.remove(path.size() - 1); // 回溯撤销，回到上一层状态
    }

    public static void main(String[] args) {
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{1,2,3,-1,5});
        BinaryTreePaths binaryTreePaths = new BinaryTreePaths();
        System.out.println(binaryTreePaths.binaryTreePaths(root));
    }

}
