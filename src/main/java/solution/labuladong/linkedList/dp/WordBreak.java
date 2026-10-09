package solution.labuladong.linkedList.dp;

import java.util.*;

public class WordBreak {
    /* 139. 单词拆分 */
    private List<String> wordDict;
    private boolean found;
    private List<String> track; // 记录回溯路径
    // 存储不能切分的子串（子树）避免重复计算
    private Set<String> memo;
    public boolean wordBreak(String s, List<String> wordDict) {
        this.wordDict = wordDict;
        found = false;
        track = new LinkedList<>();
        memo = new HashSet<>();
        // 执行回溯算法穷举所有可能的组合
        backtrack(s, 0);
        return found;
    }
    private void backtrack(String s, int i) { // i 是当前遍历的起始位置
        // 已经找到答案，不再递归搜索
        if (found) return;
        if (i == s.length()) {
            // 整个 s 都被匹配完成，找到一个合法答案
            found = true;
            return;
        }
        // 判断子串（子树）是否已经计算过
        String suffix = s.substring(i); // 取出后缀 s[i...]
        if (memo.contains(suffix)) {
            // 当前子串（子树）不能被切分，不用继续递归
            return;
        }
        // 回溯算法框架
        for (String word : wordDict) {
            // 看看哪个单词能够匹配 s[i..] 的前缀
            int len = word.length();
            if (i + len <= s.length() && s.substring(i, i + len).equals(word)) {
                // 找到一个单词匹配 s[i..i+len)
                track.add(word); // 做选择
                backtrack(s, i + len); // 继续匹配 s[i+len..]
                track.remove(track.size() - 1); // 撤销选择
            }
        }
        // 将不能切分的子串记录到备忘录
        if (!found) memo.add(suffix);
    }

    private Set<String> wordDict2;
    private int[] memo2;
    public boolean wordBreak2(String s, List<String> wordDict) {
        // 转化为哈希集合，快速判断元素是否存在
        this.wordDict2 = new HashSet<>(wordDict);
        // 备忘录初始化为 -1
        this.memo2 = new int[s.length()];
        Arrays.fill(memo2, -1);
        return dp(s, 0);
    }

    // 定义：s[i..] 是否能够被拼出
    boolean dp(String s, int i) {
        // base case
        if (i == s.length()) return true;
        // 防止冗余计算
        if (memo2[i] != -1) {
            return memo2[i] == 0 ? false : true;
        }
        // 遍历 s[i..] 的所有前缀
        for (int len = 1; i + len <= s.length(); len++) {
            // 看看哪些前缀存在 wordDict 中
            String prefix = s.substring(i, i + len);
            if (wordDict2.contains(prefix)) { // 找到一个单词匹配 s[i..i+len)
                // 只要 s[i+len..] 可以被拼出，s[i..] 就能被拼出
                boolean subProblem = dp(s, i + len);
                if (subProblem) {
                    memo2[i] = 1;
                    return true;
                }
            }
        }
        // s[i..] 无法被拼出
        memo2[i] = 0;
        return false;
    }

    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        System.out.println(wordBreak.wordBreak2("leetcode", Arrays.asList("leet", "code")));
    }
}