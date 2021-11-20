package programmers;

// 프로그래머스 위클리챌린지 피로도
// 완탐
public class Programmers_tired {
    static boolean[] visit;
    static int[][] d;

    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};

        System.out.println(solution(k, dungeons));
    }

    public static int solution(int k, int[][] dungeons) {
        visit = new boolean[dungeons.length];
        d = dungeons;

        int ans = dfs(k, 0);
        return ans;
    }

    public static int dfs(int tired, int cnt) {
        int result = cnt;

        for (int i = 0; i < d.length; i++) {
            if (visit[i] || tired < d[i][0]) continue;

            visit[i] = true;
            result = Math.max(result, dfs(tired - d[i][1], cnt + 1));
            visit[i] = false;
        }
        return result;
    }
}
