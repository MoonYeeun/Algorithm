package boj;

import java.util.*;

// 백준 1360 되돌리기...

public class Back_1360 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Stack<pair> stack = new Stack<>();

        for(int i = 0 ; i < n ; i++) {
            stack.push(new pair(sc.next(), sc.next(), sc.nextInt()));
        }

        int due = Integer.MAX_VALUE;
        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            pair pair = stack.pop();
            int cur = pair.time;

            if(cur >= due) continue;

            if(pair.type.equals("type"))
                sb.append(pair.value);

            else {
                due = cur - Integer.parseInt(pair.value);
            }
        }
        System.out.println(sb.reverse().toString());
    }
    static class pair {
        String type, value;
        int time;

        pair(String type, String value, int time) {
            this.type = type;
            this.value = value;
            this.time = time;
        }
    }
}
