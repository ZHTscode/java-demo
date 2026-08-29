package solution.labuladong.array.hash;

public class FindTheDifference {
    public char findTheDifference(String s, String t) {
        int res = 0;
        for (char c : s.toCharArray()) {
            res = res ^ c; // 异或运算
        }
        for (char d : t.toCharArray()) {
            res = res ^ d; // 异或运算
        }
        // 根据异或运算规则，所有字符的异或结果就是多出来的那个字符
        return (char) res;
    }

    public char findTheDifference2(String s, String t) {
        int[] count1 = countChar(s);
        int[] count2 = countChar(t);
        for (int i = 0; i < count1.length; i++) {
            if (count1[i] != count2[i]) {
                return (char) (i + 'a');
            }
        }
        return ' ';
    }
    // 计算字符的出现次数
    int[] countChar(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            // 把小写字母映射到 0~25 的区间
            int delta = c - 'a';
            count[delta]++;
        }
        return count;
    }

    public static void main(String[] args) {
        FindTheDifference solution = new FindTheDifference();
        String s = "abcd";
        String t = "abcde";
        System.out.println(solution.findTheDifference(s, t));
    }
}
