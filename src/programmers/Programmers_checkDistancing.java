package programmers;

// 프로그래머스 2021 카카오 인턴십 - 거리두기 확인하기
public class Programmers_checkDistancing {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static String[][] map;
    static boolean[][] visit;

    public static void main(String[] args) {
        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};

        int[] result = solution(places);
        for (int i : result) {
            System.out.println(i);
        }
    }

    public static int[] solution(String[][] places) {
        int[] answer = new int[5];

        for (int i = 0; i < 5; i++) {
            map = new String[5][5];
            visit = new boolean[5][5];
            boolean flag = true;

            for (int j = 0; j < 5; j++) {
                map[j] = places[i][j].split("");
            }

            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (!map[j][k].equals("P")) continue;

                    visit[j][k] = true;
                    if (check(j, k, 0)) continue;

                    flag = false;
                    break;
                }
                if (!flag) break;
            }
            answer[i] = flag ? 1 : 0;
        }
        return answer;
    }

    public static boolean check(int x, int y, int dis) {
        if (dis == 2) return !map[x][y].equals("P");

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
            if (visit[nx][ny]) continue;

            visit[nx][ny] = true;

            if (map[nx][ny].equals("X")) continue;
            if (map[nx][ny].equals("P") || !check(nx, ny, dis + 1)) return false;
        }
        return true;
    }
}
