package bfs;

import java.util.*;

public class WordLadder {
    // 单词接龙
    // 字典 wordList 中从单词 beginWord 到 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
    // - 每一对相邻的单词只差一个字母。
    // - 对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
    // - sk == endWord
    // 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，
    // 返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。

    // 广度优先搜索 单向BFS
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }
        wordSet.remove(beginWord);

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        visited.add(beginWord);
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int c = 0; c < size; c++) {
                String curWord = queue.poll();
                char[] chars = curWord.toCharArray();
                for (int i = 0; i < endWord.length(); i++) {
                    char origin = chars[i];
                    for (char j = 'a'; j <= 'z'; j++) {
                        if (j == origin) {
                            continue;
                        }
                        chars[i] = j;
                        String nextWord = String.valueOf(chars);
                        if (wordSet.contains(nextWord)) {
                            if (nextWord.equals(endWord)) {
                                return step + 1;
                            }
                            if (!visited.contains(nextWord)) {
                                queue.add(nextWord);
                                visited.add(nextWord);
                            }
                        }
                    }
                    chars[i] = origin;
                }
            }
            step++;
        }
        return 0;
    }

    // 双向 BFS
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }
        Set<String> visited = new HashSet<>();
        Set<String> curLevel = new HashSet<>();
        curLevel.add(beginWord);
        visited.add(beginWord);
        Set<String> targetLevel = new HashSet<>();
        targetLevel.add(endWord);
        int step = 1;
        while (!curLevel.isEmpty() && !targetLevel.isEmpty()) {
            if (curLevel.size() > targetLevel.size()) {
                Set<String> temp = curLevel;
                curLevel = targetLevel;
                targetLevel = temp;
            }
            Set<String> nextLevel = new HashSet<>();
            for (String curWord : curLevel) {
                char[] chars = curWord.toCharArray();
                for (int i = 0; i < curWord.length(); i++) {
                    char origin = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == origin) {
                            continue;
                        }
                        chars[i] = c;
                        String nextWord = String.valueOf(chars);
                        if (wordSet.contains(nextWord)) {
                            if (targetLevel.contains(nextWord)) {
                                return step + 1;
                            }
                            if (!visited.contains(nextWord)) {
                                nextLevel.add(nextWord);
                                visited.add(nextWord);
                            }
                        }
                    }
                    chars[i] = origin;
                }
            }
            curLevel = nextLevel;
            step++;
        }
        return 0;
    }
}
