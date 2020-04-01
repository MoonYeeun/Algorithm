package boj;

import java.util.Scanner;

// 백준 1976 여행가자
// 유니온파인드
public class Back_1976 {
    static int[][] city;
    static int[] root;
    static int[] plan;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 도시의 수
        int m = sc.nextInt(); // 여행 계획

        city = new int[n+1][n+1];
        root = new int[n+1];
        plan = new int[m];

        for(int i = 0 ; i <= n ; i++) {
            root[i] = i;
        }
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                city[i][j] = sc.nextInt();

                if(city[i][j] == 1)
                    union(i, j);
            }
        }
        boolean flag = true;
        int root;
        // 여행 계획
        for(int i = 0 ; i < m ; i++) {
            plan[i] = sc.nextInt();

            root = find(plan[0]);
            if(root != find(i))
                flag = false;

        }
        if(!flag) System.out.println("NO");
        else System.out.println("YES");
    }
    public static int find(int x) {
        if(root[x] == x)
            return x;
        return find(root[x]);
    }
    public static void union(int x, int y) {
        // 각 원소가 속한 트리의 루트 노드 찾음
        x = find(x);
        y = find(y);

        root[y] = x;
    }
}
