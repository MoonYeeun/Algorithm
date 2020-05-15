package boj;

import java.util.*;

// 백준 2668 숫자고르기
// dfs
public class Back_2668 {
    static int[] arr;
    static boolean[] visit;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        arr = new int[n+1];
        for(int i = 1 ; i <= n ; i++) {
            arr[i] = sc.nextInt();
        }

        for(int i = 1 ; i <= n ; i++) {
            visit = new boolean[n+1];
            // 첫번째 칸 숫자 == 두번째 칸 숫자
            if(i == arr[i]) {
                pq.add(i);
                continue;
            }
            // 이미 숫자 선택된 경우
            if(!pq.isEmpty() && pq.contains(i)) continue;
            dfs(i, i); // 고를 숫자로 돌아올 수 있나 판단
        }
        System.out.println(pq.size());
        while (!pq.isEmpty()) System.out.println(pq.poll());
    }
    static boolean dfs(int s, int idx) {
        if(arr[idx] == s) {
            pq.add(idx);
            return true;
        }
        // 이미 방문한 지점이거나 선택된 숫자일 경우
        if(visit[idx] || pq.contains(idx)) return false;

        visit[idx] = true;
        if(dfs(s, arr[idx])) {
            pq.add(idx);
            return true;
        }
        return false;
    }
}
