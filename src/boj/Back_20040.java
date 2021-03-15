package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 20040 사이클게임
// 유니온파인드
public class Back_20040 {
    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int ans = 0;

        root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int s1 = Integer.parseInt(st.nextToken());
            int s2 = Integer.parseInt(st.nextToken());

            if (find(s1) == find(s2)) {
                ans = i;
                break;
            }
            union(s1, s2);
        }
        System.out.println(ans);
    }

    static int find(int a) {
        if (root[a] == a) return a;
        return root[a] = find(root[a]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        root[b] = a;
    }
}
