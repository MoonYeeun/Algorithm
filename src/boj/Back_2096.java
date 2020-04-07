package boj;

import java.util.Arrays;
import java.util.Scanner;

// 백준 2096 내려가기
// dp
public class Back_2096 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // 이전 col
        int[] colMin = new int[3];
        int[] colMax = new int[3];
        // 현재 col
        int[] tempMin = new int[3];
        int[] tempMax = new int[3];

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < 3 ; j++) {
                tempMin[j] = sc.nextInt();
                tempMax[j] = tempMin[j];
            }
            tempMax[0] = Math.max(colMax[0] + tempMax[0], colMax[1] + tempMax[0]);
            tempMax[1] = Math.max(Math.max(colMax[0] + tempMax[1], colMax[1] + tempMax[1]), colMax[2] + tempMax[1]);
            tempMax[2] = Math.max(colMax[1] + tempMax[2], colMax[2] + tempMax[2]);

            tempMin[0] = Math.min(colMin[0] + tempMin[0], colMin[1] + tempMin[0]);
            tempMin[1] = Math.min(Math.min(colMin[0] + tempMin[1], colMin[1] + tempMin[1]), colMin[2] + tempMin[1]);
            tempMin[2] = Math.min(colMin[1] + tempMin[2], colMin[2] + tempMin[2]);

            colMax = Arrays.copyOf(tempMax, tempMax.length);
            colMin = Arrays.copyOf(tempMin, tempMin.length);
        }
        Arrays.sort(tempMax);
        Arrays.sort(tempMin);

        System.out.println(tempMax[2]);
        System.out.println(tempMin[0]);
    }
}
