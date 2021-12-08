package LeetCode;

import java.util.*;

// 207. Course Schedule
// 위상정렬
public class LC207_Course_Schedule {
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};

        System.out.println(canFinish(numCourses, prerequisites));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] list = new ArrayList[numCourses];
        int[] check = new int[numCourses]; // 간선 체크
        boolean[] visit = new boolean[numCourses]; // 방문 체크
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            list[i] = new ArrayList<>();
        }

        for (int[] pre : prerequisites) {
            list[pre[1]].add(pre[0]);
            check[pre[0]]++;
        }

        for (int i = 0; i < numCourses; i++) {
            if (check[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (visit[cur]) continue;

            visit[cur] = true;
            for (int next : list[cur]) {
                check[next]--;
                if (check[next] == 0) queue.add(next);
            }
        }

        for (boolean cur : visit) {
            if (!cur) return false;
        }
        return true;
    }
}
