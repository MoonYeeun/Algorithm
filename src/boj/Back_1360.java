package boj;

import java.util.*;

// 백준 1360 되돌리기...
/* 스택에서 하나씩 빼면서
undo의 경우 (현재시간 - 되돌릴 시간) 보다 큰 시간은 무시해주면서
답 찾아가면 된다
(최종 undo 만이 효력있으므로 그 전 값들 무시 가능)
 */
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
