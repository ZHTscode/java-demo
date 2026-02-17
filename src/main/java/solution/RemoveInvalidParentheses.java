package solution;

import java.util.*;

public class RemoveInvalidParentheses {
    /* 解法一：BFS
     * 初始状态：原字符串 s
     * 每一层 BFS：尝试删除当前字符串中每一个括号（跳过字母）
     * 检查有效性：如果新字符串有效，加入结果集
     * 剪枝：同一层中，相同字符串只处理一次；一旦某层找到有效结果，不再继续下一层
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(s); // s入队
        visited.add(s); // s标记为已访问
        boolean found = false; // 标记是否已找到有效解
        // 开始 BFS：一个括号一个括号地删除
        while (!queue.isEmpty()) {
            String curr = queue.poll(); // 出队
            if (isValid(curr)) { // 如果当前字符串有效
                result.add(curr);
                found = true;
            }
            if (found) continue; // 跳过本层剩余的处理
            // 尝试删除 curr 中的每一个括号：生成新字符串
            for (int i = 0; i < curr.length(); i++) { // 遍历每个字符
                char c = curr.charAt(i);
                if (c != '(' && c != ')') continue; // 只处理括号
                // 删除当前字符 i ，生成新字符串（substring 包头不包尾）
                String next = curr.substring(0, i) + curr.substring(i + 1);
                if (!visited.contains(next)) { // next 未被访问过
                    visited.add(next); // 标记 next 为已访问
                    queue.offer(next); // next 入队
                }
            }
        }
        return result;
    }
    // 检查字符串是否有效
    private boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;
            if (count < 0) return false; // 右括号多于左括号
        }
        return count == 0; // 左右括号数量相等
    }

    /* 解法二：DFS + 回溯 + 剪枝
     * 统计需要删除的左右括号数量
     * 从左到右遍历字符串，尝试删除每一个括号，递归检查剩余字符串是否有效
     * 剪枝：如果剩余字符串长度小于当前结果长度，则不可能找到更优解，直接返回
     * */
    List<String> res = new ArrayList<>();
    int leftCount = 0; // 需要删除的左括号数量
    int rightCount = 0; // 需要删除的右括号数量
    public List<String> removeInvalidParentheses2(String s) {
        // 统计需要删除的左右括号数量
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') leftCount++;
            else if (s.charAt(i) == ')') {
                if (leftCount == 0) rightCount++;
                else leftCount--; // 抵消一个左括号
            }
        }
        recur(s, 0); // 从左到右遍历字符串，尝试删除每一个括号
        return res;
    }
    // last: 当前处理的字符串
    // index: 从 last 的哪个位置开始尝试删除
    public void recur(String last, int index) {
        // 剪枝：如果要删的比剩下的还多 → 不可能完成，直接返回
        if (leftCount + rightCount > last.length() - index) return;
        // 递归终止条件：已经删够了 || 遍历完字符串
        if (leftCount == 0 && rightCount == 0 || index >= last.length()) {
            if (isValid2(last)) res.add(last); // 当前字符串有效则加入结果
            return;
        }
        // 核心循环：尝试删除一个括号（从 index 开始，避免重复尝试前面的位置）
        for (int i = index; i < last.length(); i++) {
            // 去重剪枝：在连续相同的括号中，只删除第一个
            if (i != index && last.charAt(i) == last.charAt(i - 1)) continue;
            // 尝试删除左括号
            if (last.charAt(i) == '(' && leftCount > 0) {
                // 可以删除左括号，尝试删除
                leftCount--;
                String newLast = last.substring(0, i) + last.substring(i + 1);
                // 递归：继续尝试删除
                recur(newLast, i);
                // 回溯：撤销删除
                leftCount++;
            } else if (last.charAt(i) == ')' && rightCount > 0) {
                // 可以删除右括号，尝试删除
                rightCount--;
                String newLast = last.substring(0, i) + last.substring(i + 1);
                // 递归：继续尝试删除
                recur(newLast, i);
                rightCount++; // 回溯：撤销删除
            }
        }
    }
    public boolean isValid2(String s) {
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left++;
            else if (s.charAt(i) == ')') {
                left--;
                if (left < 0) break;
            }
        }
        return left == 0;
    }

    public static void main(String[] args) {
        RemoveInvalidParentheses removeInvalidParentheses = new RemoveInvalidParentheses();
        System.out.println(removeInvalidParentheses.removeInvalidParentheses("()())()"));
    }
}