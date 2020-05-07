package programmers;

import java.util.*;

// 2020 카카오 외벽점검
// 1. 각 외벽 취약지점 선형으로 나열 (시작 위치 다르게)
// 2. 최소인원부터 최대인원까지 외벽 점검할 친구 구하기 (순열)
public class Programmers_checkOuterWall {
    static ArrayList<Integer> list;
    static int len;
    static int[][] loc;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) {
//        int n = 12;
//        int[] weak = {1, 5, 6, 10};
//        int[] dist = {1, 2, 3, 4};
        int n = 30;
        int[] weak = {0, 3, 11, 21};
        int[] dist = {10, 4};

        len = weak.length;
        // 각 외벽 취약 지점
        loc = new int[len][len];
        for(int i = 0 ; i < len ; i++) {
            for(int j = i ; j < i + len ; j++) {
                loc[i][(j-i)] = j >= len ? weak[j % len] + n : weak[j];
            }
        }
        // 최소 인원으로 부터 외벽 점검 가능한지 확인
        for(int i = 1 ; i <= dist.length ; i++) {
            list = new ArrayList<>();
            per(i, dist, 0, 0);
        }
        System.out.println(answer);
    }
    static void per(int cnt, int[] dist, int select, int bit) {
        if(cnt == select) {
            for(int i = 0 ; i < len ; i++) {
                int start = 0; // 시작점
                int dis = 0; // 기준 취약점 위치
                int idx = 0; // 친구 투입

                while (dis < len) {
                    if(idx >= list.size()) break;
                    // 점검 불가능 한 경우
                    if(loc[i][dis] - loc[i][start] + 1 > list.get(idx)) {
                        idx++;
                        start = dis;
                        continue;
                    }
                    dis++;
                }
                // 모두 점검 가능한 경우
                if(dis >= len) {
                    answer = Math.min(answer, list.size());
                    break;
                }
            }
            return;
        }
        for(int i = dist.length -1 ; i >= 0 ; i--) {
            if((bit & (1<<i)) == (1<<i)) continue;

            list.add(dist[i]);
            per(cnt, dist, select + 1, bit | (1<<i));
            list.remove(list.size()-1);
        }
    }
}
