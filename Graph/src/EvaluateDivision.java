import java.util.*;

public class EvaluateDivision {
    // 除法求值
    // 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，
    // 其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
    // 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
    // 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
    // 如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。

    // 带权并查集
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int nVars = 0;
        Map<String, Integer> variables = new HashMap<>();
        int n = equations.size();
        // 记录变量及其标识
        for (List<String> equation : equations) {
            if (!variables.containsKey(equation.get(0))) {
                variables.put(equation.get(0), nVars++);
            }
            if (!variables.containsKey(equation.get(1))) {
                variables.put(equation.get(1), nVars++);
            }
        }
        int[] parent = new int[nVars]; // 并查集父节点
        double[] weight = new double[nVars]; // 到父节点的权重
        for (int i = 0; i < nVars; i++) {
            parent[i] = i;
        }
        Arrays.fill(weight, 1.0); // 初始时父节点都是自己
        for (int i = 0; i < n; i++) {
            int va = variables.get(equations.get(i).get(0));
            int vb = variables.get(equations.get(i).get(1));
            // 通过val连通两个数，并更新到父节点的权重
            union(parent, weight, va, vb, values[i]);
        }
        // 处理查询
        int queriesSize = queries.size();
        double[] res = new double[queriesSize];
        for (int i = 0; i < queriesSize; i++) {
            List<String> query = queries.get(i);
            double result = -1.0; // 默认结果
            // 两个操作数都存在
            if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
                int ia = variables.get(query.get(0));
                int ib = variables.get(query.get(1));
                // ia ib都属于一个父节点，可以连通，可以计算出一个value
                if (find(parent, weight, ia) == find(parent, weight, ib)) {
                    result = weight[ia] / weight[ib]; // value就是两者距离父节点的权重相除
                }
            }
            res[i] = result;
        }
        return res;
    }

    // 父节点连通，并更新子节点到父节点的权重
    private void union(int[] parent, double[] weight, int x, int y, double val) {
        int px = find(parent, weight, x);
        int py = find(parent, weight, y);
        parent[px] = py; // 父节点合并
        weight[px] = val * (weight[y] / weight[x]); // px到父节点权重更新
    }

    // 寻找父节点，并更新到父节点的权重
    private int find(int[] parent, double[] weight, int x) {
        if (x != parent[x]) {
            int p = find(parent, weight, parent[x]); // 往上递归寻找父节点(parent等于自己)
            weight[x] = weight[x] * weight[parent[x]]; // 到父节点的权重与途径的权重相乘
            parent[x] = p; // 更新父节点
        }
        return parent[x];
    }

    // 广度优先搜索
    public double[] calcEquation2(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int nVars = 0;
        Map<String, Integer> variables = new HashMap<>();
        int n = equations.size();
        // 记录变量及其标识
        for (List<String> equation : equations) {
            if (!variables.containsKey(equation.get(0))) {
                variables.put(equation.get(0), nVars++);
            }
            if (!variables.containsKey(equation.get(1))) {
                variables.put(equation.get(1), nVars++);
            }
        }
        // 每个节点的连通节点集合
        List<Pair>[] edges = new List[nVars];
        for (int i = 0; i < nVars; i++) {
            edges[i] = new ArrayList<>();
        }
        // 每对元素互相连通，保存到达彼此的权重(相除的结果)
        for (int i = 0; i < n; i++) {
            int va = variables.get(equations.get(i).get(0));
            int vb = variables.get(equations.get(i).get(1));
            edges[va].add(new Pair(vb, values[i]));
            edges[vb].add(new Pair(va, 1.0 / values[i]));
        }
        // 开始计算
        int queriesSize = queries.size();
        double[] res = new double[queriesSize];
        for (int i = 0; i < queriesSize; i++) {
            List<String> query = queries.get(i); // 运算
            double result = -1.0;
            // 操作数都存在
            if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
                int ia = variables.get(query.get(0));
                int ib = variables.get(query.get(1));
                // 是相同的操作数
                if (ia == ib) {
                    res[i] = 1.0;
                    continue;
                }
                // 广度优先搜索，寻找操作数之间的权重
                Queue<Integer> points = new LinkedList<>();
                points.add(ia); // 从 ia 寻找 ib
                double[] ratios = new double[nVars];
                Arrays.fill(ratios, -1.0); // 默认操作数之间不能相除，也表示没有访问过
                ratios[ia] = 1.0; // 与自己相除的结果
                // 层序遍历，记录与每一层相除的结果(基于上一层的结果)
                while (!points.isEmpty() && ratios[ib] == -1) {
                    int x = points.poll();
                    for (Pair pair : edges[x]) { // 遍历连通节点集合
                        int y = pair.index;  // 连通的数
                        double val = pair.value; // 相除的结果
                        if (ratios[y] == -1) {
                            // 统计 ia / y 的结果
                            ratios[y] = ratios[x] * val;
                            points.add(y);
                        }
                    }
                }
                // ia / ib 的结果，如果还是-1，证明层序遍历没访问到，两者没连通，不能相除
                result = ratios[ib];
            }
            res[i] = result;
        }
        return res;
    }

    // Floyd 算法
    public double[] calcEquation3(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int nVars = 0;
        Map<String, Integer> variables = new HashMap<>();
        int n = equations.size();
        // 记录变量及其标识
        for (List<String> equation : equations) {
            if (!variables.containsKey(equation.get(0))) {
                variables.put(equation.get(0), nVars++);
            }
            if (!variables.containsKey(equation.get(1))) {
                variables.put(equation.get(1), nVars++);
            }
        }
        // 用图记录双向的相除结果
        double[][] graph = new double[nVars][nVars];
        for (int i = 0; i < nVars; i++) {
            Arrays.fill(graph[i], -1.0); // 默认与所有的数相除的结果都是 -1
        }
        // 记录双向的相除结果
        for (int i = 0; i < n; i++) {
            int va = variables.get(equations.get(i).get(0));
            int vb = variables.get(equations.get(i).get(1));
            graph[va][vb] = values[i];
            graph[vb][va] = 1.0 / values[i];
        }
        // 除法的结合律与传递性，连通能够相除的操作数
        for (int i = 0; i < nVars; i++) {
            for (int j = 0; j < nVars; j++) {
                for (int k = 0; k < nVars; k++) {
                    if (graph[i][j] != -1.0 && graph[j][k] != -1.0) {
                        graph[i][k] = graph[i][j] * graph[j][k];
                    }
                }
            }
        }
        // 到这里已经计算了所有能够相除的操作之间的结果，记录在图里面
        // 遍历图，获取两个操作数相除的运算的结果
        int queriesSize = queries.size();
        double[] res = new double[queriesSize];
        for (int i = 0; i < queriesSize; i++) {
            List<String> query = queries.get(i);
            double result = -1.0;
            // 操作数存在
            if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
                int ia = variables.get(query.get(0));
                int ib = variables.get(query.get(1));
                if (graph[ia][ib] != -1) { // 可以相除，返回结果
                    result = graph[ia][ib];
                }
            }
            // 两数相除的结果
            res[i] = result;
        }
        return res;
    }
}

class Pair {
    int index; // 元素的标识
    double value; // 到达父节点的权重
    Pair(int index, double value) {
        this.index = index;
        this.value = value;
    }
}

