package boj;

import java.util.Scanner;

// 백준 2531 회전초밥
// 슬라이딩 윈도우
public class Back_2531 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int d = sc.nextInt();
        int k = sc.nextInt();
        int c = sc.nextInt();

        int[] map = new int[n]; // 회전초밥 벨트
        int[] check = new int[d+1]; // 회전초밥 종류
        for(int i = 0 ; i < n ; i++) {
            map[i] = sc.nextInt();
        }
        int cnt = 1 ; // 먹을 수 있는 초밥 종류
        int answer = 0;

        check[c]++;
        for(int i = 0 ; i < k ; i++) {
            if(check[map[i]]++ == 0) cnt++;
        }
        for(int i = k ; i < n + k; i++) {
            if(--check[map[i - k]] == 0) cnt--;
            if(check[map[i % n]]++ == 0) cnt++;

            answer = Math.max(cnt, answer);
        }
        System.out.println(answer);
    }
}
