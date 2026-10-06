package solution.labuladong.math;

public class SuperPow {
    /* 372. 超级次方 */
    private int base = 1337;

    // 计算 a 的 k 次方然后与 base 求模的结果
    private int mypow(int a, int k) {
        // 对因子求模
        a %= base;
        int res = 1;
        for (int i = 0; i < k; i++) {
            // 这里有乘法，是潜在的溢出点
            res *= a;
            // 对乘法结果求模
            res %= base;
        }
        return res;
    }

    public int superPow(int a, int[] b) {
        if (b.length == 0) return 1;
        int last = b[b.length - 1];
        int[] newB = new int[b.length - 1];
        System.arraycopy(b, 0, newB, 0, b.length - 1);

        int part1 = mypow(a, last);
        int part2 = mypow(superPow(a, newB), 10);
        // 每次乘法都要求模
        return (part1 * part2) % base;
    }
}
