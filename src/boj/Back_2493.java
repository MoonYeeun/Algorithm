package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 2493 탑
// 스택
public class Back_2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Stack<pair> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < n ; i++) {
            int num = Integer.parseInt(st.nextToken());

            // 자신보다 작은 탑 있으면 제거 (도달하지 못하므로)
            while (!stack.isEmpty() && stack.peek().height < num) {
                stack.pop();
            }

            if(stack.isEmpty()) {
                System.out.print(0 + " ");
                stack.push(new pair(i+1, num));
            }
            else {
                System.out.print(stack.peek().idx + " ");
                stack.push(new pair(i+1, num));
            }
        }
    }
    static class pair {
        int idx, height;

        pair(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }
    }
}
