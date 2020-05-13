package boj;

import java.util.*;

// 백준 1484 다이어트
// 투포인터
public class Back_1484 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int g = sc.nextInt();

        int s = 1; int e = 1;
        boolean flag = false;
        while (e < g) {
            if(e * e - s * s == g) {
                System.out.println(e);
                flag = true;
            }
            if(e * e - s * s < g) e++;
            else s++;
        }
        if(!flag) System.out.println(-1);
    }
}
