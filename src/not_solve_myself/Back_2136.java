package not_solve_myself;

import java.util.*;

// 백준 2136 개미
// 가장 마지막에 떨어지는 시간 : 오른쪽으로 가는 개미 시간, 왼쪽으로 가는 개미 시간 비교 후 더 큰 값
// 가장 마지막에 떨어지는 개미 : 좌표 순으로 정렬 후 앞선 시간 비교 후 오른쪽 개미 / 왼쪽 개미 idx 구하기
public class Back_2136 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int len = sc.nextInt();

        int[] ant = new int[n];
        pair[] result = new pair[n];

        int left = 0;
        int rightTime = 0;
        int leftTime = 0;

        for (int i = 0; i < n; i++) {
            ant[i] = sc.nextInt();

            if (ant[i] < 0) {
                left++;
                leftTime = Math.max(leftTime, -ant[i]);
            } else rightTime = Math.max(rightTime, len - ant[i]);

            result[i] = new pair(i + 1, Math.abs(ant[i]));
        }
        // 마지막으로 떨어진 개미 찾기 위해 정렬 (양 끝점과의 거리순)
        Arrays.sort(result, new Comparator<pair>() {
            @Override
            public int compare(pair t1, pair t2) {
                return Integer.compare(t1.time, t2.time);
            }
        });

        if (rightTime > leftTime) System.out.println(result[left].idx + " " + rightTime);
        else System.out.println(result[left - 1].idx + " " + leftTime);
    }

    static class pair {
        int idx, time;

        pair(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }
}
