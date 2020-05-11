package boj;

import java.util.*;

// 백준 1202 보석도둑
// 정렬 후 우선순위큐
public class Back_1202 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        // 보석 정보 리스트
        ArrayList<pair> list = new ArrayList<>();
        for(int i = 0 ; i < n ; i++) {
            list.add(new pair(sc.nextInt(), sc.nextInt()));
        }
        Collections.sort(list, (o1, o2) -> Integer.compare(o1.w, o2.w));
        // 가방
        int[] bag = new int[k];
        for(int i = 0 ; i < k ; i++) {
            bag[i] = sc.nextInt();
        }
        Arrays.sort(bag);

        PriorityQueue<pair> pq = new PriorityQueue<>();
        int idx = 0;
        long answer = 0;
        for(int i = 0 ; i < k ; i++) {
            // 해당 가방 최대 무게 보다 작은 보석 다 pq 에 넣음
            while (idx < list.size() && bag[i] >= list.get(idx).w) {
                pq.add(list.get(idx));
                idx++;
            }
            if(!pq.isEmpty()) answer += pq.poll().price;
        }
        System.out.println(answer);
    }
    static class pair implements Comparable<pair>{
        int w, price;

        pair(int w, int price) {
            this.w = w;
            this.price = price;
        }

        @Override
        public int compareTo(pair pair) {
            return pair.price - this.price;
        }
    }
}
