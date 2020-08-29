package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 2170 선긋기
// 라인스위핑
public class Back_2170 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        ArrayList<pair> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            list.add(new pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(list);

        long answer = 0;
        int curStart = -1000000000;
        int curEnd = -1000000000;
        for (pair p : list) {
            // 새로운 범위
            if (p.start > curEnd) {
                answer += curEnd - curStart;
                curStart = p.start;
                curEnd = p.end;
            }
            // 범위 겹치는 경우
            if (p.end > curEnd) curEnd = p.end;
        }
        answer += curEnd - curStart; // 마지막 범위
        System.out.println(answer);
    }

    static class pair implements Comparable<pair> {
        int start, end;

        pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(pair pair) {
            if (this.start == pair.start) return this.end - pair.end;
            return this.start - pair.start;
        }
    }
}
