package solution.labuladong.linkedList.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreakII {
    /* 140. 单词拆分 II */
    // 回溯思路
    private List<String> res;
    private List<String> track; // 记录回溯算法的路径
    private List<String> wordDict;
    public List<String> wordBreak(String s, List<String> wordDict) {
        track = new ArrayList<>();
        this.wordDict = wordDict;
        // 执行回溯算法穷举所有可能的组合
        backtrack(s, 0);
        return res;
    }
    private void backtrack(String s, int i) {
        // base case
        if (i == s.length()) {
            // 找到一个合法组合拼出整个 s，转化成字符串
            res.add(String.join(" ", track));
            return;
        }
        // 回溯算法框架
        for (String word : wordDict) {
            // 看看哪个单词能够匹配 s[i..] 的前缀
            int len = word.length();
            if (i + len <= s.length() && s.substring(i, i + len).equals(word)) {
                // 找到一个单词匹配 s[i..i+len)
                track.add(word); // 做选择
                // 进入回溯树的下一层，继续匹配 s[i+len..]
                backtrack(s, i + len);
                track.remove(track.size() - 1); // 撤销选择
            }
        }
    }

    // 动态规划思路
    private Set<String> wordDict2;
    private List<String>[] memo;
    public List<String> wordBreak2(String s, List<String> wordDict) {
        this.wordDict2 = new HashSet<>(wordDict);
        memo = new List[s.length()];
        return dp(s, 0);
    }
    // 定义：返回用 wordDict 构成 s[i..] 的所有可能
    List<String> dp(String s, int i) {
        List<String> res = new ArrayList<>();
        if (i == s.length()) {
            res.add("");
            return res;
        }
        // 防止冗余计算
        if (memo[i] != null) return memo[i];
        // 遍历 s[i..] 的所有前缀
        for (int len = 1; i + len <= s.length(); len++) {
            // 看看哪些前缀存在 wordDict 中
            String prefix = s.substring(i, i + len);
            if (wordDict2.contains(prefix)) {
                // 找到一个单词匹配 s[i..i+len)
                List<String> subProblem = dp(s, i + len);
                // 构成 s[i+len..] 的所有组合加上 prefix
                // 就是构成构成 s[i] 的所有组合
                for (String sub : subProblem) {
                    if (sub.isEmpty()) {
                        res.add(prefix); // 防止多余的空格
                    } else {
                        res.add(prefix + " " + sub);
                    }
                }
            }
        }
        memo[i] = res; // 存入备忘录
        return res;
    }

    public static void main(String[] args) {
        WordBreakII solution = new WordBreakII();
        System.out.println(solution.wordBreak2("catsanddog", List.of("cat", "cats", "and", "sand", "dog")));
    }
}