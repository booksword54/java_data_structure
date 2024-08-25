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

    // 广度优先搜索 图 + 单向BFS
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null) {
            return 0;
        }
        // 无需转换，序列内单词数为1
        if (beginWord.equals(endWord)) {
            return 1;
        }
        int n = wordList.size();
        // 单词列表没有单词，无法转换
        if (n == 0) {
            return 0;
        }
        // 寻找终点单词在单词列表中的位置
        int endWordIdx = -1;
        for (int i = 0; i < n; i++) {
            if (endWord.equals(wordList.get(i))) {
                endWordIdx = i;
                break;
            }
        }
        // 终点单词不存在
        if (endWordIdx == -1) {
            return 0;
        }
        // 图数据结构，记录单词及其邻接单词
        // 按照单词列表中的位置存储单词的邻接单词，起始单词占据特殊位置 n
        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        // 记录起始单词的邻接单词
        int beginWordIdx = n; // 起始单词在图中占据特殊位置 n
        for (int i = 0; i < n; i++) {
            if (isNeighbor(beginWord, wordList.get(i))) {
                graph[beginWordIdx].add(i);
                graph[i].add(beginWordIdx);
            }
        }
        // 记录单词库中的单词邻接关系
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isNeighbor(wordList.get(i), wordList.get(j))) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }
        // 记录单词是否被转换过，因为是层序遍历，之前转换所需的步骤少
        boolean[] visited = new boolean[n + 1];
        // 层序遍历的队列，从起点单词开始，一层层的遍历邻接单词直到访问到终点单词
        Queue<Integer> curLevel = new LinkedList<>();
        curLevel.add(beginWordIdx);
        visited[beginWordIdx] = true;
        int step = 0;
        // 从起点单词，层序遍历邻接单词
        while (!curLevel.isEmpty()) {
            step++;
            // 下一层的队列
            Queue<Integer> nextLevel = new LinkedList<>();
            // 遍历当前队列的所有单词的所有邻接单词
            for (Integer wordIdx : curLevel) {
                for (Integer nextWordIdx : graph[wordIdx]) {
                    // 邻接单词就是终点单词
                    if (nextWordIdx == endWordIdx) {
                        return step + 1;
                    }
                    // 之前转换过，存在最小的转换次数，不再进行转换
                    if (visited[nextWordIdx]) {
                        continue;
                    }
                    // 加入下一层遍历，继续寻找其邻接单词
                    nextLevel.add(nextWordIdx);
                    // 邻接单词已经被转换过
                    visited[nextWordIdx] = true;
                }
            }
            // 遍历下一层的邻接单词
            curLevel = nextLevel;
        }
        return 0;
    }

    // 图 + 双向 BFS
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null) {
            return 0;
        }
        // 无需转换，序列内单词数为1
        if (beginWord.equals(endWord)) {
            return 1;
        }
        int n = wordList.size();
        // 单词列表没有单词，无法转换
        if (n == 0) {
            return 0;
        }
        // 寻找终点单词在单词列表中的位置
        int endWordIdx = -1;
        for (int i = 0; i < n; i++) {
            if (endWord.equals(wordList.get(i))) {
                endWordIdx = i;
                break;
            }
        }
        // 终点单词不存在
        if (endWordIdx == -1) {
            return 0;
        }
        // 图数据结构，记录单词及其邻接单词
        // 按照单词列表中的位置存储单词的邻接单词，起始单词占据特殊位置 n
        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        // 记录起始单词的邻接单词
        int beginWordIdx = n; // 起始单词在图中占据特殊位置 n
        for (int i = 0; i < n; i++) {
            if (isNeighbor(beginWord, wordList.get(i))) {
                graph[beginWordIdx].add(i);
                graph[i].add(beginWordIdx);
            }
        }
        // 记录单词库中的单词邻接关系
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isNeighbor(wordList.get(i), wordList.get(j))) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }
        // 记录单词是否被转换过，因为是层序遍历，之前转换所需的步骤少
        boolean[] visited = new boolean[n + 1];
        // 层序遍历的队列，从起点单词开始，一层层的遍历邻接单词直到访问到终点单词
        Set<Integer> curLevel = new HashSet<>();
        curLevel.add(beginWordIdx);
        visited[beginWordIdx] = true;
        Set<Integer> targetLevel = new HashSet<>();
        targetLevel.add(endWordIdx);
        int step = 0;
        // 从起点单词，层序遍历邻接单词
        while (!curLevel.isEmpty()) {
            step++;
            if (curLevel.size() > targetLevel.size()) {
                Set<Integer> temp = curLevel;
                curLevel = targetLevel;
                targetLevel = temp;
            }
            // 下一层的队列
            Set<Integer> nextLevel = new HashSet<>();
            // 遍历当前队列的所有单词的所有邻接单词
            for (Integer wordIdx : curLevel) {
                for (Integer nextWordIdx : graph[wordIdx]) {
                    // 邻接单词就是终点单词
                    if (targetLevel.contains(nextWordIdx)) {
                        return step + 1;
                    }
                    // 之前转换过，存在最小的转换次数，不再进行转换
                    if (visited[nextWordIdx]) {
                        continue;
                    }
                    // 加入下一层遍历，继续寻找其邻接单词
                    nextLevel.add(nextWordIdx);
                    // 邻接单词已经被转换过
                    visited[nextWordIdx] = true;
                }
            }
            // 遍历下一层的邻接单词
            curLevel = nextLevel;
        }
        return 0;
    }

    // 判断两单词是否邻接
    private boolean isNeighbor(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int len = s1.length();
        int diff = 0;
        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }
        }
        return diff == 1;
    }
}
