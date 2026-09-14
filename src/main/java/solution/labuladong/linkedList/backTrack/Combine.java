package solution.labuladong.linkedList.backTrack;

import java.util.LinkedList;
import java.util.List;

public class Combine {
    /* 77. 组合 */
    List<List<Integer>> res;
    List<Integer> track;
    // 从 [1,2,3,...,n] 里选出 k 个数的所有组合
    public List<List<Integer>> combine(int n, int k) {
        res = new LinkedList<>();
        track = new LinkedList<>();
        backtrack(1, n, k); // 从 1 开始回溯
        return res;
    }
    private void backtrack(int start, int n, int k) {
        if(track.size() == k){
            res.add(new LinkedList<>(track));
            return;
        }
        for(int i=start; i<=n; i++){
            track.add(i);
            backtrack(i+1, n, k);
            track.remove(track.size() - 1);
        }
    }

    public static void main(String[] args) {
        Combine c = new Combine();
        System.out.println(c.combine(4, 2));
    }
}
