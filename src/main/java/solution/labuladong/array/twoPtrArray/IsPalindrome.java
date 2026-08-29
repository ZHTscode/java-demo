package solution.labuladong.array.twoPtrArray;

public class IsPalindrome {
    /* 125. 验证回文串 */
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) { // 遍历字符串中的每个字符
            if (Character.isLetterOrDigit(c)) { // 如果当前字符是字母或数字
                sb.append(Character.toLowerCase(c)); // 转换为小写并添加到StringBuilder中
            }
        }
        String filteredString = sb.toString();
        int left = 0, right = sb.length() - 1;
        while (left < right) {
            if (sb.charAt(left) != sb.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        IsPalindrome isPalindrome = new IsPalindrome();
        String s = "A man, a plan, a canal: Panama";
        boolean palindrome = isPalindrome.isPalindrome(s);
        System.out.println(palindrome);
    }
}
