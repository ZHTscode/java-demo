package solution.labuladong.array.twoPtrArray;

public class LongestCommonPrefix {
    /* 14. 最长公共前缀 */
    public String longestCommonPrefix(String[] strs) {
        int m = strs.length;
        for (int col = 0; col < strs[0].length(); col++) { // strs[0]为空串直接返回
            char c = strs[0].charAt(col);
            for (int row = 1; row < m; row++) { // 固定列，从第二行开始，每一行都跟第一个字符串比较
                // 关键：如果当前字符串长度已经等于col，说明已经到头，不能再取charAt(col)
                if (col >= strs[row].length() || strs[row].charAt(col) != c) {
                    return strs[0].substring(0, col);
                }
            }
        }
        return strs[0];
    }

    public static void main(String[] args) {
        LongestCommonPrefix s = new LongestCommonPrefix();
        String[] strs = {"flower","flow","flight"};
        String res = s.longestCommonPrefix(strs);
        System.out.println(res);
    }
}
