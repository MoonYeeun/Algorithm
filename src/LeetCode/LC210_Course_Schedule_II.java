package LeetCode;

import java.util.*;

// 210. Course Schedule II
// 위상정렬
public class LC210_Course_Schedule_II {
    public static void main(String[] args) {
//        int numCourses = 4;
//        int[][] prrerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int numCourses = 3;
        int[][] prrerequisites = {{1, 0}, {1, 2}, {0, 1}};

        int[] result = findOrder(numCourses, prrerequisites);
        for (int i : result) {
            System.out.println(i);
        }
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ans = new int[numCourses];
        int[] indegree = new int[numCourses]; // 해당 코스 전 들어야 하는 코스 수
        HashSet<Integer>[] after = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i++) {
            after[i] = new HashSet<>();
        }

        for (int[] pre : prerequisites) {
            indegree[pre[0]]++;
            after[pre[1]].add(pre[0]);
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) queue.add(i);
        }

        int idx = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            ans[idx++] = cur;

            Iterator<Integer> it = after[cur].iterator();
            while (it.hasNext()) {
                int course = it.next();

                indegree[course]--;
                if (indegree[course] == 0) queue.add(course);
            }
        }

        if (idx != numCourses) return new int[0];
        return ans;
    }
}
