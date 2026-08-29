package solution.labuladong.array.hash;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    /* 49. 字母异位词分组 */
    public List<List<String>> groupAnagrams(String[] strs) {
        // 编码到分组的映射
        Map<String, List<String>> codeToGroup = new HashMap<>();
        for (String s : strs) {
            // 对字符串进行编码
            String code = encode(s);
            // 把编码相同的字符串放在一起
            codeToGroup.putIfAbsent(code, new LinkedList<>());
            codeToGroup.get(code).add(s);
        }
        // 获取结果
        List<List<String>> res = new LinkedList<>();
        for (List<String> group : codeToGroup.values()) {
            res.add(group);
        }
        return res;
    }

    // 利用每个字符的出现次数进行编码
    String encode(String s) {
        char[] count = new char[26];
        for (char c : s.toCharArray()) {
            int delta = c - 'a';
            count[delta]++;
        }
        return new String(count);
    }

    public static void main(String[] args) {
        GroupAnagrams solution = new GroupAnagrams();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> res = solution.groupAnagrams(strs);
        System.out.println(res);
    }
}
