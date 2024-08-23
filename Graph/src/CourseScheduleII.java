import java.util.*;

public class CourseScheduleII {
    // 课程表 II
    // 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
    // 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
    // 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。

    // 广度优先搜索
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) {
            return new int[0];
        }
        // 上课的顺序
        int[] res = new int[numCourses];
        // 没有前后顺序的要求，课程随便上
        if (prerequisites == null || prerequisites.length == 0) {
            for (int i = 0; i < numCourses; i++) {
                res[i] = i;
            }
            return res;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] inDegree = new int[numCourses];
        // 记录课程的后续课程，并将后续课程的入度加一
        for (int[] pre : prerequisites) {
            List<Integer> list = map.getOrDefault(pre[1], new ArrayList<>());
            list.add(pre[0]);
            map.put(pre[1], list);
            inDegree[pre[0]]++;
        }
        // 遍历入度为0的课程，即首先上的课程
        Queue<Integer> zeroDegree = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                zeroDegree.add(i);
            }
        }
        // 没有课程可以上，课程前后关系出现循环依赖
        if (zeroDegree.isEmpty()) {
            return new int[0];
        }
        int idx = 0;
        // 层序遍历，不断减少后续课程的入度，如果入度为零，后续课程可以上了，加入队列
        while (!zeroDegree.isEmpty()) {
            int size = zeroDegree.size(); // 这种层序遍历分层更清晰
            for (int i = 0; i < size; i++) {
                int preCourse = zeroDegree.poll();
                res[idx++] = preCourse;
                List<Integer> list = map.get(preCourse);
                if (list == null) { // 后续课程为空，不会解锁新的课程，也避免空指针异常
                    continue;
                }
                // 解锁新的课程，后续课程入度减一
                for (Integer course : list) {
                    inDegree[course]--;
                    if (inDegree[course] == 0) {
                        zeroDegree.add(course);
                    }
                }
            }
        }
        // 如果还有入度不为0的课程，证明存在无法上的课程
        for (int degree : inDegree) {
            if (degree > 0) {
                return new int[0];
            }
        }
        // 全部课都可上
        return res;
    }
}
