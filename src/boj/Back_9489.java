package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 9489 사촌
public class Back_9489 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if (n == 0 && k == 0) break;

            int[] arr = new int[n + 1];
            int[] parent = new int[n + 1];

            // 각 노드의 부모 계산
            int p = -1;
            int target = 0;
            arr[0] = -1;
            parent[0] = -1;

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());

                // 같은 부모 아닌 경우
                if (arr[i] != arr[i - 1] + 1) p++;
                if (arr[i] == k) target = i;

                parent[i] = p;
            }

            int total = 0;

            // 사촌 : 타겟과 부모는 다름 조부모는 같음
            for (int i = 1; i <= n; i++) {
                if (parent[target] != parent[i] && parent[parent[target]] == parent[parent[i]]) total++;
            }
            System.out.println(total);
        }
    }
}
