package solution;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinations {
    /* 17. 电话号码的字母组合 */
    private static final String[] KEYS = { // 电话键盘映射表
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return result; // 边界条件：空字符串
        backtrack(digits, 0, new StringBuilder(), result); // 开始回溯
        return result;
    }
    private void backtrack(String digits, int index, StringBuilder path, List<String> result) {
        // 1. 递归终止条件：已处理完所有数字
        if (index == digits.length()) {
            result.add(path.toString());
            return;
        }
        // 2. 获取当前数字对应的字母
        int digit = digits.charAt(index) - '0';
        String letters = KEYS[digit];
        // 3. 遍历当前数字的所有字母
        for (char c : letters.toCharArray()) {
            path.append(c); // 做选择
            backtrack(digits, index + 1, path, result);  // 递归处理下一个数字
            path.deleteCharAt(path.length() - 1); // 撤销选择（回溯）
        }
    }

    public static void main(String[] args) {
        LetterCombinations lc = new LetterCombinations();
        String digits = "23";
        List<String> result = lc.letterCombinations(digits);
        System.out.println(result);
    }


}
