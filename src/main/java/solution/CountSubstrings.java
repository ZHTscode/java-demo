package solution;

public class CountSubstrings {
    // 方法一：暴力法
    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isPalindrome(s.substring(i, j + 1))) {
                    // 从i到j的子串是回文串，i是起始索引（包含），j+1是结束索引（不包含）
                    count++;
                }
            }
        }
        return count;
    }
    private boolean isPalindrome(String s) {
        // 使用双指针判断子串是否为回文串
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // s.charAt()表示获取字符串中指定索引的字符
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    // 方法二：动态规划状态方程
    public int countSubstrings2(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        // dp[i][j]表示索引从i到j的子串是否为回文串
        int res = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i >= 2)
                        // 外层是否为回文串=内层是否为回文串
                        dp[i][j] = dp[i + 1][j - 1];
                    else
                        // 长度为1子串是回文串，两端相等、长度为2的子串是回文串
                        dp[i][j] = true;
                    if (dp[i][j])
                        res++;
                }
            }
        }
        return res;
    }
    // 方法三：中心扩散
    public int countSubstrings3(String s) {
        int cnt = 0;
        char[] str = s.toCharArray(); // 转为字符数组
        for(int i = 0; i < str.length; i++) {
            cnt += expandAroundCenter(str, i, i); // 奇数长度的回文串
            cnt += expandAroundCenter(str, i, i + 1); // 偶数长度的回文串
        }
        return cnt;
    }
    private int expandAroundCenter(char[] str, int left, int right) {
        // 以left和right为中心，向两边扩散，统计回文串的个数
        int cnt = 0;
        while(left >= 0 && right < str.length && str[left] == str[right]) {
            cnt++;
            left--;
            right++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        CountSubstrings solution = new CountSubstrings();
        String s = "aaabb";
        System.out.println(solution.countSubstrings2(s));
    }

}
