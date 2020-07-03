package boj;

import java.util.*;

// 백준 9466 텀프로젝트
public class Back_9466 {
    static int[] student;
    static boolean[] visit;
    static int cnt;
    static HashSet<Integer> set;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            cnt = 0;
            student = new int[n+1];
            set = new HashSet<>();

            for(int i = 1 ; i <= n ; i++) {
                student[i] = sc.nextInt();
            }

            for(int i = 1 ; i <= n ; i++) {
                if(set.contains(i)) continue;

                visit = new boolean[n+1];
                dfs(i, i); // 성립한 팀 구하기
            }
            System.out.println(n - cnt);
        }
    }
    static boolean dfs(int s, int cur) {
        if(s == student[cur]) {
            set.add(cur);
            cnt++;
            return true;
        }
        if(visit[cur]) return false;

        visit[cur] = true;
        if(dfs(s, student[cur])) {
            set.add(cur);
            cnt++;
            return true;
        }

        return false;
    }
}
