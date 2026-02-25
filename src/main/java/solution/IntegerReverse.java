package solution;

public class IntegerReverse {
    /* 7.整数反转 */
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            // 提取最后一位数字
            int digit = x % 10;
            // 检查溢出：
            // 1. 正数溢出：res > Integer.MAX_VALUE/10 或 (res == Integer.MAX_VALUE/10 且 digit > 7)
            // 2. 负数溢出：res < Integer.MIN_VALUE/10 或 (res == Integer.MIN_VALUE/10 且 digit < -8)
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }
            // 反转累加
            res = res * 10 + digit;
            // 去掉最后一位
            x = x / 10;
        }
        return res;
    }

    public static void main(String[] args) {
        IntegerReverse integerReverse = new IntegerReverse();
        int x = 981111178;
        System.out.println(integerReverse.reverse(x));
    }
}
