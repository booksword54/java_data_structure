package bfs;

import java.util.*;

public class MinimumGeneticMutation {
    // 最小基因变化
    // 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
    // 假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
    // - 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
    // 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。（变化后的基因必须位于基因库 bank 中）
    // 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。
    // 注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。

    // 图 + BFS
    public int minMutation(String startGene, String endGene, String[] bank) {
        if (startGene.equals(endGene)) {
            return 0;
        }
        int n = bank.length;
        int startGeneIdx = n;
        int endGeneIdx = -1;
        for (int i = 0; i < n; i++) {
            if (endGene.equals(bank[i])) {
                endGeneIdx = i;
                break;
            }
        }
        if (endGeneIdx == -1) {
            return -1;
        }
        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            if (isNeighbor(bank[i], startGene)) {
                graph[i].add(startGeneIdx);
                graph[startGeneIdx].add(i);
            }
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isNeighbor(bank[i], bank[j])) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }
        int[] res = new int[n + 1];
        boolean[] visit = new boolean[n + 1];
        Queue<Integer> curLevel = new LinkedList<>();
        curLevel.add(endGeneIdx);
        visit[endGeneIdx] = true;
        while (!curLevel.isEmpty()) {
            Queue<Integer> nextLevel = new LinkedList<>();
            for (Integer geneIdx : curLevel) {
                for (Integer nextGeneIdx : graph[geneIdx]) {
                    if (visit[nextGeneIdx]) {
                        continue;
                    }
                    res[nextGeneIdx] = res[geneIdx] + 1;
                    if (nextGeneIdx == startGeneIdx) {
                        return res[nextGeneIdx];
                    }
                    nextLevel.add(nextGeneIdx);
                    visit[nextGeneIdx] = true;
                }
            }
            curLevel = nextLevel;
        }
        return -1;
    }

    private boolean isNeighbor(String s1, String s2) {
        int diff = 0;
        for (int i = 0; i < 8; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }
        }
        return diff == 1;
    }

    // 广度优先遍历
    public int minMutation1(String startGene, String endGene, String[] bank) {
        // 起点就是终点
        if (startGene.equals(endGene)) {
            return 0;
        }
        // 基因库的基因序列集合
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        // 终点是无效的
        if (!bankSet.contains(endGene)) {
            return -1;
        }
        // 记录基因是否被判断过是否可以到达终点
        Set<String> visited = new HashSet<>();
        char[] keys = {'A', 'G', 'C', 'T'};
        // 层序遍历，每个字符突变形成新基因，加入队列，访问下层...
        Queue<String> curLevel = new LinkedList<>();
        curLevel.add(startGene); // 起点节点加入当前层
        visited.add(startGene); // 起点节点已经被判断过

        int step = 0; // 基因突变次数
        while (!curLevel.isEmpty()) {
            step++;
            // 下一层遍历的节点
            Queue<String> nextLevel = new LinkedList<>();
            // 遍历当前层的所有基因
            for (String gene : curLevel) {
                // 变化基因的每个字符。形成新基因，加入队列，层序遍历
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 4; j++) {
                        // 跳过相同字符
                        if (keys[j] == gene.charAt(i)) {
                            continue;
                        }
                        // 变化字符，形成新基因
                        StringBuilder sb = new StringBuilder(gene);
                        sb.setCharAt(i, keys[j]);
                        String nextGene = sb.toString();
                        // 新基因就是终点，返回结果
                        if (nextGene.equals(endGene)) {
                            return step;
                        }
                        // 基因没有访问过，且有效
                        if (!visited.contains(nextGene) && bankSet.contains(nextGene)) {
                            // 加入下层遍历
                            nextLevel.add(nextGene);
                            // 加入访问集合
                            visited.add(nextGene);
                        }
                        // 访问过的基因，或者无效的基因(不会进行层序遍历)不作处理
                    }
                }
            }
            // 开始遍历下一层，当前层切换为下一层
            curLevel = nextLevel;
        }
        // 遍历了所有可能演变的结果，还是没有到达终点
        return -1;
    }

    // 双向BFS
    public int minMutation2(String startGene, String endGene, String[] bank) {
        // 起点就是终点
        if (startGene.equals(endGene)) {
            return 0;
        }
        // 基因库的基因序列集合
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        // 终点是无效的
        if (!bankSet.contains(endGene)) {
            return -1;
        }
        // 记录基因是否被判断过是否可以到达终点
        Set<String> visited = new HashSet<>();
        char[] keys = {'A', 'G', 'C', 'T'};
        // 层序遍历，每个字符突变形成新基因，加入队列，访问下层...
        // 使用Set存储,判断是否包含某个节点的时候更加便捷
        Set<String> curLevel = new HashSet<>(); // 当前层遍历的节点
        curLevel.add(startGene); // 起点节点加入当前层
        visited.add(startGene); // 起点节点已经被判断过
        Set<String> targetLevel = new HashSet<>(); // 终点层，层中的节点可以演变成终点
        targetLevel.add(endGene);

        int step = 0;
        while (!curLevel.isEmpty()) {
            step++;
            // 采用元素较少的层，作为当前遍历层，总的回溯的次数尽可能少(纺锤形的层序遍历，而不是金字塔形，金字塔减去纺锤就是减少的回溯次数)
            // 呈现的效果是，curLevel层序遍历几次，targetLevel又层序遍历几次，最终汇合
            if (curLevel.size() > targetLevel.size()) {
                Set<String> temp = curLevel;
                curLevel = targetLevel;
                targetLevel = temp;
            }
            // 下一层遍历的节点
            Set<String> nextLevel = new HashSet<>();
            // 遍历当前层的所有基因
            for (String gene : curLevel) {
                // 变化当前层基因的每一个字符。形成新基因，加入下一层，层序遍历
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 4; j++) {
                        // 变换为不同的字符，跳过相同字符
                        if (keys[j] == gene.charAt(i)) {
                            continue;
                        }
                        // 变化字符，形成新基因
                        StringBuilder sb = new StringBuilder(gene);
                        sb.setCharAt(i, keys[j]);
                        String nextGene = sb.toString();
                        // 新基因可以转变为终点，返回结果
                        if (targetLevel.contains(nextGene)) {
                            return step;
                        }
                        // 基因没有访问过，且有效，加入下层遍历
                        if (!visited.contains(nextGene) && bankSet.contains(nextGene)) {
                            // 加入下层遍历
                            nextLevel.add(nextGene);
                            // 加入访问集合
                            visited.add(nextGene);
                        }
                        // 访问过的基因，或者无效的基因(不会进行层序遍历)不作处理
                    }
                }
            }
            // 开始遍历下一层，当前层切换为下一层
            curLevel = nextLevel;
        }
        // 遍历了所有可能演变的结果，还是没有到达终点
        return -1;
    }

    // A* 算法 启发式搜索
    class Node {
        String startGene;
        String targetGene;
        // 与T相似的分值
        int similarityScore;

        Node(String startGene, String targetGene) {
            this.startGene = startGene;
            this.targetGene = targetGene;
            for (int i = 0; i < 8; i++) {
                if (startGene.charAt(i) != targetGene.charAt(i)) {
                    similarityScore++;
                }
            }
        }
    }

    static char[] keys = new char[]{'A', 'G', 'C', 'T'};
    String START_GENE, TARGET_GENE;

    public int minMutation3(String startGene, String endGene, String[] bank) {
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((a, b) -> a.similarityScore - b.similarityScore);
        Map<String, Integer> map = new HashMap<>();
        priorityQueue.add(new Node(startGene, endGene));
        map.put(startGene, 0); // 转换成S需要0步
        // 层序遍历，优先搜寻最接近target的元素
        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            Integer step = map.get(node.startGene);
            char[] geneSeq = node.startGene.toCharArray();
            for (int i = 0; i < 8; i++) {
                for (char c : keys) {
                    if (c == geneSeq[i]) {
                        continue;
                    }
                    char[] nextGeneSeq = geneSeq.clone();
                    nextGeneSeq[i] = c;
                    String nextGene = String.valueOf(nextGeneSeq);
                    // 无效的基因
                    if (!bankSet.contains(nextGene)) {
                        continue;
                    }
                    if (nextGene.equals(endGene)) {
                        return step + 1; // 转换成endGene需要 step + 1步
                    }
                    // 之前没有转换到这个字符串，或者转换需要的步骤比当前转换多
                    if (!map.containsKey(nextGene) || map.get(nextGene) > step + 1) {
                        map.put(nextGene, step + 1); // 转换成 nextGene需要 step + 1步
                        // 加入下层的层序遍历。搜索最接近元素，更新转换需要的步骤
                        priorityQueue.add(new Node(nextGene, endGene));
                    }
                }
            }
        }
        return -1;
    }

}
