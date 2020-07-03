package boj;

import java.util.*;

// 백준 9466 텀프로젝트
// 1. 각 학생을 방문했는지 체크
// 2. 방문할 때마다 몇번째로 방문했는지 체크
// 3. 첫번째 방문자가 누군지 확인
public class Back_9466 {
    static int[] student;
    static int[] visit;
    static int[] cycle;
    static int ans;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            student = new int[n+1];
            visit = new int[n+1]; // 방문체크
            cycle = new int[n+1]; // 사이클 확정

            for(int i = 1 ; i <= n ; i++) {
                student[i] = sc.nextInt();
            }

            int ans = 0;
            for(int i = 1 ; i <= n ; i++) {
                if(visit[i] != 0) continue; // 이미 방문했거나 사이클 형성된 경우

                ans += dfs(i, i, 1);
            }
            System.out.println(n - ans);
        }
    }
    static int dfs(int s, int cur, int cnt) {
        visit[cur] = cnt;
        cycle[cur] = s;

        int next = student[cur];

        // 이미 방문한적 있는 경우
        if(visit[next] != 0) {
            // 시작점 같은경우
            if(visit[next] != 0 && s == cycle[next])
                return cnt - visit[next] + 1;

            // 시작점 다른경우
            if(visit[next] != 0 && s != cycle[next]) return 0;
        }

        return dfs(s, next, cnt + 1);
    }
    // cylce 찾는법 ver 2
    // 방문한적 있으면 해당 시작점부터 방문한 점 개수 증가 -> 사이클이므로
//    static void dfs(int cur) {
//        visit[cur] = true;
//
//        int next = student[cur];
//
//        if(!visit[next]) dfs(next);
//
//        else if(!done[next]) {
//            for(int i = next ; i != cur ; i = student[i]) {
//                cnt++;
//            }
//            cnt++; // 자기자신
//        }
//
//        done[cur] = true;
//    }
}
