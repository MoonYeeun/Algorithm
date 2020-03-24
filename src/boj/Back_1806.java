package boj;

import java.util.Scanner;

// 백준 1806 부분합
// 투포인터 활용 !
public class Back_1806 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] arr = new int[n+1];

        for(int i = 0 ; i < n ; i++) {
            arr[i] = sc.nextInt();
        }

        int start = 0; // 시작 idx
        int end = 0; // 끝 idx
        int min = Integer.MAX_VALUE;
        int sum = arr[0];

        while(true) {
            if(end >= n || start > end || start >= n)
                break;
            if(sum >= s) {
                min = Math.min(end - start + 1, min);
                sum -= arr[start++];
            }
            if(sum < s)
                sum += arr[++end];
        }
        if(min == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(min);
    }
}
