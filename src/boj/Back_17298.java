package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 17298 오큰수
// 스택
public class Back_17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];

        Stack<pair> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            int cur = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty() && stack.peek().amount < cur) {
                arr[stack.pop().idx] = cur;
            }
            stack.push(new pair(i, cur));
        }

        while (!stack.isEmpty()) {
            arr[stack.pop().idx] = -1;
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    static class pair {
        int idx, amount;

        pair(int idx, int amount) {
            this.idx = idx;
            this.amount = amount;
        }
    }
}
