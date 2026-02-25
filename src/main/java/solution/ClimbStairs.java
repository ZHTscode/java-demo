package solution;

public class ClimbStairs {
    /* 70. 爬楼梯
       核心：动态规划 */
    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int[] dp = new int[n + 1]; // 定义(i)：爬到第 i 阶楼梯的方法数
        dp[1] = 1; // 初始化：爬到第 1 阶楼梯的方法数为 1
        dp[2] = 2; // 初始化：爬到第 2 阶楼梯的方法数为 2
        for (int i = 3; i <= n; i++) {
            /* 爬到第 i 阶楼梯的方法数 =
               爬到第 i-1 阶楼梯的方法数（再爬 1 阶） + 爬到第 i-2 阶楼梯的方法数（再爬 2 阶）*/
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
    // 优化空间复杂度
    public int climbStairs2(int n) {
        int ppre;
        int pre = 1;
        int cur = 2;
        if(n <= 2)
            return n;
        for(int i = 3; i <= n; i++) {
            ppre = pre;
            pre = cur;
            cur = ppre + pre;
        }
        return cur;

    }

    public static void main(String[] args) {
        ClimbStairs c = new ClimbStairs();
        System.out.println(c.climbStairs(3));
    }
}
