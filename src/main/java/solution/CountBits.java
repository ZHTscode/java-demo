package solution;

public class CountBits {
    // 解法一：最直接的解法，一个一个数的计算
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            result[i] = count(i);
        }
        return result;
    }
    private int count(int x) {
        int count = 0;
        while (x != 0) {
            count += x & 1; // 判断x的最低位是否为1
            x >>>= 1; // 右移一位
        }
        return count;
    }
    // 解法二：动态规划
    /*
    1.确定dp数组以及下标的含义：dp[i]，下标i对应的数字的二进制中1的个数
    2.确定递推公式：没有用递推公式，但是有一个规律：
        a.每个奇数对应二进制中1的个数是它前一个偶数的个数 + 1
        b.每个偶数对应二进制中1的个数是它除以2的偶数的二进制中1的个数，即向右移一位，而末尾是0，所以不影响
    3.dp数组初始化：dp[0] = 0, dp[1] = 1
    4.确定遍历顺序：从前往后遍历
    5.举例推到dp数组（报错的时候）
    */
    public int[] countBits2(int n) {
        // 如果n为0，那么dp数组的长度为1，下面初始化dp[1]会越界
        if (n < 1) return new int[1];
        // 因为从0开始，所以要得到dp[5]，长度要加1
        int[] dp = new int[n + 1];
        dp[0] = 0; // 0的二进制中1的个数为0
        dp[1] = 1; // 1的二进制中1的个数为1
        for (int i = 2; i < dp.length; i++){
            if (i % 2 == 0)
                dp[i] = dp[i / 2];
            else
                dp[i] = dp[i - 1] + 1;
        }
        return dp;
    }

    public static void main(String[] args) {
        CountBits cb = new CountBits();
        int[] result = cb.countBits2(5);
        for (int x : result) {
            System.out.print(x + " ");
        }
    }
}
