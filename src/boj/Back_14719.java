package boj;

import java.util.*;

// 백준 14719 빗물
// 스택
public class Back_14719 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();

        Stack<pair> st = new Stack<>();

        int ans = 0;
        for (int i = 0; i < w; i++) {
            int cur = sc.nextInt();

            while (!st.isEmpty() && st.peek().block < cur) {
                pair prev = st.pop();

                if (!st.isEmpty()) {
                    int amount = Math.min(st.peek().block, cur);
                    ans += (amount - prev.block) * (i - st.peek().idx - 1);
                }
            }
            st.push(new pair(i, cur));
        }
        System.out.println(ans);
    }

    static class pair {
        int idx, block;

        pair(int idx, int block) {
            this.idx = idx;
            this.block = block;
        }
    }
}
