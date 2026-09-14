package solution.labuladong.linkedList.backTrack;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    /* 22. 括号生成 */
    List<String> res; // 记录所有合法的括号组合
    StringBuilder track; // 回溯过程中的路径
    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        track = new StringBuilder();
        if (n == 0) return res;
        backtrack(n, n); // 可用的左、右括号数量初始化为 n
        return res;
    }
    // 可用的左括号数量为 left 个，可用的右括号数量为 right 个
    private void backtrack(int left, int right) {
        // 若左括号剩下的多，说明不合法
        if (right < left) return;
        // 左括号用完了，不能继续放左括号
        if (left < 0) return;
        // 所有括号都恰好用完，得到一个合法的括号组合
        if (left == 0 && right == 0) {
            res.add(track.toString());
            return;
        }
        // 尝试放一个左括号
        // 选择
        track.append('(');
        backtrack(left - 1, right);
        // 撤消选择
        track.deleteCharAt(track.length() - 1);
        // 尝试放一个右括号
        // 选择
        track.append(')');
        backtrack(left, right - 1);
        // 撤消选择
        track.deleteCharAt(track.length() - 1);
    }

    public static void main(String[] args) {
        GenerateParenthesis c = new GenerateParenthesis();
        int n = 3;
        System.out.println(c.generateParenthesis(n));
    }
}
