package boj;

import java.util.*;

// 백준 1092 배
// 그리디 + 이분탐색
public class Back_1092 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] w = new int[n];
        for(int i = 0 ; i < n ; i++) {
            w[i] = sc.nextInt();
        }
        Arrays.sort(w); // 크레인 정렬

        int m = sc.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0 ; i < m ; i++) {
            list.add(sc.nextInt());
        }
        Collections.sort(list); // 박스 크기 정렬

        if(list.get(m-1) > w[n-1]) System.out.println(-1); // 옮길 수 없는 경우
        else {
            int cnt = 0; // 소요 시간
            int rm = 0; // 삭제한 개수

            while (rm < m) {
                for(int i = n-1; i >= 0 ; i--) {
                    int idx = bs(0, list.size(), w[i], list); // 삭제할 수 있는 박스 중 가장 무거운 것 찾기
                    if(idx >= 0) {
                        list.remove(idx);
                        rm++;
                    }
                }
                cnt++;
            }
            System.out.println(cnt);
        }
    }
    static int bs(int start, int end, int target, ArrayList<Integer> list) {
        boolean flag = false;
        int ans = 0;
        while (start < end) {
            int mid = (start + end) / 2;

            if(target >= list.get(mid)) {
                flag = true;
                ans = Math.max(ans, mid);
                start = mid + 1;
            }
            if(target < list.get(mid)) end = mid -1;
        }
        if(flag) return ans;
        else return -1;
    }
}
