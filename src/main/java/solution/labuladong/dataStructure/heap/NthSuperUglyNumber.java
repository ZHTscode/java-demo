package solution.labuladong.dataStructure.heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class NthSuperUglyNumber {
    /* 313. 超级丑数$$ */
    public int nthSuperUglyNumber(int n, int[] primes) {
        /*
          每个质数对应一条无限长、有序链表，对质数 primes[i]，链表是：
          primes[i] * ugly[1] , primes[i] * ugly[2], primes[i] * ugly[3], ...
          ugly[1] = 1（第 1 个丑数固定是 1）
          从这 n 条有序链表里面，不断拿全局最小的数，合并成一个大的有序丑数序列
          index是结果链表上的指针，标记下一次要拿 ugly[index] 和 prime 相乘，生成下一个元素
        */

        // 存储三元组 {product, prime, index}
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // 把多条链表的头结点加入优先级队列
        for (int i = 0; i < primes.length; i++) {
            pq.offer(new int[]{1, primes[i], 1});
        }
        int[] ugly = new int[n + 1]; // 结果链表
        int p = 1; // 结果链表上的指针
        while (p <= n) {
            // 取所有链表的最小结点
            int[] pair = pq.poll();
            // System.out.println(pair[0] + " " + pair[1] + " " + pair[2]);
            int product = pair[0]; // 当前结果链表末尾节点值
            int prime = pair[1]; // 计算下一个节点所需的质数
            int index = pair[2]; // 结果链表上的指针
            if (product != ugly[p - 1]) {
                ugly[p] = product; // 不重复则接到结果链表上
                p++;
            }
            // 生成下一个节点加入优先级队列
            int[] nextPair = new int[]{ugly[index] * prime, prime, index + 1};
            pq.offer(nextPair);
        }
        return ugly[n];
    }

    public static void main(String[] args) {
        NthSuperUglyNumber solution = new NthSuperUglyNumber();
        int n = 12;
        int[] primes = {2, 7, 13, 19};
        int nthSuperUglyNumber = solution.nthSuperUglyNumber(n, primes);
        System.out.println(nthSuperUglyNumber);
    }
}
