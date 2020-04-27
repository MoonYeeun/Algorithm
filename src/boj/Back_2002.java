package boj;

import java.util.HashMap;
import java.util.Scanner;

// 백준 2002 추월
public class Back_2002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0 ; i < n ; i++) {
            String car = sc.next();
            map.put(car, i); // 차량, 들어온 순서
        }
        // 나온 차량
        int cnt = 0;
        String[] arr = new String[n];
        for(int i = 0 ; i < n ; i++) {
            String car = sc.next();
            arr[i] = car;
        }
        // 해당 차량보다 뒤에 있는 차량 중 먼저 들어온 차량이 있는 경우 -> 추월
        for(int i = 0 ; i < n ; i++) {
            for(int j = i+1 ; j < n ; j++) {
                if(map.get(arr[j]) < map.get(arr[i])) {
                    cnt++;
                    break;
                }
            }
        }
        System.out.println(cnt);
    }
}
