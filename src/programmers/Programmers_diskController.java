package programmers;
import java.util.*;

// 프로그래머스 디스크 컨트롤러
// 요청 시간이 작은 것 부터 정렬 - 시간 같으면 처리 시간 작은 순
// 해당 작업이 끝나기 전에 들어온 작업들 우선순위 큐에 넣음 ( 처리 시간 작은 것이 우선순위)
// 모든 작업이 끝난 후에 새로 들어온 작업 있을 경우 처리
public class Programmers_diskController {
    public static void main(String[] args) {
        //int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
        //int[][] jobs = {{1, 11},{1, 9}, {2, 15}, {10, 1}};
        //int[][] jobs = {{0, 9}, {0, 4}, {0, 5}, {0, 7}, {0, 3}};
        int[][] jobs = {{0,3},{0,1},{5,7}};

        // 요청 들어온 시간 기준으로 정렬
        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                if(ints[0] == t1[0])
                    return ints[1] - t1[1];
                return ints[0] - t1[0];
            }
        });

        PriorityQueue<pair> pq = new PriorityQueue<>();

        int cur = jobs[0][0]; // 현재 시간
        int wait = 0; // 작업 시작시간 - 들어온 시간
        int answer = 0;
        int idx = 1;
        pq.add(new pair(jobs[0][0], jobs[0][1]));

        while (idx < jobs.length || !pq.isEmpty()) {
            pair pair = pq.poll();

            wait = cur - pair.order;
            answer += wait + pair.time;
            cur += pair.time;

            for(int i = idx; i < jobs.length ; i++) {
                if(jobs[i][0] <= cur) {
                    pq.add(new pair(jobs[i][0], jobs[i][1]));
                    idx++;
                }
            }
            // 이전 작업이 다 끝난 후 새로운 작업이 들어오는 경우
            if(pq.isEmpty() && idx < jobs.length) {
                pq.add(new pair(jobs[idx][0], jobs[idx][1]));
                cur = jobs[idx][0];
                idx++;
            }
        }
        System.out.println(answer / jobs.length);
    }
    static class pair implements Comparable<pair> {
        int order, time;

        pair(int order, int time) {
            this.order = order;
            this.time = time;
        }

        @Override
        public int compareTo(pair pair) {
            if(this.time == pair.time)
                return this.order - pair.order;
            return this.time - pair.time;
        }
    }
}
