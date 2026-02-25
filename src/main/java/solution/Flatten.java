package solution;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.Arrays;

public class Flatten {
    /* 114. 二叉树展开为链表 */
    /* 核心：反向后序遍历（右→左→根）
       前序遍历 从前向后（需要保存左子树）❌
       反向后序 从后向前（只需保存前一个节点）✅
       从最后一个节点开始往前构建链表
       用 pre 保存已经处理好的链表头
       每个节点的 right 指向 pre
       更新 pre = 当前节点 */
    private TreeNode pre = null; // 保存已处理好的链表头
    public void flatten(TreeNode root) {
        if (root == null)       return;
        flatten(root.right);  // 1. 先处理右子树
        flatten(root.left);   // 2. 再处理左子树
        root.right = pre;     // 3. 当前节点的 right 指向已处理好的链表
        root.left = null;     // 4. left 置空（展开为链表）
        pre = root;           // 5. 更新 pre 为当前节点（成为新的链表头）
        TreeNodeUtil.printTreeShape(root);
    }

    public static void main(String[] args) {
        Flatten flatten = new Flatten();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{1,2,5,3,4,-1,6});
        TreeNodeUtil.printTreeShape(root);
        flatten.flatten(root);
        TreeNodeUtil.printTreeShape(root);
    }
}
