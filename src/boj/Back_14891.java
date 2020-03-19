package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 백준 14891 톱니바퀴
public class Back_14891 {
    static int[][] wheel; // 톱니바퀴 정보
    static boolean[] visit;
    static Queue<pair> queue = new LinkedList<>(); // 회전 정보 담을 큐
    static Queue<pair> turn; // 이동할 톱니바퀴
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        wheel = new int[5][8];

        for(int i = 1 ; i <= 4 ; i++) {
            String str = sc.nextLine();
            for(int j = 0 ; j < 8 ; j++) {
                wheel[i][j] = str.charAt(j) - '0';
            }
        }
        int k = sc.nextInt();
        for(int i = 0 ; i < k ; i++) {
            int idx = sc.nextInt();
            int dir = sc.nextInt();
            queue.add(new pair(idx, dir));
        }
        while(!queue.isEmpty()) {
            visit = new boolean[5];
            turn = new LinkedList<>();

            pair pair = queue.poll();
            turn.add(new pair(pair.wheel_idx, pair.dir));
            check(pair.wheel_idx, pair.dir); // 이동할 톱니바퀴 체크
            move(); // 톱니바퀴 이동
        }
        // 점수 계산
        int answer = 0;
        for(int i = 1 ; i <= 4 ; i++) {
            answer += score(i);
        }
        System.out.print(answer);
    }
    static void check(int idx, int dir) {
        visit[idx] = true;
        if(idx == 2 || idx == 3) {
            // 양 옆 톱니바퀴 확인
            if(!visit[idx-1] &&
                    (wheel[idx][6] != wheel[idx-1][2])) {
                turn.add(new pair(idx-1, dir * -1)); // 이동시킬 목록에 집어 넣기
                check(idx-1, dir * -1);
            }
            if(!visit[idx+1] &&
                    (wheel[idx][2] != wheel[idx+1][6])) {
                turn.add(new pair(idx+1, dir * -1)); // 이동시킬 목록에 집어 넣기
                check(idx+1, dir * -1);
            }
        }
        if(idx == 1) {
            if(!visit[idx+1] &&
                    (wheel[idx][2] != wheel[idx+1][6])) {
                turn.add(new pair(idx+1, dir * -1)); // 이동시킬 목록에 집어 넣기
                check(idx+1, dir * -1);
            }
        }
        if(idx == 4) {
            if(!visit[idx-1] &&
                    (wheel[idx][6] != wheel[idx-1][2])) {
                turn.add(new pair(idx-1, dir * -1)); // 이동시킬 목록에 집어 넣기
                check(idx-1, dir * -1);
            }
        }
    }
    // 톱니바퀴 이동
    static void move() {
        while(!turn.isEmpty()){
            pair pair = turn.poll();
            int[] temp = new int[8];
            for(int i = 0 ; i < 8 ; i++) {
                temp[i] = wheel[pair.wheel_idx][i];
            }
            for(int i = 0 ; i < 8 ; i++) {
                wheel[pair.wheel_idx][i] = temp[(i+8-pair.dir) % 8];
            }
        }
    }
    // 점수 계산
    static int score(int idx) {
        return wheel[idx][0] == 0 ? 0 : 1<<(idx-1);
    }
    static class pair {
        int wheel_idx; // 톱니바퀴 번호
        int dir; // 방향

        pair(int wheel_idx, int dir) {
            this.wheel_idx = wheel_idx;
            this.dir = dir;
        }
    }
}
