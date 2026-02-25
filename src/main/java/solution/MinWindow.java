package solution;

import java.util.Arrays;

public class MinWindow {
    /* 76. 最小覆盖子串
       用「diff数组」 和「less变量」巧妙判断「当前窗口是否包含了 t 的所有字符」，不需要每次遍历整个数组
       「diff数组」：记录每个字符的需求数
         初始状态（统计 t）：
         diff[c] > 0：表示还需要 c 字符个数
         diff[c] = 0：表示 c 字符刚好满足（或多余）
         diff[c] < 0：表示 c 字符多余了（负数绝对值是多余的数量）
         diff[c] = (t 中 c 的数量) − (当前窗口中 c 的数量)
         如果 c 在 t 中：
          初始 diff[c] > 0（缺）
          窗口加入 c → diff[c] 减小
          窗口移除 c → diff[c] 增大（需求回升）。
         如果 c 不在 t 中：
          初始 diff['D'] = 0
          窗口加入 'D' → diff['D'] 变为 -1（表示多了 1 个没用的 'D'）
          窗口移除 'D' → 必须执行 diff['D']++，让它从 -1 变回 0
       「less 变量」：还有多少种字符的需求没有被满足（即 diff[c] > 0 的字符种类数）
         less = 0 时，所有字符都满足要求，当前窗口是一个可行解
         每次移动指针只改变一个字符的状态，只需更新 less，实现 O(1) 判断
     */
    public String minWindow(String s, String t) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int m = sc.length;
        // 1. 初始化 diff 和 less
        int[] diff = new int[128]; // ASCII 码范围
        int less = 0;
        for (char c : tc) {
            if (diff[c] == 0)
                less++; // 如果是第一次遇到该字符，未满足种类 +1
            diff[c]++; // 该字符需求量 +1
        }
        System.out.println(Arrays.toString(diff));
        // 2. 滑动窗口
        int ansL = -1, ansR = m; // 初始化最小窗口的左右边界
        int l = 0, r = 0; // 左右指针
        while (r < m) {
            // 右指针扩张：纳入 s[r]
            if (--diff[sc[r]] == 0) // diff[c] 减 1（需求减少）
                /* diff[c] = 0，说明该字符刚好满足 → less-1
                   diff[c] > 0，说明还不够 → less 不变
                   diff[c] < 0，说明多了 → less 不变（因为之前已经是 0 了）
                   如果移入的是非需求字符，diff[c] 必为负！ */
                less--;
            // 左指针收缩：缩小窗口
            while (less == 0) { // 只要 less==0，当前窗口 [l, r] 就是合法的
                // 更新最小窗口
                if (r - l < ansR - ansL) {
                    ansR = r;
                    ansL = l;
                }
                // 准备移除 s[l]
                if (diff[sc[l]] == 0) // 字符都是此前移入的，为0的必为需求字符！
                    /* diff[c] == 0，说明该字符刚好满足
                       一旦移除就不再满足 → less+1 */
                    less++;
                diff[sc[l]]++; // 需求加 1（归还字符）
                l++; // 左指针右移
            }
            r++; // 右指针继续扩张
        }
        System.out.println(Arrays.toString(diff));
        return ansL == -1 ? "" : s.substring(ansL, ansR + 1); // [beginIndex, endIndex)
    }

    public static void main(String[] args) {
        MinWindow m = new MinWindow();
        System.out.println(m.minWindow("dabdc", "abc"));
    }
}
