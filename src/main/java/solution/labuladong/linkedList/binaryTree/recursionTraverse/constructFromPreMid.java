package solution.labuladong.linkedList.binaryTree.recursionTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.HashMap;
import java.util.Map;

public class constructFromPreMid {
    /* 105. 从前序与中序遍历序列构造二叉树$$ */
    Map<Integer, Integer> valToIndex = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for(int i=0; i< inorder.length; i++){
            valToIndex.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }
    private TreeNode build(int[] preorder, int pl, int pr, int[] inorder, int il, int ir){
        if(pl > pr) return null;
        int rootVal = preorder[pl];
        int rootIndexInorder = valToIndex.get(rootVal);
        int leftLen = rootIndexInorder - il;
        TreeNode root = new TreeNode(rootVal);
        root.left = build(preorder, pl+1, pl+leftLen,
                inorder, il, rootIndexInorder-1);
        root.right = build(preorder, pl+leftLen+1, pr,
                inorder, rootIndexInorder+1, ir);
        return root;
    }

    public static void main(String[] args) {
        constructFromPreMid solution = new constructFromPreMid();
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode root = solution.buildTree(preorder, inorder);
        TreeNodeUtil.printTreeShape(root);
    }
}