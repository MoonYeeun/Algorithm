package boj;

import java.util.*;

// 백준 14226 이모티콘

public class Back_14226 {
    static int s;
    static Queue<pair> queue = new LinkedList<>();
    static boolean[][] visit;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        s = sc.nextInt();

        queue.add(new pair(1, 0, 0));
        visit = new boolean[10000][10000]; // 이모티콘 개수, 클립보드 개수
        visit[1][0] = true;
        bfs();
    }
    static void bfs() {
        while (!queue.isEmpty()) {
            pair p = queue.poll();

            if(p.emo == s) {
                System.out.println(p.cnt);
                break;
            }

            // 클립보드로 복사
            if(!visit[p.emo][p.emo]) {
                visit[p.emo][p.emo] = true;
                queue.add(new pair(p.emo, p.emo, p.cnt + 1));
            }
            // 클립보드가 비어있지 않은경우, 화면 붙여넣기
            int newEmo = p.emo + p.clip;

            if(p.clip > 0 && !visit[newEmo][p.clip]) {
                visit[newEmo][p.clip] = true;
                queue.add(new pair(newEmo, p.clip, p.cnt + 1));
            }
            // 이모티콘 삭제
            if(p.emo -1 >= 2 && !visit[p.emo-1][p.emo]) {
                visit[p.emo-1][p.clip] = true;
                queue.add(new pair(p.emo-1, p.clip, p.cnt + 1));
            }
        }
    }
    static class pair {
        int emo, clip, cnt;

        pair(int emo, int clip, int cnt) {
            this.emo = emo;
            this.clip = clip;
            this.cnt = cnt;
        }
    }
}
