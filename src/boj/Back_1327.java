package boj;

import java.util.*;

// 백준 1327 소트게임
// bfs → 첨엔 복잡하게 생각했는데 그냥 bfs로 쉽게 풀리는 문제..
public class Back_1327 {
    static String target;
    static Queue<Pair> queue;
    static HashSet<String> set;
    static int n, k;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        queue = new LinkedList<>();
        set = new HashSet<>();

        char[] arr = new char[n];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            arr[i] = (char) (sc.nextInt() + '0');
            sb.append(arr[i]);
        }

        Arrays.sort(arr);
        target = new String(arr);
        queue.add(new Pair(sb.toString(), 0));
        set.add(sb.toString());

        System.out.println(bfs());
    }

    static int bfs() {
        int ans = -1;
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();

            if (pair.num.equals(target)) {
                ans = pair.cnt;
                break;
            }

            for (int i = 0; i <= n - k; i++) {
                String temp = reverse(pair.num, i, i + k - 1);

                if (set.contains(temp)) continue;
                set.add(temp);
                queue.add(new Pair(temp, pair.cnt + 1));
            }
        }
        return ans;
    }

    static String reverse(String num, int s, int e) {
        StringBuilder sb = new StringBuilder();

        for (int i = e; i >= s; i--) {
            sb.append(num.charAt(i));
        }
        return num.substring(0, s) + sb.toString() + num.substring(e + 1);
    }

    static class Pair {
        String num;
        int cnt;

        Pair(String num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}
