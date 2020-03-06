package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 백준 2606 바이러스
public class Back_2606 {
    static int[][] map;
    static boolean[] visit;
    static int computer;
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        computer = sc.nextInt();
        int connect = sc.nextInt();

        map = new int[computer+1][computer+1];
        visit = new boolean[computer+1];
        for(int i = 0; i < connect ; i++) {
            int con1 = sc.nextInt();
            int con2 = sc.nextInt();
            map[con1][con2] = 1; // 연결된 간선 정보 담은 배열
            map[con2][con1] = 1;
        }
        queue.add(1);
        System.out.println(bfs());
    }
    static int bfs(){
        int cnt = 0;
        while(!queue.isEmpty()) {
            int virus = queue.poll();
            visit[virus] = true;
            for(int i = 1 ; i <= computer ; i++) {
                if((map[virus][i] == 1 || map[i][virus] == 1) && !visit[i]) {
                    queue.add(i);
                    visit[i] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
