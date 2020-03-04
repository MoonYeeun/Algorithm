package not_solve_myself;

import java.util.Scanner;
// 백준 2098 외판원 순회
/*
방문한 도시 = 비트 마스크로 표현
모든 도시를 방문한 경우. (모든 비트의 값이 1 인지 판단)
이미 방문한 도시 여부. (i번째 비트의 값이 1 인지 판단)
다음 도시를 방문했을 경우. (i번째 비트의 값을 1로 변경)
*/
public class boj_2098 {
    static int n;
    static int INF = Integer.MAX_VALUE - 1000000;
    static int[][] w, dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); //도시의 수
        w = new int[n][n]; //도시로 가기 위한 비용
        dp = new int[n][1 << n];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                w[i][j] = sc.nextInt();
            }
        }
        System.out.println(tsp(0,1));

    }
    static int tsp(int current, int visited){
        //모든 도시 방문한 경우
        if (visited == ((1<<n) - 1)){
            if(w[current][0] == 0)
                return INF;
            return w[current][0];
        } // 이미 방문한 적이 있는 경우
        if (dp[current][visited] != 0)
            return dp[current][visited];

        dp[current][visited] = INF;
        for(int i = 0 ; i < n ; i++) {
            int next = 1<<i;
            // 갈 수 없거나 , 이미 방문한 경우 건너뛰기
            if(w[current][i] == 0 || (visited & next) != 0) continue;
            // 현재 도시(current)에서 방문한 도시들(visited)이 주어질 때, 도시 전체를 순회한 최소 비용
            dp[current][visited] = Math.min(dp[current][visited], tsp(i, visited | next) + w[current][i]);
        }
        return dp[current][visited];
    }
}
