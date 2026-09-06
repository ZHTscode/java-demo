package solution.labuladong.linkedList.binaryTree.recursionTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.HashMap;
import java.util.Map;

public class ConstructFromPostMid {
    /* 106. 从中序与后序遍历序列构造二叉树$$ */
    Map<Integer, Integer> valToIndex = new HashMap<>();
    TreeNode buildTree(int[] inorder, int[] postorder) {
        for(int i=0; i< inorder.length; i++){
            valToIndex.put(inorder[i], i);
        }
        return build(postorder, 0, postorder.length-1, inorder, 0, inorder.length-1);
    }
    private TreeNode build(int[] postorder, int pl, int pr, int[] inorder, int il, int ir){
        if(pl > pr) return null;
        int rootVal = postorder[pr];
        int rootIndexInorder = valToIndex.get(rootVal);
        int rightLen = ir - rootIndexInorder;
        TreeNode root = new TreeNode(rootVal);
        root.left = build(postorder, pl, pr-rightLen-1,
                inorder, il, rootIndexInorder-1);
        root.right = build(postorder, pr-rightLen, pr-1,
                inorder, rootIndexInorder+1, ir);
        return root;
    }

    public static void main(String[] args) {
        ConstructFromPostMid solution = new ConstructFromPostMid();
        int[] postorder = {9,15,7,20,3};
        int[] inorder = {9,3,15,20,7};
        TreeNode root = solution.buildTree(inorder, postorder);
        TreeNodeUtil.printTreeShape(root);
    }
}
