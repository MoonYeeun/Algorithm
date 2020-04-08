package boj;
import java.util.*;

// 백준 1700 멀티탭 스케줄링
// 그리디 알고리즘 -> 가장 나중에 사용할 전자기기 뽑기
public class Back_1700 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] map = new int[k];
        for(int i = 0 ; i < k ; i++) {
            map[i] = sc.nextInt();
        }
        HashSet<Integer> set = new HashSet<>(); // 멀티탭
        int cnt = 0;
        for(int i = 0 ; i < k ; i++) {
            // 멀티탭에 이미 사용하고자 하는 것 꽂혀 있는 경우
            if(set.contains(map[i])) continue;
            // 멀티탭 빈 경우
            if(set.size() < n) {
                set.add(map[i]);
                continue;
            }
            // 아닌경우 -> 꽃힌 기기 중 앞으로 가장 나중에 사용될 것 고름
            Iterator<Integer> it = set.iterator();
            PriorityQueue<pair> pq = new PriorityQueue<>();
            while (it.hasNext()) {
                boolean flag = false; // 앞으로 사용 여부
                int use = it.next();
                for(int idx = i+1 ; idx < k ; idx++) {
                    if(use == map[idx]) {
                        pq.add(new pair(use, idx));
                        flag = true;
                        break;
                    }
                }
                // 앞으로 사용되지 않을 경우
                if(!flag) pq.add(new pair(use, Integer.MAX_VALUE));
            }

            set.remove(pq.poll().kind);
            set.add(map[i]);
            cnt++;
        }
        System.out.println(cnt);
    }
    static class pair implements Comparable<pair> {
        int kind, use_idx;

        pair(int kind, int use_idx) {
            this.kind = kind;
            this.use_idx = use_idx;
        }

        @Override
        public int compareTo(pair pair) {
            return pair.use_idx - this.use_idx;
        }
    }
}
