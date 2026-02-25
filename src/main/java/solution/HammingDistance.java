package solution;

public class HammingDistance {
    /* 461.距离 */
    public int hammingDistance(int x, int y) {
        int z = x ^ y; // 异或操作，相同为 0，不同为 1
        int count = 0; // 计数器，统计不同位数的个数
        while (z != 0) {
            count++; // 每次遇到不同位数，计数器加 1
            z = z & (z - 1); // 通过将 z 与 z-1 进行与操作，可以消除 z 的最低位的 1
        }
        /*
        1100
        1011
         */
        return count;
    }

    public static void main(String[] args) {
        HammingDistance h = new HammingDistance();
        System.out.println(h.hammingDistance(1, 4));
    }
}
