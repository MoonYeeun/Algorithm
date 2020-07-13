package boj;

import java.util.*;

public class Back_1082 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] cost = new int[n]; // 각 숫자의 비용
        int[] number = new int[51]; // 만들 수 있는 숫자 자리수


        for(int i = 0 ; i < n ; i++) {
            cost[i] = sc.nextInt();
        }
        int total = sc.nextInt(); // 은진이가 가지고 있는 전체 돈

        int idx = 0; // 만들 수 있는 최대 자리수
        while (total >= cost[0]) {
            total -= cost[0];
            idx++;
        }

        int start = -1;
        int money = total + cost[0];
        // 최소값으로 최대 자리 수 만들어 놓고
        // 해당 자리 값 되팔아서 더 큰 수 살 수 있는지 판단 !
        for(int i = 0 ; i < idx ; i++) {
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
            if(!flag && start != -1) money -= cost[0];
            money += cost[0]; // 해당 자리수 팔기
        }

        for(int i = start ; i < idx ; i++) {
            System.out.print(number[i]);
        }
    }
}
