package solution.labuladong.dataStructure.bst;

import basic.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class BSTIterator {
    /* 173. 二叉搜索树迭代器 */
    // 模拟递归栈
    private Deque<TreeNode> stk = new ArrayDeque<>();

    // 左走到底
    private void pushLeftBranch(TreeNode p) {
        while (p != null) {
            stk.push(p);
            p = p.left;
        }
    }

    public BSTIterator(TreeNode root) {
        pushLeftBranch(root);
    }

    public int next() {
        TreeNode p = stk.pop();
        pushLeftBranch(p.right);
        return p.val;
    }

    public boolean hasNext() {
        return !stk.isEmpty();
    }

    public int peek() {
            return stk.peek().val;
    }
}