package solution;

import basic.TreeNode;
import utils.TreeNodeUtil;

public class DiameterOfBinaryTree {
    // 核心：二叉树的直径 = 所有节点中（左子树深度 + 右子树深度）的最大值
    // 方法一：利用数组引用传递（Java 无指针但数组是引用）来修改全局变量
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int[] diameter = new int[1];
        height(root, diameter);
        return diameter[0];
    }
    private int height(TreeNode node, int[] diameter) {
        if (node == null) return 0;
        int leftHeight = height(node.left, diameter); // 左子树深度
        int rightHeight = height(node.right, diameter); // 右子树深度
        diameter[0] = Math.max(diameter[0], leftHeight + rightHeight);
        return 1 + Math.max(leftHeight, rightHeight);
    }
    // 方法二：直接修改类的状态来修改全局变量
    private int ans;
    public int diameterOfBinaryTree2(TreeNode root) {
        dfs(root);
        return ans;
    }
    public int dfs(TreeNode root){
        if(root == null) return -1;
        //左右子树链长
        int lLen = dfs(root.left) + 1;
        int rLen = dfs(root.right) + 1;
        ans = Math.max(ans,lLen+rLen);
        return Math.max(lLen,rLen);
    }

    public static void main(String[] args) {
        DiameterOfBinaryTree solution = new DiameterOfBinaryTree();
        TreeNode root = TreeNodeUtil.buildTree(new Integer[]{1,2,3,4,5});
        int diameter = solution.diameterOfBinaryTree(root);
        System.out.println(diameter); // 输出 3
    }
}
