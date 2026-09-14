package solution.labuladong.linkedList.backTrack;

import java.util.LinkedList;
import java.util.List;

public class RestoreIpAddresses {
    /* 93. 复原IP地址 */
    List<String> res;
    List<String> track;
    public List<String> restoreIpAddresses(String s) {
        res = new LinkedList<>();
        track = new LinkedList<>();
        backtrack(s, 0);
        return res;
    }

    private void backtrack(String s, int start) {
        if (start == s.length() && track.size() == 4) {
            // 走到末尾，整个 s 被成功分割为合法的四部分，记下答案
            res.add(String.join(".", track));
        }
        for (int i = start; i < s.length(); i++) {
            if (!isValid(s, start, i)) { // s[start..i] 不是合法的 ip 数字，不能分割
                continue;
            }
            if (track.size() >= 4) { // 已经分解成 4 部分，不能再分解
                break;
            }
            // s[start..i] 是一个合法的 ip 数字，可以分割
            // 做选择，把 s[start..i] 放入路径列表中
            track.add(s.substring(start, i + 1));
            // 进入回溯树的下一层，继续切分 s[i+1..]
            backtrack(s, i + 1);
            // 撤销选择
            track.remove(track.size() - 1);
        }
    }

    // 判断 s[start..end] 是否是一个合法的 ip 段
    private boolean isValid(String s, int start, int end) {
        int length = end - start + 1;
        if (length == 0 || length > 3) return false;
        // 如果只有一位数字，合法
        if (length == 1) return true;
        // 多于一位数字，但开头是 0，不合法
        if (s.charAt(start) == '0') return false;
        // 开头不是 0，长度为 2，合法
        if (length <= 2) return true;
        // 3 位数判断
        if (Integer.parseInt(s.substring(start, start + length)) > 255) {
            return false;
        } else {
            return true;
        }
    }
}