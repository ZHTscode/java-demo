package solution;

import basic.TreeNode;

//验证二叉搜索树
public class ValidBST {
    // 对外暴露的验证方法
    public static boolean isValidBST(TreeNode root) {
        // 初始范围：Long.MIN_VALUE（避免int溢出）、Long.MAX_VALUE
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 递归校验节点是否在合法范围
     * @param node 当前节点
     * @param lower 下界（当前节点必须大于此值）
     * @param upper 上界（当前节点必须小于此值）
     * @return 是否合法
     */
    //递归函数 调用自己
    private static boolean validate(TreeNode node, long lower, long upper) {
        // 空节点是合法的BST
        if (node == null) {
            return true;
        }
        // 节点值超出范围，直接返回false
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        // 递归校验左子树（上界更新为当前节点值）和右子树（下界更新为当前节点值）
        return validate(node.right, node.val, upper) && validate(node.left, lower, node.val);
    }

    // 主方法：测试入口
    public static void main(String[] args) {
        //测试用例0
        TreeNode root0 = new TreeNode(5);
        root0.left = new TreeNode(3);
        root0.right = new TreeNode(8);
        root0.left.left = new TreeNode(2);
        root0.left.right = new TreeNode(4);
        root0.right.left = new TreeNode(6);
        root0.right.right = new TreeNode(9);
        System.out.println("测试用例0（非法BST）: " + isValidBST(root0)); // 预期false

        /*// 测试用例1：合法BST [2,1,3]
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(3);
        System.out.println("测试用例1（合法BST）: " + isValidBST(root1)); // 预期true

        // 测试用例2：非法BST [5,1,4,null,null,3,6]
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        root2.right.left = new TreeNode(3);
        root2.right.right = new TreeNode(6);
        System.out.println("测试用例2（非法BST）: " + isValidBST(root2)); // 预期false

        // 测试用例3：单节点 [Integer.MAX_VALUE]
        TreeNode root3 = new TreeNode(Integer.MAX_VALUE);
        System.out.println("测试用例3（单节点）: " + isValidBST(root3)); // 预期true

        // 测试用例4：重复值 [2,2,2]
        TreeNode root4 = new TreeNode(2);
        root4.left = new TreeNode(2);
        root4.right = new TreeNode(2);
        System.out.println("测试用例4（重复值）: " + isValidBST(root4)); // 预期false*/
    }
}