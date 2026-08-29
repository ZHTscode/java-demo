package solution.labuladong.array.slideWindow;

import java.util.HashMap;
import java.util.Map;

public class MinWindow {
    /* 76. 最小覆盖子串 */
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for(char c: t.toCharArray()){
            need.put(c, need.getOrDefault(c,0)+1); // 统计t中每个字符的个数
        }
        int left = 0, right = 0; // 左闭右开
        int valid = 0; // 满足需求的字符个数
        int start = 0, len = Integer.MAX_VALUE; // 记录最小覆盖子串的起始位置和长度

        while(right < s.length()){
            char c = s.charAt(right);
            right++;
            if(need.containsKey(c)){
                window.put(c, window.getOrDefault(c,0)+1);
                if(window.get(c).equals(need.get(c))){ // 窗口内字符c的个数满足了需求
                    valid++;
                }
            }
            while(valid == need.size()){ // 所有字符都满足需求，窗口开始收缩
                if(right - left < len){
                    start = left;
                    len = right - left;
                }
                char d = s.charAt(left); // 移出窗口的字符下标
                left++;
                if(need.containsKey(d)){
                    if(window.get(d).equals(need.get(d))){ // 字符d的个数原本恰好满足需求
                        valid--;
                    }
                    window.put(d, window.getOrDefault(d,0)-1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start+len);
    }

    public static void main(String[] args) {
        MinWindow minWindow = new MinWindow();
        System.out.println(minWindow.minWindow("ADOBECODEBANC", "ABC"));
    }
}
