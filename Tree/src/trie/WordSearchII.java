package trie;

import java.util.*;

public class WordSearchII {
    // 单词搜索 II
    // 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。
    // 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。

    // 回溯 + 字典树
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<String> findWords(char[][] board, String[] words) {
        PrefixTree trie = new PrefixTree();
        for (String word : words) {
            trie.insert(word);
        }
        Set<String> res = new HashSet<>();
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (trie.children.containsKey(board[i][j])) {
                    dfs(board, trie, i, j, m, n, res);
                }
            }
        }
        return new ArrayList<>(res);
    }

    private void dfs(char[][] board, PrefixTree node, int i, int j, int m, int n, Set<String> res) {
        char ch = board[i][j];
        PrefixTree cur = node.children.get(ch);
        if (cur.isWord) {
            res.add(cur.word);
            cur.word = "";
            cur.isWord = false;
        }
        if (!cur.children.isEmpty()) {
            board[i][j] = '#'; // 无意义的字符，表示访问过，不能再参与组成单词
            for (int[] dir : dirs) {
                int ni = i + dir[0];
                int nj = j + dir[1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && cur.children.containsKey(board[ni][nj])) {
                    dfs(board, cur, ni, nj, m, n, res);
                }
            }
            board[i][j] = ch; // 回溯
        }
        // 删除被匹配的单词
        if (cur.children.isEmpty()) {
            node.children.remove(ch);
        }
    }
}

class PrefixTree {
    String word;
    Map<Character, PrefixTree> children;
    boolean isWord;

    public PrefixTree() {
        this.word = "";
        this.children = new HashMap<Character, PrefixTree>();
    }

    public void insert(String word) {
        PrefixTree cur = this;
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new PrefixTree());
            }
            cur = cur.children.get(c);
        }
        cur.word = word;
        cur.isWord = true;
    }
}