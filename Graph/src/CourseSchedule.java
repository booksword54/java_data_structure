import java.util.*;

public class CourseSchedule {
    // 课程表
    // 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
    // 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
    // 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
    // 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。

    // 广度优先搜索
    public boolean canFinish(int numCourses, int[][] prerequisites) {
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
        // 层序遍历，不断减少后续课程的入度，如果入度为零，后续课程可以上了，加入队列
        while (!zeroDegree.isEmpty()) {
            int size = zeroDegree.size();
            for (int i = 0; i < size; i++) {
                Integer preCourse = zeroDegree.poll();
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
                return false;
            }
        }
        // 全部课都可上
        return true;
    }

}
