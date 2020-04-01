package boj;

import java.util.Scanner;

// 백준 10775 공항
// 유니온 파인드
// 입력으로 들어온 gi 부터 도킹 -> 이미 도킹되었을 경우 gi-1 도킹
// 최종 루트 == 0 / 해당 gi root가 0이라면 더이상 도킹될 수 없다는 것 의미
public class Back_10775 {
    static int p, g, answer;
    static int[] root;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        g = sc.nextInt();
        p = sc.nextInt();

        root = new int[g+1];
        for(int i = 1; i <= g; i++) {
            root[i] = i;
        }
        for(int i = 0; i < p ; i++) {
            int gi = sc.nextInt();

            int docking = find(gi);
            if(docking == 0) break;
            union(docking, docking-1);
            answer++;
        }
        System.out.println(answer);
    }
    static int find(int x) {
        if(root[x] == x)
            return x;
        // 최종 루트 값으로 경로 압축 -> 그래야 시간초과 안남 !
        return root[x] = find(root[x]);
    }
    static void union(int x, int y) {
        x =find(x);
        y = find(y);

        root[x] = y;
    }
}
