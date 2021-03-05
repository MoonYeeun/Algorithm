package boj;

import java.util.*;

// 백준 17825 주사위윷놀이
// 1. 윷놀이판 세팅 & 방향 바뀌는 시점 체크
// 2. 위치별 점수 세팅
// 3. 말 움직이기 (완탐)
public class Back_17825 {
    static int[] map;
    static int[] dice;
    static int[] dir;
    static int[] check;
    static int[] score;
    static boolean[] visit;
    static int ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        map = new int[33]; // 윷놀이 판
        dir = new int[33]; // 방향 바뀌는 시점 체크
        score = new int[33]; // 각 위치별 점수
        visit = new boolean[33]; // 방문 체크
        check = new int[5]; // 말의 위치 체크
        dice = new int[11]; // 각 턴의 주사위 수

        for (int i = 0; i < 10; i++) {
            dice[i] = sc.nextInt();
        }

        setMap(); // 윷놀이판 셋팅
        setSCore(); // 점수판 셋팅
        move(0, 0);
        System.out.println(ans);
    }

    public static void setMap() {
        for (int i = 0; i <= 26; i++) {
            if (i == 21) map[i] = i;
            else map[i] = i + 1;
        }
        map[27] = 20;
        map[28] = 29;
        map[29] = 30;
        map[30] = 25;
        map[31] = 32;
        map[32] = 25;

        dir[5] = 22;
        dir[10] = 31;
        dir[15] = 28;
    }

    public static void setSCore() {
        for (int i = 1; i <= 20; i++) {
            score[i] = 2 * i;
        }
        score[22] = 13;
        score[23] = 16;
        score[24] = 19;
        score[25] = 25;
        score[26] = 30;
        score[27] = 35;
        score[28] = 28;
        score[29] = 27;
        score[30] = 26;
        score[31] = 22;
        score[32] = 24;
    }

    public static void move(int turn, int total) {
        if (turn == 10) {
            ans = Math.max(ans, total);
            return;
        }

        for (int i = 1; i <= 4; i++) {
            if (check[i] == 21) continue; // 현재 말 도착점인 경우 -> 움직일 수 x

            int cnt = dice[turn] - 1;
            int cur = check[i];
            int next = dir[check[i]] != 0 ? dir[check[i]] : map[check[i]];

            while (cnt-- > 0) {
                next = map[next];
            }

            // 이동하려는 곳에 다른 말 있는 경우 -> 움직일 수 x
            if (next != 21 && visit[next]) continue;

            check[i] = next;
            visit[cur] = false;
            visit[next] = true;

            move(turn + 1, total + score[next]);

            check[i] = cur;
            visit[cur] = true;
            visit[next] = false;
        }
    }
}
