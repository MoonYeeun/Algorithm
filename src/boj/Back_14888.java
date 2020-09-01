package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 14888 연산자 끼워넣기
public class Back_14888 {
    static ArrayList<Integer> num = new ArrayList<>();
    static int[] op = new int[4];
    static int min, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        min = 1000000000;
        max = -1000000000;
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }
        make(1, num.get(0));
        System.out.println(max);
        System.out.println(min);
    }

    static void make(int idx, int result) {
        if (idx == num.size()) {
            if (result < min) min = result;
            if (result > max) max = result;
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (op[i] <= 0) continue;

            op[i]--;
            make(idx + 1, cal(result, num.get(idx), i));
            op[i]++;
        }
    }

    static int cal(int num1, int num2, int op) {
        if (op == 0) return num1 + num2;
        else if (op == 1) return num1 - num2;
        else if (op == 2) return num1 * num2;
        else return num1 / num2;
    }
}
