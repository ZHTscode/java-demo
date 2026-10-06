package solution.labuladong.math;

public class TrailingZeroes {
    /* 172. 阶乘后的零 */
    public int trailingZeroes(int n) {
        int res = 0;
        long divisor = 5;
        while (divisor <= n) {
            res += n / divisor;
            divisor *= 5;
        }
        return res;
    }
    public static void main(String[] args) {
        TrailingZeroes solution = new TrailingZeroes();
        int n = 10;
        int res = solution.trailingZeroes(n);
        System.out.println(res);
    }
}

