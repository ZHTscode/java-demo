package solution;

import basic.TreeNode;

public class LowestCommonAncestor {
    /* 236. 二叉树的最近公共祖先 */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 递归终止条件：遇到空节点 或 找到p/q，直接返回当前节点
        if (root == null || root == p || root == q) return root;
        // 递归遍历左子树
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 递归遍历右子树
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        /* 核心判断逻辑 到以下步 该层函数才结束*/
        // 左子树没找到，返回右子树的结果
        if (left == null) return right;
        // 右子树没找到，返回左子树的结果
        else if (right == null) return left;
        // 左右都找到，当前节点就是最近公共祖先
        else return root;
    }
    // 优化版：找到节点后立刻停止遍历
    private static int found_count = 0;
    public static TreeNode lowestCommonAncestor_optimize(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q || found_count == 2){
            if(root == p || root == q) found_count++;
            return root;
        }
        TreeNode left = lowestCommonAncestor_optimize(root.left, p, q);
        if(found_count == 2) return left; //提前返回
        TreeNode right = lowestCommonAncestor_optimize(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    public static void main(String[] args) {
        //System.out.println("======= 测试案例1 =======");
        // 手动构造二叉树：
        //         3
        //       /   \
        //      5     1
        //     / \   / \
        //    6  2  0   8
        //      / \
        //     7   4
        TreeNode root = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node0 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);
        // 组装节点关系
        root.left = node5;
        root.right = node1;
        node5.left = node6;
        node5.right = node2;
        node1.left = node0;
        node1.right = node8;
        node2.left = node7;
        node2.right = node4;

        // 测试1：查找 5 和 1 的最近公共祖先 → 预期结果：3
        //TreeNode res1 = lowestCommonAncestor(root, node5, node1);
        //System.out.println("节点5和节点1的最近公共祖先值：" + res1.val);

        // 测试2：查找 5 和 4 的最近公共祖先 → 预期结果：5
        //TreeNode res2 = lowestCommonAncestor(root, node5, node4);
        //System.out.println("节点5和节点4的最近公共祖先值：" + res2.val);

        // 测试3：查找 6 和 7 的最近公共祖先 → 预期结果：5
        TreeNode res3 = lowestCommonAncestor_optimize(root, node5, node4);
        System.out.println("节点6和节点7的最近公共祖先值：" + res3.val);

//        // ===================== 测试案例2：边界案例-一个节点是另一个的祖先 =====================
//        System.out.println("\n======= 测试案例2 =======");
//        // 构造二叉树
//        //         1
//        //          \
//        //           2
//        //            \
//        //             3
//        TreeNode root2 = new TreeNode(1);
//        TreeNode node2_2 = new TreeNode(2);
//        TreeNode node2_3 = new TreeNode(3);
//        root2.right = node2_2;
//        node2_2.right = node2_3;
//
//        // 测试：查找 1 和 3 的最近公共祖先 → 预期结果：1
//        TreeNode res4 = lowestCommonAncestor(root2, root2, node2_3);
//        System.out.println("节点1和节点3的最近公共祖先值：" + res4.val);
//
//        // 测试：查找 2 和 3 的最近公共祖先 → 预期结果：2
//        TreeNode res5 = lowestCommonAncestor(root2, node2_2, node2_3);
//        System.out.println("节点2和节点3的最近公共祖先值：" + res5.val);
    }
}