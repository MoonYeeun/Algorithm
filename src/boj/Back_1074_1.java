package boj;

import java.util.Scanner;

// 백준 1074 Z
// 분할정복 / 재귀 X
// 1, 2, 3, 4 분면 중 어디에 속하는지 구해서 한번에 계산
public class Back_1074_1 {
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
        while (size > 0) {
            size /= 2;
            // 2사분면 (왼 위)
            if(r < x + size && c < y + size) {
                answer += size * size * 0;
            }
            // 1사분면 (오른 위)
            if(r < x + size && c >= y + size) {
                answer += size * size * 1;
                y += size;
            }
            // 3사분면 (왼 아래)
            if(r >= x + size && c < y + size) {
                answer += size * size * 2;
                x += size;
            }
            // 4사분면 (오른 아래)
            if(r >= x + size && c >= y + size) {
                answer += size * size * 3;
                x += size;
                y += size;
            }

            if(size == 1) {
                System.out.println(answer);
                return;
            }
        }
    }
}
