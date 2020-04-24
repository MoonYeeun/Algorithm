package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 2910 빈도정렬
public class Back_2910 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        String str = bf.readLine();
        st = new StringTokenizer(str);

        HashMap<Integer, pair> map = new HashMap<>();
        for(int i = 0 ; i < n ; i++) {
            int num = Integer.parseInt(st.nextToken());

            if(map.containsKey(num)) {
                pair pair = map.get(num);
                map.put(num, new pair(num, pair.idx, pair.cnt + 1));
            }
            else
                map.put(num, new pair(num, i, 1));
        }

        PriorityQueue<pair> pq = new PriorityQueue<>();
        Iterator<Integer> it = map.keySet().iterator();
        while (it.hasNext()) {
            pq.add(map.get(it.next()));
        }
        while (!pq.isEmpty()) {
            pair pair = pq.poll();
            for(int i = 0 ; i < pair.cnt ; i++) {
                System.out.print(pair.num + " ");
            }
        }
    }
    static class pair implements Comparable<pair>{
        int num, idx, cnt;

        pair(int num, int idx, int cnt) {
            this.num = num;
            this.idx = idx;
            this.cnt = cnt;
        }
        @Override
        public int compareTo(pair pair) {
            if(pair.cnt == this.cnt)
                return this.idx - pair.idx;
            return pair.cnt - this.cnt;
        }
    }
}
