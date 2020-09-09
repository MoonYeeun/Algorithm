package programmers;

import java.util.*;

// 2020 카카오 블라인드 채용 자물쇠와 열쇠
public class Programmers_keyAndlock {
    static int[][] map;
    static int key_len, lock_len, total;

    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};

        boolean result = solution(key, lock);
        System.out.println(result);
    }

    static public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;

        key_len = key[0].length;
        lock_len = lock[0].length;
        total = 2 * key_len + lock_len - 2;

        map = new int[total][total];
        for (int i = 0; i < total; i++) {
            Arrays.fill(map[i], 1); // 전체 영역 돌기로 초기화
        }
        makeMap(lock);

        // 0 90 180 270 key 회전하면서 비교
        for (int i = 0; i < 4; i++) {
            key = rotateKey(key);

            if (move(key)) {
                answer = true;
                break;
            }
        }
        return answer;
    }

    static void makeMap(int[][] lock) {
        int start = key_len - 1;

        for (int i = 0; i < lock_len; i++) {
            for (int j = 0; j < lock_len; j++) {
                map[i + start][j + start] = lock[i][j];
            }
        }
    }

    static int[][] rotateKey(int[][] key) {
        int[][] arr = new int[key_len][key_len];

        for (int i = 0; i < key_len; i++) {
            int idx = 0;
            for (int j = key_len - 1; j >= 0; j--) {
                arr[i][idx++] = key[j][i];
            }
        }
        return arr;
    }

    static boolean move(int[][] key) {
        for (int i = 0; i <= total - key_len; i++) {
            for (int j = 0; j <= total - key_len; j++) {
                int[][] arr = openMap(i, j, key);
                if (check(arr)) return true;
            }
        }
        return false;
    }

    static int[][] openMap(int x, int y, int[][] key) {
        int[][] arr = copy(map);
        int curX = x;
        int curY = y;

        for (int i = 0; i < key_len; i++) {
            for (int j = 0; j < key_len; j++) {
                arr[curX][curY] = arr[curX][curY] != key[i][j] ? 1 : 0;
                curY++;
            }
            curX++;
            curY = y;
        }
        return arr;
    }

    static boolean check(int[][] map) {
        int start = key_len - 1;

        for (int i = start; i < start + lock_len; i++) {
            for (int j = start; j < start + lock_len; j++) {
                if (map[i][j] == 0) return false; // 덜 채워진 경우
            }
        }
        return true;
    }

    static int[][] copy(int[][] map) {
        int[][] arr = new int[total][total];

        for (int i = 0; i < total; i++) {
            arr[i] = Arrays.copyOf(map[i], total);
        }
        return arr;
    }
}
