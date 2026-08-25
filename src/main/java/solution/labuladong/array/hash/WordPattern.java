package solution.labuladong.array.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordPattern {
    /* 290. 单词规律 */
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (pattern.length() != words.length)
            return false;
        Map<Character, String> patternToWord = new HashMap<>();
        Set<String> usedWords = new HashSet<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];
            if (patternToWord.containsKey(c)) { // 字符c已经映射到单词word
                if (!patternToWord.get(c).equals(word)) { // 字符c映射的单词不是word，矛盾
                    return false;
                }
            } else {
                if (usedWords.contains(word)) { // 单词word已经映射给其他字符
                    return false;
                }
                patternToWord.put(c, word); // 字符c映射到单词word
                usedWords.add(word); // 单词word标记为已使用
            }
        }
        return true;
    }

    public static void main(String[] args) {
        WordPattern wordPattern = new WordPattern();
        String pattern = "abba";
        String s = "dog cat cat dog";
        boolean res = wordPattern.wordPattern(pattern, s);
        System.out.println(res);
    }
}
