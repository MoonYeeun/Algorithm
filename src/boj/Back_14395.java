package boj;

import java.util.*;

// 백준 14395 4연산
// 연산 순서 중요 ! ("*" > "+" > "-" > "/") 순으로 해야함
public class Back_14395 {
    static int s, t;
    static String ans = "";
    static String[] operation = {"*", "+", "-", "/"};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        s = sc.nextInt();
        t = sc.nextInt();

        if(s == t) {
            System.out.println(0);
            return;
        }
        bfs();
    }
    static void bfs() {
        HashSet<Long> set = new HashSet<>(); // 방문한적 있는지 확인
        Queue<pair> queue = new LinkedList<>();
        queue.add(new pair(s, ""));

        while (!queue.isEmpty()) {
            pair p = queue.poll();

            if(p.num == t) ans = ans.compareTo(p.order) >= 0 ? ans : p.order;

            for(int i = 0 ; i < 4 ; i++) {
                long result = cal(p.num, operation[i]);

                if(!set.contains(result)) {
                    set.add(result);
                    queue.add(new pair(result, p.order + operation[i]));
                }
            }
        }
        if(ans.equals("")) System.out.println(-1);
        else System.out.println(ans);
    }
    static long cal(long num, String op) {
        if(op.equals("+")) return num + num;
        else if(op.equals("-")) return num - num;
        else if(op.equals("*")) return num * num;
        else if(num != 0 && op.equals("/"))return num / num;
        else return num;
    }
    static class pair {
        long num;
        String order;

        pair(long num, String order) {
            this.num = num;
            this.order = order;
        }
    }
}
