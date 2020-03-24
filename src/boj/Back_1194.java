package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 백준 1194 달이 차오른다 가자
/* 비트마스킹 + BFS
방문 체크를 어떻게 처리해야 하나 고민했는데 key, x, y 삼중 배열로 체크하면 되었음
 */
public class Back_1194 {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int n, m;
    static char[][] map;
    static boolean[][][] visited;
    static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 세로
        m = sc.nextInt(); // 가로

        map = new char[n][m];
        visited = new boolean[64][n][m];

        for(int i = 0 ; i < n ; i++) {
            String str = sc.next();
            for(int j = 0 ; j < m ; j++) {
                map[i][j] = str.charAt(j);

                if(map[i][j] == '0') {
                    queue.add(new Node(i, j, 0, 0)); // 민식이의 현재위치
                    visited[0][i][j] = true;
                    map[i][j] = '.';
                }
            }
        }
        System.out.println(bfs());
    }
    static int bfs() {
        int answer = -1;

        while (!queue.isEmpty()) {
            Node minsik = queue.poll();

            if(map[minsik.x][minsik.y] == '1') {
                answer = minsik.cnt;
                break;
            }

            for(int i = 0 ; i < 4 ; i++) {
                int nx = minsik.x + dx[i];
                int ny = minsik.y + dy[i];
                int key = minsik.key;

                if(nx < 0 || nx >= n || ny < 0 || ny >= m
                        || map[nx][ny] == '#' || visited[key][nx][ny]) continue;

                else if('A' <= map[nx][ny] && map[nx][ny] <= 'F') {
                    if(!check(key, map[nx][ny] - 'A'))
                        continue;
                }
                else if('a' <= map[nx][ny] && map[nx][ny] <= 'f') {
                    key = key | (1 << map[nx][ny] - 'a');
                }
                if(visited[key][nx][ny])
                    continue;

                queue.add(new Node(nx, ny, minsik.cnt + 1, key));
                visited[key][nx][ny] = true;
            }
        }
        return answer;
    }
    // 해당 문에 맞는 키 가지고 있는지 체크
    static boolean check(int key, int door) {
        if((key & (1<<door)) != (1<<door)) return false;
        return true;
    }

    static class Node {
        int x, y, cnt, key;

        Node(int x, int y, int cnt, int key) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.key = key;
        }
    }
}
