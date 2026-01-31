package utils;

import basic.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 2026/01/18
 * 二叉树工具类
 * 包含：二叉树构建、前中后序遍历(递归+迭代)、层序遍历、高度/深度、判空/判平衡/判对称、找节点、打印树结构
 * 所有方法均为 静态方法，直接 TreeNodeUtil.方法名() 调用即可
 */
public class TreeNodeUtil {
    // 1.基础判空
    // 判断二叉树节点是否为空 (null)
    public static boolean isNull(TreeNode node) {
        return node == null;
    }
    // 判断二叉树是否为空树
    public static boolean isEmpty(TreeNode root) {
        return root == null;
    }

    // 2.根据数组构建二叉树
    // 根据层序数组构建二叉树（null用-1占位，例如 [1,2,3,-1,4]）
    public static TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return null;
        }
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        TreeNode root = new TreeNode(arr[0]); // 创建根节点
        queue.offer(root); // 根节点入队
        int index = 1; // 从第二个元素开始
        // 队列非空且索引在数组范围内（队空：上层节点都没有子节点）
        while (!queue.isEmpty() && index < arr.length) {
            TreeNode cur = queue.poll(); // 队首元素出队
            // 构建左节点
            if (arr[index] != -1) {
                cur.left = new TreeNode(arr[index]); // 先分配值，后入队
                queue.offer(cur.left); // 左节点入队，等待分配子节点
            }
            index++;
            // 构建右节点
            if (index < arr.length && arr[index] != -1) {
                cur.right = new TreeNode(arr[index]); // 先分配值，后入队
                queue.offer(cur.right); // 右节点入队，等待分配子节点
            }
            index++;
            // 一个循环 index 自增2
        }
        return root;
    }

    // 3.二叉树三大遍历
    // 前序遍历（根 -> 左 -> 右） 递归版
    public static void preOrder(TreeNode root) {
        if (isNull(root)) return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }
    //================================
    // 非递归版前序遍历（手动栈实现DFS）
    List<Integer> preOrderIter2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            // 1. 访问当前节点（前序：先访问）
            res.add(curr.val);
            // 2. 栈是后进先出，先压右子树，再压左子树（保证左子树先遍历）
            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
        }
        return res;
    }
    //================================
    // 中序遍历（左 -> 根 -> 右） 递归版
    public static void inOrder(TreeNode root) {
        if (isNull(root)) return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }
    // 后序遍历（左 -> 右 -> 根） 递归版
    public static void postOrder(TreeNode root) {
        if (isNull(root)) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    // 4.层序遍历（广度优先BFS，含打印）
    // 层序遍历（按层打印，一行一层）
    // 相当于层序数组构建二叉树反过来
    public static void levelOrder(TreeNode root) {
        if (isEmpty(root)) {
            System.out.println("[]");
            return;
        }
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll(); // 队首元素出队
                System.out.print(cur.val + " "); // 打印队首元素
                if (!isNull(cur.left)) queue.offer(cur.left); // 左节点入队，等待打印
                if (!isNull(cur.right)) queue.offer(cur.right); // 右节点入队，等待打印
                // 顺序：根 -> 左 -> 右
            }
            System.out.println(); // 每层打印完换行
        }
    }

    // 5.求二叉树高度、深度
    // 获取二叉树的高度（从根节点到最远叶子节点的边数/节点数）
    // 空树高度=0，单节点树高度=1
    public static int getHeight(TreeNode root) {
        if (isNull(root)) return 0;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
    // 获取指定节点的深度（从根节点到该节点的节点数）
    public static int getDepth(TreeNode root, TreeNode target) {
        if (isNull(root) || isNull(target)) return 0;
        if (root == target) return 1;
        int leftDepth = getDepth(root.left, target);
        int rightDepth = getDepth(root.right, target);
        return leftDepth > 0 ? leftDepth + 1 : rightDepth > 0 ? rightDepth + 1 : 0;
        // : 表示三元运算符，即 if-else 的简写形式
    }

    // 6.二叉树节点查找 & 计数
    // 根据值查找节点（找到第一个匹配的节点）
    public static TreeNode findNode(TreeNode root, int val) {
        if (isNull(root)) return null;
        if (root.val == val) return root;
        TreeNode leftNode = findNode(root.left, val);
        return leftNode != null ? leftNode : findNode(root.right, val);
    }
    // 获取二叉树的节点总数
    public static int getNodeCount(TreeNode root) {
        if (isNull(root)) return 0;
        return getNodeCount(root.left) + getNodeCount(root.right) + 1;
    }
    // 获取二叉树的叶子节点总数
    public static int getLeafCount(TreeNode root) {
        if (isNull(root)) return 0;
        if (isNull(root.left) && isNull(root.right)) return 1;
        return getLeafCount(root.left) + getLeafCount(root.right);
    }

    // 7. 打印二叉树
    public static void printTreeTraversal(TreeNode root) {
        if (isEmpty(root)) {
            System.out.println("二叉树为空！");
            return;
        }
        System.out.print("前序遍历：");
        preOrder(root);
        System.out.println();
        System.out.print("中序遍历：");
        inOrder(root);
        System.out.println();
        System.out.print("后序遍历：");
        postOrder(root);
        System.out.println();
        System.out.println("层序遍历（按层）：");
        levelOrder(root);
    }
    // 打印二叉树的形状
    public static void printTreeShape(TreeNode root) {
        if (isEmpty(root)) {
            System.out.println("空二叉树");
            return;
        }
        // 获取二叉树的高度
        int treeHeight = getHeight(root);
        // 二维数组存储树形结构，元素为节点值/空节点占位符
        List<List<String>> treeMatrix = new ArrayList<>();
        // 初始化二维数组，行数=树高，列数=2^树高 -1 (满二叉树的宽度)
        int col = (1 << treeHeight) - 1;
        for (int i = 0; i < treeHeight; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < col; j++) {
                row.add(" ");
            }
            treeMatrix.add(row);
        }
        // 填充二维数组：递归给每个节点赋值，确定坐标
        fillTreeMatrix(root, treeMatrix, 0, 0, col - 1);
        // 打印构建好的树形结构
        System.out.println("========= 二叉树可视化形状 =========");
        for (List<String> row : treeMatrix) {
            StringBuilder sb = new StringBuilder();
            for (String s : row) {
                sb.append(s);
            }
            System.out.println(sb);
        }
        System.out.println("====================================");
    }
    // 递归填充树形二维数组的辅助方法
    private static void fillTreeMatrix(TreeNode node, List<List<String>> matrix, int row, int left, int right) {
        if (isNull(node)) return;
        // 当前节点放在当前行的中间位置
        int mid = left + (right - left) / 2;
        matrix.get(row).set(mid, node.val + "");
        // 递归填充左子树
        fillTreeMatrix(node.left, matrix, row + 1, left, mid - 1);
        // 递归填充右子树
        fillTreeMatrix(node.right, matrix, row + 1, mid + 1, right);
    }
    // 8. 其他常用判断
    // 判断是否为平衡二叉树（任意节点的左右子树高度差的绝对值 ≤ 1）
    public static boolean isBalanced(TreeNode root) {
        if (isEmpty(root)) return true;
        int leftH = getHeight(root.left);
        int rightH = getHeight(root.right);
        if (Math.abs(leftH - rightH) > 1) return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }
    // 判断是否为对称二叉树（镜像对称）
    public static boolean isSymmetric(TreeNode root) {
        if (isEmpty(root)) return true;
        return checkSymmetric(root.left, root.right);
    }
    // 对称判断辅助方法：判断两个子树是否对称
    private static boolean checkSymmetric(TreeNode left, TreeNode right) {
        if (isNull(left) && isNull(right)) return true;
        if (isNull(left) || isNull(right)) return false;
        return left.val == right.val
                && checkSymmetric(left.left, right.right)
                && checkSymmetric(left.right, right.left);
    }
    // 判断二叉树是否为完全二叉树
    public static boolean isCompleteTree(TreeNode root) {
        if (isEmpty(root)) return true;
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);
        boolean flag = false;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (flag && !isNull(cur)) return false;
            if (isNull(cur)) {
                flag = true;
                continue;
            }
            queue.offer(cur.left);
            queue.offer(cur.right);
        }
        return true;
    }
}