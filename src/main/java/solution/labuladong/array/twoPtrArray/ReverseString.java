package solution.labuladong.array.twoPtrArray;

import java.util.Arrays;

public class ReverseString {
    /* 344. 反转字符串 */
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        ReverseString solution = new ReverseString();
        char[] s = {'h','e','l','l','o'};
        solution.reverseString(s);
        System.out.println(Arrays.toString(s));
    }
}
