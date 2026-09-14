package solution.labuladong.linkedList.backTrack;

import java.util.LinkedList;
import java.util.List;

public class CountArrangement {
    /* 526. 优美的排列 */
    int res;
    List<Integer> track;
    boolean[] used;
    public int countArrangement(int n) {
        res = 0;
        track = new LinkedList<>();
        used = new boolean[n + 1];
        backtrack(n, 1);
        return res;
    }

    void backtrack(int n, int index) {
        // base case，到达叶子节点
        if (index > n) { // 找到一个结果
            res += 1;
            return;
        }
        // 索引 index 开始选择元素
        for (int elem = 1; elem <= n; elem++) {
            if (used[elem]) continue; // 不能重复选择
            if (!(index % elem == 0 || elem % index == 0)) { // 不优美
                continue;
            }
            // 做选择，index 选择元素 elem
            used[elem] = true;
            track.add(elem);
            // 进入下一层回溯树
            backtrack(n, index + 1);
            // 取消选择
            track.remove(track.size() - 1);
            used[elem] = false;
        }
    }

    public static void main(String[] args){
        CountArrangement s = new CountArrangement();
        int res = s.countArrangement(3);
        System.out.println(res);
    }
}
