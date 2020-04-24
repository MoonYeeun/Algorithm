package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 17140 이차원 배열과 연산
public class Back_17140 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] map = new int[3][3];
        for(int i = 0 ; i < 3; i++) {
            String str = bf.readLine();
            st = new StringTokenizer(str);

            for(int j = 0 ; j < 3 ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int cnt = 0;
        int type = 0;
        int[][] arr = Arrays.copyOf(map, map.length);

        while (cnt < 100) {
            if(r-1 < arr.length && c-1 < arr[0].length && arr[r-1][c-1] == k) break;

            type = arr.length >= arr[0].length ? 0 : 1;
            arr = oper(type, arr);
            cnt++;
        }

        if(cnt >= 100) System.out.println(-1);
        else System.out.println(cnt);
    }
    static int[][] oper(int type, int[][] arr) {
        int max = 0; // 가장 긴 행 or 열

        // type = 0 : R연산 1 : C연산
        int end = type == 0 ? arr.length : arr[0].length;
        int cnt = type == 0 ? arr[0].length : arr.length;

        ArrayList<Integer> list[] = new ArrayList[end];

        for(int i = 0 ; i < end ; i++) {
            list[i] = new ArrayList<>();

            HashMap<Integer, Integer> map = new HashMap<>();
            for(int j = 0 ; j < cnt ; j++) {
                if(type == 0) {
                    if(arr[i][j] == 0) continue;
                    map.put(arr[i][j], map.getOrDefault(arr[i][j], 0)+1);
                }
                else {
                    if(arr[j][i] == 0) continue;
                    map.put(arr[j][i], map.getOrDefault(arr[j][i], 0)+1);
                }
            }

            PriorityQueue<pair> pq = new PriorityQueue<>();
            Iterator<Integer> it = map.keySet().iterator();
            while (it.hasNext()) {
                int key = it.next();
                pq.add(new pair(key, map.get(key)));
            }
            while (!pq.isEmpty()) {
                pair pair = pq.poll();
                list[i].add(pair.num);
                list[i].add(pair.cnt);
            }
            max = Math.max(max, list[i].size());
        }
        // 새로운 배열 만들기
        int[][] new_arr;
        if(max > 100) max = 100;
        if(type == 0)
            new_arr = new int[end][max];
        else
            new_arr = new int[max][end];

        int idx = 0;
        for(ArrayList<Integer> tm : list) {
            for(int i = 0 ; i < max ; i++) {
                if(type == 0)
                    new_arr[idx][i] = i < tm.size() ? tm.get(i) : 0;
                else
                    new_arr[i][idx] = i < tm.size() ? tm.get(i) : 0;
            }
            idx++;
        }
        return new_arr;
    }
    static class pair implements Comparable<pair>{
        int num, cnt;

        pair(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
        @Override
        public int compareTo(pair pair) {
            if(pair.cnt == this.cnt)
                return this.num - pair.num;
            return this.cnt - pair.cnt;
        }
    }
}
