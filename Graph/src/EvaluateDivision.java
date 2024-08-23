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
        // 记录变量及其索引
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
            // 通过val连通两个数，更新子节点到父节点的权重
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
}
