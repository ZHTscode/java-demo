package solution.labuladong.array.slideWindow;

public class LongestSubstring {
    /* 395. 至少有 K 重复字符的最长子串 */
    public int longestSubstring(String s, int k) {
        int res = 0;
        for(int i = 1; i<=26; i++){
            // 限制窗口中不同字母的个数为i
            res = Math.max(res, longestKLetterSubstr(s, k, i));
        }
        return res;
    }

    private int longestKLetterSubstr(String s, int k, int count){
        int res = 0;
        int left = 0, right = 0;
        int[] windowCount = new int[26]; // 窗口中各个字母的个数
        int windowDiff = 0; // 窗口中字母的种类数
        int windowValid = 0; // 窗口中满足个数要求的字母种类数
        while(right < s.length()){
            char c = s.charAt(right);
            if(windowCount[c-'a'] == 0){
                windowDiff++; // 窗口中新增一种字母
            }
            windowCount[c-'a']++; // 窗口中c的个数加1
            if(windowCount[c-'a'] == k){
                windowValid++; // c为有效字符，被添加
            }
            right++;

            while(windowDiff > count){ // 窗口中字母的种类数超过count（窗口至少有字符，必然满足 left < right）
                char d = s.charAt(left);
                left++; // 窗口左边界右移
                windowCount[d-'a']--; // 窗口中d的个数减1
                if(windowCount[d-'a'] == 0){
                    windowDiff--; // 窗口中d的个数减为0，diff减1
                }
                if(windowCount[d-'a'] == k){
                    windowValid--; // d为有效字符，被移除
                }
            }
            // 以上维持窗口中字母的种类数为count
            if(windowValid == count){ // 窗口中所有字母都满足个数要求
                res = Math.max(res, right - left);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LongestSubstring ls = new LongestSubstring();
        System.out.println(ls.longestSubstring("abaabb", 3));
    }
}
