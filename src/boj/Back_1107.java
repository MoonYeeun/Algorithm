package boj;

import java.util.Scanner;

// 백준 1107 리모컨
/*
1. 입력 가능한 채널 범위
2. 모든 경우의 수 체크하면서 가능한지 불가능한지 확인
*/
public class Back_1107 {
    static int n;
    static int[] broke = new int[10];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int m = sc.nextInt();
        for(int i = 0 ; i < m ; i++) {
            int num = sc.nextInt();
            broke[num] = 1;
        }
        int min = Math.abs(n - 100); // +/- 만 눌러서 목표 채널에 도달하는 경우

        for(int i = 0 ; i <= 1000000 ; i++) {
            String temp = String.valueOf(i);
            int num = check(temp);
            if(num > 0)
                min = Math.min(min, (Math.abs(i - n)+ num));
        }
        System.out.println(min);
    }
    // 해당 숫자 만들 수 있는지 없는지 체크
    static int check(String num) {
        int len = num.length();
        for(int i = 0 ; i < len ; i++) {
            if(broke[num.charAt(i) - '0'] == 1) return 0;
        }
        return len;
    }
}
