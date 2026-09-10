package solution.labuladong.linkedList.binaryTree.recursionTraverse;

import basic.TreeNode;
import utils.TreeNodeUtil;

import java.util.LinkedList;
import java.util.List;

public class AllPossibleFBT {
    /* 894. 所有可能的满二叉树 */
    // 备忘录，记录 n 个节点能够组合成的所有可能二叉树
    List<TreeNode>[] memo;
    public List<TreeNode> allPossibleFBT(int n) {
        if (n % 2 == 0) {
            // 真二叉树不可能是偶数个节点，返回空列表
            return new LinkedList<>();
        }
        memo = new LinkedList[n + 1];
        return build(n);
    }

    // 定义：生成节点数为 n 的所有可能的真二叉树
    public List<TreeNode> build(int n) {
        List<TreeNode> res = new LinkedList<>();
        // base case
        if (n == 1) {
            res.add(new TreeNode(0));
            return res;
        }
        if (memo[n] != null) return memo[n]; // 避免冗余计算

        for (int i = 1; i < n; i += 2) { // i 是左子树节点数量，必须是奇数
            int j = n - i - 1; // j 是右子树节点数量
            // 递归拿到所有i个节点的左子树、j个节点的右子树
            List<TreeNode> leftSubTrees = build(i);
            List<TreeNode> rightSubTrees = build(j);
            // 双重循环：左子树所有形状 × 右子树所有形状，组合
            for (TreeNode left : leftSubTrees) {
                for (TreeNode right : rightSubTrees) {
                    // 生成根节点
                    TreeNode root = new TreeNode(0);
                    // 组装出一种可能的二叉树形状
                    root.left = left;
                    root.right = right;
                    // 加入结果列表
                    res.add(root);
                }
            }
        }
        // 存入备忘录
        memo[n] = res;
        return res;
    }

    public static void main(String[] args) {
        AllPossibleFBT solution = new AllPossibleFBT();
        List<TreeNode> res = solution.allPossibleFBT(7);
        for(TreeNode root : res)
            TreeNodeUtil.printTreeShape(root);
    }
}
