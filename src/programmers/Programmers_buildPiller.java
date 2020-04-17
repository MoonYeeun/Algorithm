package programmers;

import java.util.*;

// 2020 카카오 기둥과 보 설치
// 삭제 시 임의로 삭제하고 설치할 수 있는지 확인하는 걸로 삭제 가능한지 판단 !
public class Programmers_buildPiller {
    static int[][][] map;
    static ArrayList<pair> list = new ArrayList<>();
    static int size;
    public static void main(String[] args) {
        int n = 5;
//        int[][] build_frame = {
//                {1,0,0,1},
//                {1,1,1,1},
//                {2,1,0,1},
//                {2,2,1,1},
//                {5,0,0,1},
//                {5,1,0,1},
//                {4,2,1,1},
//                {3,2,1,1}
//        };
        int[][] build_frame = {
                {0,0,0,1},
                {2,0,0,1},
                {4,0,0,1},
                {0,1,1,1},
                {1,1,1,1},
                {2,1,1,1},
                {3,1,1,1},
                {2,0,0,0},
                {1,1,1,0},
                {2,2,0,1}
        };

        size = n;
        map = new int[n+3][n+3][2];
        for(int[] frames : build_frame) {
            int x = frames[0] + 1; // 가로
            int y = frames[1] + 1; // 세로
            // 삭제
            if(frames[3] == 0) {
                remove(x, y, frames[2]);
            }
            // 설치
            if(frames[3] == 1) {
                if(!make(x, y, frames[2])) continue;
                list.add(new pair(x, y, frames[2]));
                map[x][y][frames[2]] = 1;
            }
        }
        int[][] answer = new int[list.size()][3];
        int idx = 0;
        Collections.sort(list);
        for(pair pair : list) {
            answer[idx][0] = pair.x - 1;
            answer[idx][1] = pair.y - 1;
            answer[idx][2] = pair.a;
            idx++;
        }
    }
    static boolean make(int x, int y, int type) {
        // 기둥
        if(type == 0) {
            // 바닥이거나 기둥 위거나 보의 끝이거나
            if(y == 1 || map[x][y-1][0] == 1 || map[x][y][1] == 1 || map[x-1][y][1] == 1)
                return true;
        }
        // 보
        if(type == 1) {
            // 기둥 위이거나 양 끝이 보와 연결된 경우
            if(map[x][y-1][0] == 1 || map[x+1][y-1][0] == 1 || (map[x-1][y][1] == 1 && map[x+1][y][1] == 1))
                return true;
        }
        return false;
    }
    static void remove(int x, int y, int type) {
        boolean flag = true;
        // 삭제
        if(type == 0) map[x][y][0] = 0;
        else map[x][y][1] = 0;

        loop:
        for(int i = 1 ; i <= size+1 ; i++) {
            for(int j = 1 ; j <= size+1; j++) {
                if(map[i][j][0] == 1 && !make(i, j, 0)) {
                    flag = false;
                    break loop;
                }
                if(map[i][j][1] == 1 && !make(i, j, 1)) {
                    flag = false;
                    break loop;
                }
            }
        }

        if(flag) list.remove(new pair(x, y, type));
        else {
            if(type == 0) map[x][y][0] = 1;
            else map[x][y][1] = 1;
        }
    }
    static class pair implements Comparable<pair>{
        int x, y, a;

        pair(int x, int y, int a) {
            this.x = x;
            this.y = y;
            this.a = a;
        }

        @Override
        public int compareTo(pair pair) {
            if(this.x == pair.x) {
                if(this.y == pair.y)
                    return this.a - pair.a;
                return this.y - pair.y;
            }
            return this.x - pair.x;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof pair) {
                pair p = (pair)obj;
                return p.x == x && p.y == y && p.a == a;
            }
            return false;
        }
    }
}
