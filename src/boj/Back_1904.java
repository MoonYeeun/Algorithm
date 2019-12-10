package boj;

import java.util.Scanner;
//백준 01타일
public class Back_1904 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] arr = new long[n+1];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;
        for(int i = 3; i <= n ; i++){
            arr[i] = arr[i-1] + arr[i-2];
            arr[i] %= 15746;
        }
        System.out.println(arr[n]);
    }
}
