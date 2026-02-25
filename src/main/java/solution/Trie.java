package solution;

public class Trie {
    /* 208. 实现 Trie (前缀树) */
    // Trie 节点内部类（核心结构）
    private static class TrieNode {
        TrieNode[] children; // 子节点数组：a-z 对应下标 0-25
        boolean isEnd;       // 标记是否是完整单词的结尾
        // 构造方法：节点初始化，子节点数组默认全为 null，isEnd 默认为 false
        public TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }
    private TrieNode root; // Trie 的根节点（空节点，不存储任何字符）
    // 1. 初始化 Trie：根节点是一个空的 TrieNode
    public Trie() {
        root = new TrieNode();
    }
    // 2. 插入字符串到  Trie 中
    public void insert (String word){
        TrieNode current = root; // 从根节点开始遍历
        for (char c : word.toCharArray()) {
            int index = c - 'a'; // 计算字符对应的下标（a→0，b→1...）
            // 如果当前字符的子节点不存在，创建新节点
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            // 移动到子节点，继续处理下一个字符
            current = current.children[index];
        }
        // 遍历完所有字符，标记为完整单词的结尾
        current.isEnd = true;
    }
    // 3. 查询字符串是否是 Trie 中的完整单词
    public boolean search (String word){
        TrieNode node = searchPrefix(word);
        // 节点存在 + 是完整单词结尾，才返回 true
        return node != null && node.isEnd;
    }
    // 4. 查询 Trie 中是否存在以 prefix 为前缀的字符串
    public boolean startsWith (String prefix){
        // 只要前缀能匹配完，无需检查 isEnd
        return searchPrefix(prefix) != null;
    }

    // 私有辅助方法：查询前缀（核心复用逻辑）返回匹配完成后的最后一个节点
    private TrieNode searchPrefix (String prefix){
        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            // 字符对应的子节点不存在，说明前缀/单词不存在
            if (current.children[index] == null) {
                return null;
            }
            current = current.children[index];
        }
        // 所有字符匹配成功，返回最后一个节点
        return current;
    }

    public static void main (String[]args){
        Trie trie = new Trie();
        // 插入测试
        trie.insert("apple");
        // 查询完整单词
        System.out.println(trie.search("apple"));   // true（完整单词）
        System.out.println(trie.search("app"));     // false（不是完整单词）
        // 查询前缀
        System.out.println(trie.startsWith("app")); // true（是前缀）
        // 插入新单词
        trie.insert("app");
        System.out.println(trie.search("app"));     // true（现在是完整单词）
    }
}

