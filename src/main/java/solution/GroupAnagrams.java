package solution;

import java.util.*;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 哈希表：key=排序后的字符串，value=对应的异位词列表
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            // 1. 将字符串转为字符数组，方便排序
            char[] charArr = s.toCharArray();
            // 2. 排序（核心：异位词排序后结果一致）
            Arrays.sort(charArr);
            // System.out.println(charArr);
            // 3. 转回字符串作为key
            String key = new String(charArr);
            // 4. 分组：key不存在则创建新列表，存在则直接添加
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s); // 无论列表是否新建，都统一添加元素
            System.out.println(key + " : " + map.get(key));
        }
        // 5. 哈希表的value集合就是最终结果
        return new ArrayList<>(map.values());
    }
    public static void main(String[] args) {
        GroupAnagrams ga = new GroupAnagrams();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(ga.groupAnagrams(strs));
    }
}
