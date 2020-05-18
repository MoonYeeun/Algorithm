package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 1756 피자굽기
public class Back_1756 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] oven = new int[d+1];
        int[] pizza = new int[n];
        st = new StringTokenizer(br.readLine());
        // 각 오븐에 넣을 수 있는 최소 크기 설정
        oven[0] = Integer.MAX_VALUE;
        for(int i = 1 ; i <= d ; i++) {
            int num = Integer.parseInt(st.nextToken());
            oven[i] = Math.min(num, oven[i-1]);
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < n ; i++) {
            pizza[i] = Integer.parseInt(st.nextToken());
        }
        int idx = 0;
        for(int i = d ; i >= 1 ; i--) {
            if(oven[i] >= pizza[idx]) idx++;
            if(idx >= n) {
                System.out.println(i);
                break;
            }
        }
        // 오븐에 들어갈 수 없는 경우
        if(idx < n) System.out.println(0);
    }
}
