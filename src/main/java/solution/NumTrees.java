package solution;

public class NumTrees {
    /* 96. 不同的二叉搜索树
       解法一：动态规划
       BST：左子树 < 根 < 右子树
       对 n 个节点，枚举每个节点 j 作为根节点：
       左子树：1...j-1（j-1 个）
       右子树：j+1...n（n-j 个）
       组合数：左子树种类 × 右子树种类 */
    public int numTrees(int n) {
        int[] G = new int[n + 1]; // G[i]：i 个节点能组成的 BST 数量
        G[0] = 1;  // 空树算 1 种（强行定义）
        G[1] = 1;  // 单个节点只有 1 种
        // G[i] = Σ(G[j-1] × G[i-j])
        for (int i = 2; i <= n; ++i) { // i：当前总节点数
            for (int j = 1; j <= i; ++j) { // j：枚举的根节点（第 j 个节点）
                G[i] += G[j - 1] * G[i - j];
                // G[j-1] 左子树的节点数 = j-1
                // G[i-j] 右子树的节点数 = i-j
                // 总数 = (j-1) + (i-j) + 1 = i
            }
        }
        return G[n];
    }
    /* 解法二：卡特兰数（本质）
       C(n) = (2n)! / [(n+1)!n!] */
    public int numTrees2(int n) {
        long result = 1;
        for (int i = 0; i < n; i++) {
            result = result * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) result;
    }

    public static void main(String[] args) {
        NumTrees n = new NumTrees();
        System.out.println(n.numTrees(3));
        /* i = 2:
           j = 1: 根=1, 左=0个, 右=1个 → G[0]×G[1] = 1×1 = 1
           j = 2: 根=2, 左=1个, 右=0个 → G[1]×G[0] = 1×1 = 1
           G[2] = 1 + 1 = 2
           i = 3:
           j = 1: 根=1, 左=0个, 右=2个 → G[0]×G[2] = 1×2 = 2
           j = 2: 根=2, 左=1个, 右=1个 → G[1]×G[1] = 1×1 = 1
           j = 3: 根=3, 左=2个, 右=0个 → G[2]×G[0] = 2×1 = 2
           G[3] = 2 + 1 + 2 = 5 */
    }
}
