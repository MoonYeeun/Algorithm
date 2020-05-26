package boj;

import java.util.*;

// 백준 2026 소풍
// 1부터 N까지 각각 친구될 수 있는지 확인
public class Back_2026 {
    static int k, n, f;
    static boolean end;
    static int[][] friend;
    static ArrayList<Integer> list;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        n = sc.nextInt();
        f = sc.nextInt();

        friend = new int[n+1][n+1];
        for(int i = 0 ; i < f ; i++) {
            int f1 = sc.nextInt();
            int f2 = sc.nextInt();

            friend[f1][f2] = friend[f2][f1] = 1;
        }
        for(int i = 1 ; i <= n ; i++) {
            list = new ArrayList<>();
            list.add(i);
            check(i);

            if(end) break;
        }
        if(!end) System.out.println(-1);
    }
    static void check(int s) {
        if(end) return;
        // 정답
        if(list.size() == k) {
            end = true;
            for(int i : list) {
                System.out.println(i);
            }
            return;
        }
        for(int i = s + 1 ; i <= n ; i++) {
            boolean flag = true;

            for(int j : list) {
                if(friend[i][j] != 1) flag = false;
            }
            if(flag) {
                list.add(i);
                check(i);
                list.remove(list.size()-1);
            }
        }
    }
}
