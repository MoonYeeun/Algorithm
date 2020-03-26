package boj;

import java.util.Scanner;

// 백준 6064 카잉 달력
public class Back_6064 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for(int i = 0 ; i < t ; i++) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();

            int answer = -1;
            int idx = 0;
            int num = x;
            int last = m * n / gcd(m, n); // m, n 의 최소공배수

            if(y == n) y = 0;
            while (true) {
                num = x + (idx * m);
                if(num % n == y) {
                    answer = num;
                    break;
                }
                if(num > last)
                    break;
                idx++;
            }
            System.out.println(answer);
        }
    }
    // 최대공약수
    static int gcd(int a , int b) {
        if (a % b == 0)
            return b;

        return gcd(b, a % b);
    }
}
