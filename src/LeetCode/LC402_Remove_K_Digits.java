package LeetCode;

import java.util.*;

// 402. Remove K Digits
public class LC402_Remove_K_Digits {
    public static void main(String[] args) {
        String num = "1432219";
        //String num = "10200";
        //String num = "11";
        //String num = "101";
        int k = 3;
        //int k = 2;
        //int k = 2;

        String result = removeKdigits(num, k);
        System.out.println(result);
    }

    public static String removeKdigits(String num, int k) {
        if (num.length() == k) return "0";

        Stack<Integer> st = new Stack<>();

        int cnt = 0; // 제거 개수
        for (char c : num.toCharArray()) {
            int cur = c - '0';

            while (!st.empty() && cnt < k && st.peek() > cur) {
                st.pop();
                cnt++;
            }

            st.push(cur);
        }

        StringBuilder sb = new StringBuilder();
        while (!st.empty() && cnt != k) {
            st.pop();
            cnt++;
        }

        for (int x : st) {
            if (x == 0 && sb.length() == 0) continue;
            else sb.append(x);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
