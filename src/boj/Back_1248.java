package boj;

import java.util.ArrayList;
import java.util.Scanner;

// 백준 1248 맞춰봐
public class Back_1248 {
    static int[][] map;
    static ArrayList<Integer> arr;
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        String str = sc.next();
        map = new int[n][n];
        arr = new ArrayList<>();

        int idx = 0;
        for(int i = 0 ; i < n ; i++) {
            for(int j = i ; j < n ; j++) {
                if(str.charAt(idx) == '-')
                    map[i][j] = -1;
                if(str.charAt(idx) == '0')
                    map[i][j] = 0;
                if(str.charAt(idx) == '+')
                    map[i][j] = 1;
                idx++;
            }
        }
        dfs(0);
    }
    static void dfs(int cnt) {
        if(cnt == n) {
            for(int i = 0 ; i < n ; i++) {
                System.out.print(arr.get(i) + " ");
            }
            System.exit(0);
        }
        for(int i = 0 ; i < 21 ; i++) {
            arr.add(i - 10);
            if(check(cnt))
                dfs(cnt + 1);
            arr.remove(cnt);
        }
    }
    static boolean check(int idx) {
        int sum = 0;
        for(int i = idx ; i >= 0 ; i--) {
            sum += arr.get(i);
            if(map[i][idx] == 0 && sum != 0) return false;
            if(map[i][idx] == -1 && sum >= 0) return false;
            if(map[i][idx] == 1 && sum <= 0) return false;
        }
        return true;
    }
}
