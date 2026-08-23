package solution.labuladong.array.slideWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAnagrams {
    /* 438. 找到字符串中所有字母异位词 */
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> need = new HashMap<>(); // 需要的字符
        Map<Character, Integer> window = new HashMap<>(); // 窗口中的字符
        for(char c: p.toCharArray()){
            need.put(c, need.getOrDefault(c,0)+1);
        }
        int left = 0, right = 0; // 左闭右开
        int valid = 0; // 满足需求的字符个数
        List<Integer> res = new ArrayList<>(); // 结果列表

        while(right < s.length()){
            char c = s.charAt(right);
            right++;
            if(need.containsKey(c)){
                window.put(c, window.getOrDefault(c,0)+1);
                if(window.get(c).equals(need.get(c))){
                    valid++;
                }
            }
            while(right - left >= p.length()){ // 维护定长窗口
                if(valid == need.size()){
                    res.add(left);
                }
                char d = s.charAt(left);
                left++;
                if(need.containsKey(d)){
                    if(window.get(d).equals(need.get(d))){
                        valid--;
                    }
                    window.put(d, window.getOrDefault(d,0)-1);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FindAnagrams f = new FindAnagrams();
        System.out.println(f.findAnagrams("cbaebabacd", "abc"));
    }
}
