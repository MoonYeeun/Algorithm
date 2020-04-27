package boj;

import java.util.*;

// 백준 1475 방 번호
public class Back_1475 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String room = String.valueOf(n);

        int[] arr = new int[10];
        for(int i = 0 ; i < room.length() ; i++) {
            int num = room.charAt(i) - '0';
            if(num == 6 || num == 9) {
                arr[6]++; // 6으로 몰기
                continue;
            }
            arr[num]++;
        }
        arr[6] = arr[6]/2 + arr[6]%2; // 한 세트당 2개가능
        Arrays.sort(arr);
        System.out.println(arr[9]);
    }
}

