package LeetCode;

import java.util.*;

// 743. Network Delay Time
// 다익스트라
public class LC743_Network_Delay_Time {
    static ArrayList<Pair>[] list;
    static int[] result;

    public static void main(String[] args) {
        //int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        int[][] times = {{1, 2, 1}, {2, 1, 3}};
        int n = 2;
        int k = 2;
        int ans = networkDelayTime(times, n, k);
        System.out.println(ans);
    }

    public static int networkDelayTime(int[][] times, int n, int k) {
        list = new ArrayList[n + 1];
        result = new int[n + 1];
        Arrays.fill(result, Integer.MAX_VALUE);

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int[] time : times) {
            list[time[0]].add(new Pair(time[1], time[2]));
        }

        dijkstra(k);

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (result[i] == Integer.MAX_VALUE) {
                ans = -1;
                break;
            }
            ans = Math.max(ans, result[i]);
        }
        return ans;
    }

    static void dijkstra(int start) {
        result[start] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(start, 0));

        while (!pq.isEmpty()) {
            Pair p = pq.poll();

            if (result[p.des] < p.time) continue;

            for (Pair next : list[p.des]) {
                if (next.time + p.time < result[next.des]) {
                    pq.add(new Pair(next.des, next.time + p.time));
                    result[next.des] = next.time + p.time;
                }
            }
        }
    }

    static class Pair implements Comparable<Pair> {
        int des, time;

        Pair(int des, int time) {
            this.des = des;
            this.time = time;
        }

        @Override
        public int compareTo(Pair pair) {
            return this.time - pair.time;
        }
    }
}
