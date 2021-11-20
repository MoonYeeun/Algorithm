package LeetCode;

import java.util.*;

// 739. Daily Temperatures
// stack
public class LC739_Daily_Temperatures {
    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = dailyTemperatures(temperatures);

        for (int i : result) {
            System.out.println(i);
        }
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        Stack<Pair> st = new Stack<>();
        int len = temperatures.length;
        int[] ans = new int[len];

        for (int i = 0; i < len; i++) {
            int cur = temperatures[i];

            while (!st.isEmpty() && st.peek().tem < cur) {
                Pair p = st.pop();
                ans[p.idx] = i - p.idx;
            }
            st.push(new Pair(i, cur));
        }
        return ans;
    }

    public static class Pair {
        int idx, tem;

        Pair(int idx, int tem) {
            this.idx = idx;
            this.tem = tem;
        }
    }
}
