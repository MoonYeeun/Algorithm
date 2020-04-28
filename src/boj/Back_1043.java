package boj;

import java.util.*;

// 백준 1043 거짓말
// 비트마스킹 & 유니온파인드
public class Back_1043 {
    static int[] root = new int[51];
    static int[] party;
    static HashSet<Integer> truth = new HashSet<>();
    static HashSet<Integer> lie = new HashSet<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int p = sc.nextInt();

        party = new int[m];
        for(int i = 0 ; i < p ; i++) {
            truth.add(sc.nextInt()); // 진실을 아는 사람
        }
        for(int i = 1 ; i <= n ; i++) {
            root[i] = i;
            lie.add(i); // 거짓말 가능한 사람 초기화
        }
        for(int i = 0 ; i < m ; i++) {
            int num = sc.nextInt();

            int last = 0;
            int bit = 0;
            for(int j = 0 ; j < num ; j++) {
                int w = sc.nextInt();
                bit |= (1<<w);
                // 파티에서 만난 사람 연결
                if(j != 0 && find(w) != find(last)) union(last, w);
                last = w;
            }
            party[i] = bit; // 해당 파티에 온 사람
        }
        // 거짓말 해도 되는 사람 구하기
        int bit = 0;
        Iterator<Integer> it = truth.iterator();
        while (it.hasNext()) {
            int wit = find(it.next());
            for(int i = 1 ; i <= n ; i++) {
                if(wit == find(i)) lie.remove(i);
            }
        }
        Iterator<Integer> it2 = lie.iterator();
        while (it2.hasNext()) {
            bit |= (1<<it2.next());
        }
        int cnt = 0;
        for(int i : party) {
            if((i & bit) == i) cnt++;
        }
        System.out.println(cnt);
    }
    static int find(int x) {
        if(x == root[x]) return x;
        return root[x] = find(root[x]);
    }
    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        root[x] = y;
    }
}
