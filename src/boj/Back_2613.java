package boj;

import java.util.*;

// 백준 2613 숫자구슬
public class Back_2613 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] arr = new int[n+1];
        for(int i = 1 ; i <= n ; i++) {
            arr[i] = sc.nextInt();
        }

        int[] group = new int[301]; // 각 구슬 그룹 개수
        int s = 1; int e = 30000;
        int idx; int ans = 0;

        while (s <= e) {
            int[] temp = new int[301];
            int mid = (s + e) / 2; // 그룹 최대값
            idx = 1;
            int sum = 0;

            for(int i = 1 ; i <= n ; i++) {
                if(arr[i] > mid) { // 그룹 만들 수 없는 경우
                    idx = m+1;
                    break;
                }
                sum += arr[i];

                if(sum > mid) {
                    idx++;
                    sum = arr[i];
                    temp[idx] = 1;
                    continue;
                }
                temp[idx]++;
            }
            // 만들어진 그룹 수 <= 만들어야 하는 그룹 수
            if(idx <= m) {
                ans = mid;
                group = temp;
                e = mid - 1;
            }
            // 만들어진 그룹 수 > 만들어야 하는 그룹 수
            else s = mid + 1;
        }

        print(ans, m, group);
    }
    static void print(int mid, int m, int[] group) {
        System.out.println(mid);

        int idx = 0;
        // 0개인 그룹 없도록 만들기
        for(int i = 1 ; i <= m ; i++) {
            if(group[i] != 0) continue;

            idx = i - 1;
            group[i]++;

            while (true) {
                if(group[idx] == 1) idx--;
                else break;
            }
            group[idx]--;
        }
        // 출력
        for(int i = 1 ; i <= m ; i++) {
            System.out.print(group[i] + " ");
        }
    }
}
