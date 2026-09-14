package solution.labuladong.linkedList.backTrack;

import java.util.LinkedList;
import java.util.List;

public class LetterCombinations {
    /* 17. 电话号码的字母组合 */
    String[] mapping = new String[]{
            "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };
    List<String> res;
    StringBuilder sb;
    public List<String> letterCombinations(String digits) {
        res = new LinkedList<>();
        sb = new StringBuilder();
        if (digits == null || digits.isEmpty()) return res;
        backtrack(digits, 0);
        return res;
    }

    private void backtrack(String digits, int start) {
        if(sb.length() == digits.length()){ // 路径长度等于数字长度
            res.add(sb.toString());
            return;
        }
        int digit = digits.charAt(start) - '0'; // 获取数字对应的映射
        for(char c : mapping[digit].toCharArray()){
            // 做选择
            sb.append(c);
            // 递归下一层回溯树
            backtrack(digits, start + 1);
            // 撤销选择
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        LetterCombinations solution = new LetterCombinations();
        List<String> res = solution.letterCombinations("23");
        System.out.println(res);
    }
}
