package solution;

import java.util.*;

public class GroupAnagrams {
    /* 49. 字母异位词分组 */
    public List<List<String>> groupAnagrams(String[] strs) {
        // key=排序后的字符串，value=对应的异位词列表
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] charArr = s.toCharArray(); // 字符串转为字符数组，方便排序
            Arrays.sort(charArr);
            String key = new String(charArr); // 转回字符串作为key
            if (!map.containsKey(key)) { // key不存在则创建
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s); // 添加元素
        }
        return new ArrayList<>(map.values()); // 哈希表的value集合强转为ArrayList
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> m = new HashMap<>();
        for (String s : strs) {
            char[] array = s.toCharArray();
            Arrays.sort(array);
            String k = new String(array);
            List<String> list = m.getOrDefault(k, new ArrayList<>());
            list.add(s);
            m.put(k, list);
        }
        return new ArrayList<>(m.values());
    }

    public static void main(String[] args) {
        GroupAnagrams ga = new GroupAnagrams();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(ga.groupAnagrams(strs));
    }
}
