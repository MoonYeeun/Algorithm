package boj;

import java.util.*;

// ⭐️ 백준 3015 오아시스 재결합
// 스택
public class Back_3015 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Stack<pair> st = new Stack<>();

        long ans = 0;
        for (int i = 0; i < n; i++) {
            int cur = sc.nextInt();
            pair p = new pair(cur, 1); // 키, 해당 키를 가진 사람 수

            // 들어온 값이 기존 사람보다 같거나 큰 경우 -> 기존 사람이 이룰 수 있는 쌍 거기까지이므로 계산
            while (!st.isEmpty() && st.peek().height <= cur) {
                ans += st.peek().cnt;

                if (st.peek().height == cur) p.cnt += st.peek().cnt;
                st.pop();
            }
            if (!st.isEmpty()) ans++; // 이웃사람 더하기
            st.push(p);
        }
        System.out.println(ans);
    }

    static class pair {
        int height, cnt;

        pair(int height, int cnt) {
            this.height = height;
            this.cnt = cnt;
        }
    }
}
