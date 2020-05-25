package boj;

import java.util.*;

// 백준 1826 연료채우기
// 그리디 + 우선순위큐
public class Back_1826 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        pair[] list = new pair[n];
        for(int i = 0 ; i < n ; i++) {
            int d = sc.nextInt();
            int f = sc.nextInt();

            list[i] = new pair(d, f);
        }
        Arrays.sort(list, new Comparator<pair>() {
            @Override
            public int compare(pair pair, pair t1) {
                return pair.dis - t1.dis;
            }
        });

        int des = sc.nextInt(); // 목적지
        int cur = sc.nextInt(); // 현재 연료량
        int idx = 0;
        int ans = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        while (cur < des) {
            if(idx < n && cur - list[idx].dis >= 0) {
                pq.add(list[idx++].fuel);
            }
            else { // 해당 연료로 더이상 가지 못하는 경우 (연료 채움)
                if(pq.isEmpty()) break;

                cur += pq.poll();
                ans++;
            }
        }
        if(cur >= des) System.out.println(ans);
        else System.out.println(-1);
    }
    static class pair {
        int dis, fuel;

        pair(int dis, int fuel) {
            this.dis = dis;
            this.fuel = fuel;
        }
    }
}
