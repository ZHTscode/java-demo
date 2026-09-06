package solution.labuladong.linkedList.binaryTree.recursionTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.HashMap;
import java.util.Map;

public class ConstructFromPostPre {
    /* 889. 根据前序和后序遍历构造二叉树$$ */
    // 存储 postorder 中值到索引的映射
    Map<Integer, Integer> valToIndex = new HashMap<>();
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        for (int i = 0; i < postorder.length; i++) {
            valToIndex.put(postorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1);
    }

    TreeNode build(int[] preorder, int preStart, int preEnd, int[] postorder, int postStart, int postEnd) {
        if (preStart > preEnd) return null;
        // 假设当前根一定有左孩子，区间里至少有 2 个以上节点，只有 1 个节点的时直接返回
        if (preStart == preEnd) return new TreeNode(preorder[preStart]);
        int rootVal = preorder[preStart]; // 前序遍历数组的第一个元素
        int leftRootVal = preorder[preStart + 1]; // root.left 的值是前序遍历第二个元素
        int index = valToIndex.get(leftRootVal); // leftRootVal 在后序遍历数组中的索引
        int leftSize = index - postStart + 1;
        TreeNode root = new TreeNode(rootVal);
        root.left = build(preorder, preStart + 1, preStart + leftSize,
                postorder, postStart, index);
        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                postorder, index + 1, postEnd - 1);
        return root;
    }

    public static void main(String[] args) {
        ConstructFromPostPre solution = new ConstructFromPostPre();
        int[] preorder = {1,2,4,5,3,6,7};
        int[] postorder = {4,5,2,6,7,3,1};
        TreeNode root = solution.constructFromPrePost(preorder, postorder);
        TreeNodeUtil.printTreeShape(root);
    }

}

