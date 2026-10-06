package solution.labuladong.math;

import java.util.Arrays;

public class CountPrimes {
    /* 204. 计数质数 */
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true); // 初始化，认为所有数都是质数
        for (int i = 2; i * i < n; i++) { // 只需要遍历到 sqrt(n)
            if (isPrime[i]) { // 如果是质数，则将其倍数全部标记为非质数
                // 从 i*i 开始，因为 i*i 之前已经判断过
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) count++; // 统计质数个数
        }
        return count;
    }

    public static void main(String[] args) {
        CountPrimes solution = new CountPrimes();
        int n = 10;
        int res = solution.countPrimes(n);
        System.out.println(res);
    }
}