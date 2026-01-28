package test;

import basic.TreeNode;
import utils.TreeNodeUtil;

/**
 * 二叉树工具类测试类 - 所有方法测试验证，直接运行即可
 */
public class TestTreeNodeUtil {
    public static void main(String[] args) {
        // ========== 测试1：构建二叉树 + 打印所有遍历 ==========
        System.out.println("======= 测试1：构建二叉树 & 遍历 =======");
        Integer[] arr = {1,2,3,-1,4,5,6}; // -1代表null节点
        TreeNode root = TreeNodeUtil.buildTree(arr);
        TreeNodeUtil.printTreeTraversal(root);
        //TreeNodeUtil.printTreeShape(root);

        // ========== 测试2：获取二叉树属性 ==========
        System.out.println("\n======= 测试2：二叉树属性获取 =======");
        System.out.println("二叉树高度：" + TreeNodeUtil.getHeight(root));
        System.out.println("总节点数：" + TreeNodeUtil.getNodeCount(root));
        System.out.println("叶子节点数：" + TreeNodeUtil.getLeafCount(root));

        // ========== 测试3：查找节点 & 获取深度 ==========
        System.out.println("\n======= 测试3：节点查找 =======");
        TreeNode target = TreeNodeUtil.findNode(root, 4);
        System.out.println("查找值为4的节点：" + (target == null ? "无" : "找到，值=" + target.val));
        System.out.println("值为4的节点深度：" + TreeNodeUtil.getDepth(root, target));

        // ========== 测试4：二叉树判断（平衡、对称、完全） ==========
        System.out.println("\n======= 测试4：二叉树判断 =======");
        System.out.println("是否是平衡二叉树：" + TreeNodeUtil.isBalanced(root));
        // 构建对称二叉树 [1,2,2,3,4,4,3]
        TreeNode symmetricRoot = TreeNodeUtil.buildTree(new Integer[]{1,2,2,3,4,4,3});
        System.out.println("对称二叉树是否对称：" + TreeNodeUtil.isSymmetric(symmetricRoot));
        System.out.println("普通二叉树是否对称：" + TreeNodeUtil.isSymmetric(root));
        System.out.println("是否是完全二叉树：" + TreeNodeUtil.isCompleteTree(root));

        // ========== 测试5：边界值测试（空树、单节点树） ==========
        System.out.println("\n======= 测试5：边界值测试 =======");
        TreeNode emptyTree = null;
        TreeNode singleNode = new TreeNode(5);
        System.out.println("空树是否为空：" + TreeNodeUtil.isEmpty(emptyTree));
        System.out.println("单节点树高度：" + TreeNodeUtil.getHeight(singleNode));
        System.out.println("单节点树是否平衡：" + TreeNodeUtil.isBalanced(singleNode));
    }
}