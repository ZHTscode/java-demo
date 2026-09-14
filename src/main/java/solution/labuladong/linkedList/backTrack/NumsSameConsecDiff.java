package solution.labuladong.linkedList.backTrack;

import java.util.LinkedList;
import java.util.List;

public class NumsSameConsecDiff {
    /* 967. 连续差相同的数字 */
    List<Integer> res;
    int track; // 记录当前路径组成的数字的值
    int digit; // 记录当前数字的位数
    public int[] numsSameConsecDiff(int n, int k) {
        res = new LinkedList<>();
        track = 0;
        digit = 0;
        backtrack(n, k);
        // Java 需要把 List<Integer> 转成 int[]
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }

    void backtrack(int n, int k) {
        // base case，到达叶子节点
        if (digit == n) { // 找到一个合法的 n 位数
            res.add(track);
            return;
        }
        for (int i = 0; i <= 9; i++) {
            // 剪枝 1，第一个数字不能是 0
            if (digit == 0 && i == 0) continue;
            // 剪枝 2，相邻两个数字的差的绝对值必须等于 k
            if (digit > 0 && Math.abs(i - track % 10) != k) continue;
            // 做选择，在 track 尾部追加数字 i
            digit++;
            track = 10 * track + i;
            // 进入下一层回溯树
            backtrack(n, k);
            // 取消选择，删除 track 尾部数字
            track = track / 10;
            digit--;
        }
    }

    public static void main(String[] args) {
        NumsSameConsecDiff s = new NumsSameConsecDiff();
        int[] res = s.numsSameConsecDiff(3, 7);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}