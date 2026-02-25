package solution;

public class MyAtoi {
    /* 8. 字符串转换整数 (atoi) */
    public int myAtoi(String s) {
        int n = s.length();
        int i = 0;
        int sign = 1; // 符号位，默认正数
        long res = 0; // 用long接收避免溢出
        // 1. 跳过前导空格
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }
        // 2. 判断正负号（仅1次）
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = s.charAt(i) == '+' ? 1 : -1;
            i++;
        }
        // 3. 提取连续数字，同时校验溢出
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            // 提前判断：若当前res*10+digit超出int范围，直接返回边界值
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + digit;
            i++;
        }
        return (int) (sign * res);
    }

    public static void main(String[] args) {
        MyAtoi myAtoi = new MyAtoi();
        System.out.println(myAtoi.myAtoi("042aa啊1"));
    }
}
