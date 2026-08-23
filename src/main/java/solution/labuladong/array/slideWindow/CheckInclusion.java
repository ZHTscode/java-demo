package solution.labuladong.array.slideWindow;

import java.util.HashMap;
import java.util.Map;

public class CheckInclusion {
    /* 567. 字符串的排列 */
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for(char c: s1.toCharArray()){
            need.put(c, need.getOrDefault(c,0)+1);
        }
        int left = 0, right = 0; // 左闭右开
        int valid = 0; // 满足需求的字符个数
        while(right < s2.length()){
            char c = s2.charAt(right);
            right++; // 移入元素，扩大窗口
            if(need.containsKey(c)){
                window.put(c, window.getOrDefault(c,0)+1);
                if(window.get(c).equals(need.get(c))){ // 窗口内字符c的个数满足了需求
                    valid++;
                }
            }
            while(right - left >= s1.length()){ // 维护定长窗口
                if(valid == need.size()){
                    return true;
                }
                char d = s2.charAt(left);
                left++;
                if(need.containsKey(d)){ // 字符d的个数原本恰好满足需求
                    if(window.get(d).equals(need.get(d))){
                        valid--;
                    }
                    window.put(d, window.getOrDefault(d,0)-1);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CheckInclusion c = new CheckInclusion();
        System.out.println(c.checkInclusion("ab", "eidbaooo"));
    }
}
