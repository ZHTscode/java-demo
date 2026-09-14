package solution.labuladong.linkedList.backTrack;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class GrayCode {
    /* 89. 格雷编码 */
    Set<Integer> used;
    List<Integer> path;
    List<Integer> res;
    public List<Integer> grayCode(int n) {
        used = new HashSet<>();
        path = new LinkedList<>();
        res = null;
        traverse(0, n);
        return res;
    }
    private void traverse(int root, int n) {
        if (res != null) return;  // 找到一个解，立即返回
        if (path.size() == (1 << n)) { // 路径长度等于2的n次方
            res = new LinkedList<>(path); // 找到一个解
            return;
        }
        if (used.contains(root)) return;
        // 多叉树遍历的前序位置
        used.add(root);
        path.add(root);
        // 对当前数字的每位进行翻转，得到子节点
        for (int i = 0; i < n; i++) {
            int next = flipBit(root, i);
            traverse(next, n);
        }
        // 多叉树遍历的后序位置
        used.remove(root);
        path.remove(path.size() - 1);
    }
    // 把第 i 位取反（0 变 1，1 变 0）
    private int flipBit(int x, int i) {
        return x ^ (1 << i); // 异或操作，把第 i 位取反
    }

    public static void main(String[] args) {
        GrayCode solution = new GrayCode();
        List<Integer> res = solution.grayCode(3);
        System.out.println(res);
    }
}