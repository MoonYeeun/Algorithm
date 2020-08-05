package boj;

import java.io.*;
import java.util.*;

// 백준 17951 흩날리는 시험지 속에서 내 평점이 느껴진거야
// 이분탐색
public class Back_17951 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int max = 0; int min = 0;

        int[] score = new int[n];
        st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < n ; i++) {
            score[i] = Integer.parseInt(st.nextToken());

            max += score[i];
        }

        int ans = 0;
        while (min <= max) {
            int mid = (min + max) / 2; // 받을 수 있는 최소값

            int cnt = 0; // 만들어진 그룹수
            int total = Integer.MAX_VALUE;
            int cur = 0; // 현재 점수

            for(int i = 0 ; i < n ; i++) {
                cur += score[i];

                if(cur >= mid) {
                    total = Math.min(total, cur);
                    cur = 0;
                    cnt++;
                }
            }
            // 만들어진 그룹 > 나눌 그룹 수
            if(cnt >= k) {
                ans = Math.max(ans, total);
                min = mid + 1;
            } else max = mid -1;
        }
        System.out.println(ans);
    }
}
