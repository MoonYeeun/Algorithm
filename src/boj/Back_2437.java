package boj;

import java.util.*;

// 백준 2437 저울
// 현재 올리려는 저울추의 무게 > 지금까지 올린 저울추의 총 합 + 1 이면
// 지금까지 올린 저울추의 총 합 + 1 잴 수 없다.
public class Back_2437 {
    static int n;
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        int total = 0;
        for (int i = 0; i < n; i++) {
            if (total + 1 < arr[i]) break;

            total += arr[i];
        }
        System.out.println(total + 1);
    }
}
