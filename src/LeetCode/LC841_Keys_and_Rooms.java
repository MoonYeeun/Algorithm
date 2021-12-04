package LeetCode;

import java.util.*;

// 841. Keys and Rooms
// bfs
public class LC841_Keys_and_Rooms {
    static boolean[] visit;
    static Queue<Integer> queue;

    public static void main(String[] args) {

    }

    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        visit = new boolean[rooms.size()];
        queue = new LinkedList<>();

        // 0번째 룸 방문
        visit[0] = true;
        for (int key : rooms.get(0)) {
            queue.add(key);
        }
        //  갈 수 있는 곳 가보기
        bfs(rooms);

        for (boolean flag : visit) {
            if (!flag) return false;
        }
        return true;
    }

    public static void bfs(List<List<Integer>> rooms) {
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (visit[cur]) continue;

            visit[cur] = true;

            for (int key : rooms.get(cur)) {
                if (visit[key]) continue;
                queue.add(key);
            }
        }
    }
}
