package not_solve_myself;

import java.util.*;

// ⭐️ 백준 10165 버스노선
// 라인스위핑...
public class Back_10165 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<pair> list = new ArrayList<>();
        int irrS = 1000000000;
        int irrE = 0; // 역방향 노선 중 시작점 가장 작은 값 : S 끝점 가장 큰 값 : E

        for (int i = 1; i <= m; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();

            if (s > e) {
                irrS = Math.min(irrS, s);
                irrE = Math.max(irrE, e);
            }
            list.add(new pair(i, s, e));
        }

        Collections.sort(list);
        boolean[] check = new boolean[500001];
        int curE = 0; // 현재까지 정방향의 끝 값
        int curIE = 0; // 현재까지 역방향의 끝 값

        for (pair p : list) {
            // 현재 정방형
            if (p.start < p.end) {
                // 정방형 -> 정방형
                if (curE >= p.end) check[p.idx] = true;
                else curE = p.end;
                // 정방형 -> 역방형
                if (p.start >= irrS || p.end <= irrE) check[p.idx] = true;
            }
            // 역방형 -> 역방형
            else {
                if (curIE >= p.end + n) check[p.idx] = true;
                else curIE = p.end + n;
            }
        }

        for (int i = 1; i <= m; i++) {
            if (check[i]) continue;
            System.out.print(i + " ");
        }
    }

    static class pair implements Comparable<pair> {
        int idx, start, end;

        pair(int idx, int start, int end) {
            this.idx = idx;
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(pair pair) {
            if (this.start == pair.start) return pair.end - this.end;
            return this.start - pair.start;
        }
    }
}
