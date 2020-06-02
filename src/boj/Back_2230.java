package boj;

import java.util.*;

// 백준 2230 수 고르기
// 투포인터
public class Back_2230 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];

        for(int i = 0 ; i < n ; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);

        int s = 0; int e = 0;
        int min = 2000000000;

        while (s < n && e < n) {
            if(arr[e] - arr[s] < m) e++;
            else {
                min = Math.min(min, arr[e] - arr[s]);
                s++;
            }
        }
        System.out.println(min);
    }
}
