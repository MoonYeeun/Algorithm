package boj;

import java.util.*;

// 백준 2458 키 순서
// 플로이드 와샬
public class Back_2458 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] student = new int[n+1][n+1];

        for(int i = 1 ; i <= m ; i++) {
            int st1 = sc.nextInt();
            int st2 = sc.nextInt();

            student[st1][st2] = -1;
            student[st2][st1] = 1;
        }

        for(int k = 1; k <= n ; k++) {
            for(int i = 1; i <= n ; i++) {
                for(int j = 1 ; j <= n ; j++) {
                    // i > k > j => i > j
                    if (student[i][k] == 1 && student[k][j] == 1)
                        student[i][j] = 1;
                    // i < k < j => i < j
                    else if (student[i][k] == -1 && student[k][j] == -1)
                        student[i][j] = -1;
                }
            }
        }

        int ans = 0;
        for(int i = 1; i <= n ; i++) {
            boolean flag = true;

            for(int j = 1; j <= n ; j++) {
                if(i == j) continue;
                if(student[i][j] == 0) flag = false;
            }

            if(flag) ans++;
        }

        System.out.println(ans);
    }
}
