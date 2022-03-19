package programmers;

import java.util.*;

// 2022 카카오 양궁대회
public class Programmers_Archery_tournament {
    static int result = Integer.MIN_VALUE;
    static int[] ans;
    static int[] appeachScore;

    public static void main(String[] args) {
        //int n = 5;
        int n = 1;
        //int[] info = {2,1,1,1,0,0,0,0,0,0,0};
        int[] info = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] result = solution(n, info);
        Arrays.stream(result).forEach(e -> System.out.println(e));
    }

    public static int[] solution(int n, int[] info) {
        appeachScore = info;
        shoot(n, 10, n, new int[11]);
        return result == Integer.MIN_VALUE ? new int[]{-1} : ans;
    }

    public static void shoot(int n, int idx, int total, int[] infoRyan) {
        if (idx == -1) {
            if (total != 0) return;

            int score = calScore(infoRyan, appeachScore);
            if (score > 0 && score > result) {
                result = score;
                ans = Arrays.copyOf(infoRyan, infoRyan.length);
            } else if (score == result && resetShoot(ans, infoRyan)) ans = Arrays.copyOf(infoRyan, infoRyan.length);
            return;
        }
        for (int i = 0; i <= n; i++) {
            infoRyan[idx] = i;
            shoot(n - i, idx - 1, total - i, infoRyan);
            infoRyan[idx] = 0;
        }
    }

    public static int calScore(int[] infoRyan, int[] infoAppeach) {
        int appeach = 0;
        int ryan = 0;

        for (int i = 0; i < 11; i++) {
            if (infoRyan[i] > infoAppeach[i]) ryan += (10 - i);
            else if (infoAppeach[i] != 0 && infoAppeach[i] >= infoRyan[i]) appeach += (10 - i);
        }
        return ryan - appeach;
    }

    public static boolean resetShoot(int[] prev, int[] cur) {
        for (int i = 10; i >= 0; i--) {
            if (prev[i] == cur[i]) continue;
            return prev[i] <= cur[i];
        }
        return true;
    }
}
