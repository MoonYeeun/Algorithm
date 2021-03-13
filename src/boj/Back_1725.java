package boj;

import java.util.*;

// 백준 1725 히스토그램
// 스택 (스택에 저장된 높이보다 현재 높이가 클 경우만 스택에 넣기)
public class Back_1725 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long ans = 0;

        Stack<pair> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            int h = sc.nextInt();
            int left = i; // 사각형 계산할 시작 인덱스

            while (!st.empty() && st.peek().height > h) {
                pair p = st.pop();
                ans = Math.max(ans, (i - p.idx) * p.height);
                left = p.idx;
            }
            if (st.empty() || st.peek().height != h) st.push(new pair(left, h));
        }

        while (!st.empty()) {
            pair p = st.pop();
            ans = Math.max(ans, (n - p.idx) * p.height);
        }
        System.out.println(ans);
    }

    static class pair {
        int idx, height;

        pair(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }
    }
}
