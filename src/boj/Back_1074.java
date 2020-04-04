package boj;

import java.util.Scanner;

// 백준 1074 z
// 분할정복
public class Back_1074 {
    static int answer, r, c;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();

        int size = (int)Math.pow(2, n);
        check(0, 0, size);
    }
    static void check(int x, int y, int size) {
        if(size == 1) {
            if(x == r && y == c)
                System.out.println(answer);
            answer++;
            return;
        }
        int s = size/2;

        check(x, y, s); // 1사분면
        check(x, y + s, s); // 2사분면
        check(x + s, y, s); // 3사분면
        check(x + s, y + s, s); // 4사분면
    }
}
