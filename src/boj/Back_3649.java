package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 3649 로봇프로젝트
// 투포인터
public class Back_3649 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        while ((input = br.readLine()) != null) {
            int x = Integer.parseInt(input);
            int n = Integer.parseInt(br.readLine());

            x *= 10000000; // 나노 미터 단위로 바꿔줌

            int[] lego = new int[n];
            for(int i = 0 ; i < n ; i++) {
                lego[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(lego); // 레고 작은 길이부터 정렬

            int s = 0; int e = n-1;
            boolean flag = false;
            while (s < e) {
                if(lego[s] + lego[e] == x) {
                    flag = true;
                    break;
                }
                else if(lego[s] + lego[e] < x) s++;
                else e--;
            }
            if(flag) System.out.println("yes " + lego[s] + " " + lego[e]);
            else System.out.println("danger");
        }
    }
}
