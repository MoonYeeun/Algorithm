package LeetCode;

import java.util.*;

// ⭐️ 1353. Maximum Number of Events That Can Be Attended
public class LC1353_Maximum_Number_of_Events_That_Can_Be_Attended {
    public static void main(String[] args) {
        int[][] events = {{1, 4}, {4, 4}, {2, 2}, {3, 4}, {1, 1}};
        System.out.println(maxEvents(events));
    }

    public static int maxEvents(int[][] events) {
        int ans = 0;
        Arrays.sort(events, (e1, e2) -> Integer.compare(e1[0], e2[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int idx = 0;
        int len = events.length;
        for (int i = 1; i <= 100000; i++) {
            // 이벤트 시작일이 현재 일자인 경우 pq에 다 담기
            while (idx < len && events[idx][0] == i) {
                pq.add(events[idx++][1]); // 종료일 넣기
            }
            // 이벤트 종료일이 현재 날짜보다 작은 경우 -> 참가할 수 없으므로 제거
            while (!pq.isEmpty() && pq.peek() < i) {
                pq.poll();
            }
            // 참가할 수 있는 이벤트 중 가장 종료일이 빠른 것 선택
            if (!pq.isEmpty()) {
                pq.poll();
                ans++;
            }
        }
        return ans;
    }
}
