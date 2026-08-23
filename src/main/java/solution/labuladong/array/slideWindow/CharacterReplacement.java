package solution.labuladong.array.slideWindow;

public class CharacterReplacement {
    /* 424. 替换后的最长重复字符 */
    public int characterReplacement(String s, int k) {
        int left = 0, right = 0;
        int[] windowCharCount = new int[26]; // 窗口内字符个数统计
        int windowMaxCharCount = 0;
        int res = 0;

        while(right < s.length()){
            char c = s.charAt(right);
            right++;
            windowCharCount[c - 'A']++;
            windowMaxCharCount = Math.max(windowMaxCharCount, windowCharCount[c - 'A']);
            if(right - left > k + windowMaxCharCount ){ // 收缩左边界，只执行一次
                char d = s.charAt(left);
                left++;
                windowCharCount[d - 'A']--;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    public static void main(String[] args) {
        CharacterReplacement characterReplacement = new CharacterReplacement();
        String s = "AABABBA";
        int k = 1;
        System.out.println(characterReplacement.characterReplacement(s, k));
    }
}
