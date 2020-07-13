package boj;

import java.util.*;

// 백준 1082 방번호
public class Back_1082 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] cost = new int[n]; // 각 숫자의 비용
        int[] number = new int[51]; // 만들 수 있는 숫자 자리수
        int min = 50; int idx = 0; // 최소 비용의 값, 자리

        for(int i = 0 ; i < n ; i++) {
            cost[i] = sc.nextInt();

            if(cost[i] <= min) {
                min = cost[i];
                idx = i;
            }
        }
        int total = sc.nextInt(); // 은진이가 가지고 있는 전체 돈

        int cnt = 0;
        while (total >= min) {
            total -= min;
            number[cnt++] = idx; // 최소 번호로 채우기
        }

        int start = -1;
        int money = total + min;
        // 최소값으로 최대 자리 수 만들어 놓고
        // 해당 자리 값 되팔아서 더 큰 수 살 수 있는지 판단 !
        for(int i = 0 ; i < cnt ; i++) {
            boolean flag = false;

            for(int j = n-1 ; j > 0 ; j--) {
                if(money >= cost[j]) {
                    money -= cost[j];
                    number[i] = j;
                    flag = true;

                    if(start == -1) start = i;
                    break;
                }
            }
            // 이미 구매했고, 바꿀 수 있는 자리 없는 경우 -> 최소값 그대로 구매
            if(!flag && start != -1) money -= min;
            money += min; // 해당 자리수 팔기
        }

        if(start == -1 || n == 1) System.out.println(0);
        else {
            for(int i = start ; i < cnt ; i++) {
                System.out.print(number[i]);
            }
            System.out.println();
        }
    }
}
