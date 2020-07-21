package boj;

import java.util.*;

// ⭐️ 백준 7575 바이러스
// KMP
// 1. 첫번째 프로그램 기준 k개씩 나눠서 실패테이블 만들기
// 2. 각 프로그램 별로 1에서 만들어진 패턴 다 비교(순방향, 역방향)
// 3. 매칭된 패턴 체크 후 앞서 매칭된 패턴만 다른 프로그램에서 비교
// 4. 마지막까지 살아남은 패턴 있으면 YES 아니면 NO
public class Back_7575 {
    static int[][] table;
    static int[] virusLen;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[][] virus = new int[n][1001];
        virusLen = new int[n]; // 각 프로그램의 길이

        // 각 프로그램의 바이러스 코드 입력 받기
        for(int i = 0 ; i < n ; i++) {
            virusLen[i] = sc.nextInt();

            for(int j = 0 ; j < virusLen[i] ; j++) {
                virus[i][j] = sc.nextInt();
            }
        }
        table = new int[virusLen[0]- k + 1][k];
        boolean[] pattern = new boolean[virusLen[0]- k + 1];
        Arrays.fill(pattern, true);

        // 첫번째 프로그램 기준 k개 문자열 실패테이블 만들기
        for(int i = 0 ; i < virusLen[0] - k + 1; i++) {
            makeTable(virus, i, k);
        }
        // 비교
        for(int i = 1; i < n ; i++) {
            for(int j = 0 ; j < virusLen[0] - k + 1 ; j++) {
                if(!pattern[j]) continue; // 살아남지 못한 패턴

                // 다른 프로그램에서 매칭된 패턴만 비교
                if(!match(virus, i, j, k)) pattern[j] = false;
            }
        }
        // 마지막까지 살아남은 패턴있는지 체크
        boolean flag = false;
        for(int i = 0 ; i < virusLen[0] - k + 1 ; i++) {
            if(pattern[i]) flag = true;
        }
        if(flag) System.out.println("YES");
        else System.out.println("NO");
    }
    static boolean match(int[][] virus, int v, int p, int k) {
        int j = 0;
        // 순방향 비교
        for(int i = 0 ; i < virusLen[v] ; i++) {
            while (j > 0 && virus[v][i] != virus[0][p + j]) {
                j = table[p][j-1];
            }
            if(virus[v][i] == virus[0][p + j]) {
                j++;
                if(j == k) return true;
            }
        }
        // 역방향 비교
        j = 0;
        for(int i = virusLen[v]-1 ; i >= 0 ; i--) {
            while (j > 0 && virus[v][i] != virus[0][p + j]) {
                j = table[p][j-1];
            }
            if(virus[v][i] == virus[0][p + j]) {
                j++;
                if(j == k) return true;
            }
        }
        return false;
    }
    static void makeTable(int[][] virus, int idx, int k) {
        int j = 0;
        for(int i = 1 ; i < k ; i++) {
            while (j > 0 && virus[0][idx + i] != virus[0][idx + j]) {
                j = table[idx][j-1];
            }
            if(virus[0][idx + i] == virus[0][idx + j]) table[idx][i] = ++j;
        }
    }
}
