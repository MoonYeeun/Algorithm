package LeetCode;

import java.util.*;

// 투포인터
public class LC56_Merge_Intervals {
    public static void main(String[] args) {
        //int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] intervals = {{1, 4}, {4, 5}};
        int[][] result = merge(intervals);

        for(int i = 0 ; i < result.length ; i++) {
            System.out.println(result[i][0] + " " + result[i][1]);
        }
    }
    static public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (v1, v2) -> {
            if(v1[0] == v2[0]) return Integer.compare(v1[1], v2[1]);
            else return Integer.compare(v1[0], v2[0]);
        });

        Queue<pair> queue = new LinkedList<>();
        if(intervals.length > 0) {
            int s = 0; int e = 0;
            int curS = intervals[0][0]; int curE = intervals[0][1];

            while (e < intervals.length) {
                // 범위 내에 있는 경우
                if(curS <= intervals[e][0] && curE >= intervals[e][0]) {
                    curE = intervals[e][1] > curE ? intervals[e][1] : curE;
                }
                else {
                    queue.add(new pair(curS, curE));
                    s = e;
                    curS = intervals[s][0];
                    curE = intervals[s][1];
                }
                e++;

                if(e == intervals.length) {
                    queue.add(new pair(curS, curE));
                }
            }
        }
        int idx = 0;
        int[][] answer = new int[queue.size()][2];
        while (!queue.isEmpty()) {
            pair p = queue.poll();

            answer[idx][0] = p.s;
            answer[idx++][1] = p.e;
        }
        return answer;
    }
    static class pair {
        int s, e;

        pair(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }
}
