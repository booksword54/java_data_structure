package trie;

class WordDictionary {
    // 添加与搜索单词 - 数据结构设计
    // 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
    // 实现词典类 WordDictionary ：
    // - WordDictionary() 初始化词典对象
    // - void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
    // - bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。

    private Trie root;

    public WordDictionary() {
        root = new Trie();
    }

    public void addWord(String word) {
        root.insert(word);
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int index, Trie node) {
        if (index == word.length()) {
            return node.isEnd;
        }
        char ch = word.charAt(index);
        if (Character.isLetter(ch)) {
            int childIdx = ch - 'a';
            Trie child = node.children[childIdx];
            if (child != null && dfs(word, index + 1, child)) {
                return true;
            }
        } else if (ch == '.') {
            for (int i = 0; i < 26; i++) { // 匹配任意一个字符
                Trie child = node.children[i];
                if (child != null && dfs(word, index + 1, child)) {
                    return true;
                }
            }
        }
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */