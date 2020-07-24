package boj;

import java.util.*;

// ⭐️ 백준 1593 문자해독
// 투포인터
// 2개의 map으로 각 문자의 개수 체크하기
// 각 맵에서 찾는 단어 개수 같은지 체크
public class Back_1593 {
    static int g, s;
    static char[] W, S;
    static HashMap<Character, Integer> W_Map = new HashMap<>();
    static HashMap<Character, Integer> S_Map = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        g = sc.nextInt();
        s = sc.nextInt();

        W = sc.next().toCharArray(); // 찾는 단어

        for(int i = 0 ; i < g ; i++) {
            // 각 문자 별 개수 체크
            W_Map.put(W[i], W_Map.getOrDefault(W[i], 0) + 1);
        }

        S = sc.next().toCharArray(); // 추출한 단어

        int ans = 0;
        int idx = 0;
        int len = 0;

        for(int i = 0 ; i < s ; i++) {
            len++;
            S_Map.put(S[i], S_Map.getOrDefault(S[i], 0) + 1);

            // 찾는 단어 길이와 같아진 경우
            if(len == g) {
                if(check()) ans++; // 찾는 단어 있는지 체크

                len--;
                S_Map.put(S[idx], S_Map.get(S[idx]) -1);
                idx++;
            }
        }
        System.out.println(ans);
    }
    static boolean check() {
        for(int i = 0 ; i < g ; i++) {
            if(W_Map.get(W[i]) != S_Map.get(W[i]))
                return false;
        }
        return true;
    }
    /*
    배열 활용하는 경우
    A-Z : 0 ~ 25 a-z : 26 ~ 51 로 지정
    투포인터로 위와 같은 방식으로 진행
    체킹 시 각 알파벳 배열로 개수 상태 체크 !
    (52개만 비교하므로 g 길이가 3000까지 커지는 경우 생각하면 시간 훨씬 줄어듬)

    static int g, s;
    static char[] W, S;
    static int[] wArr, sArr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        g = sc.nextInt();
        s = sc.nextInt();

        W = sc.next().toCharArray(); // 찾는 단어

        wArr = new int[52];
        sArr = new int[52];

        for(int i = 0 ; i < g ; i++) {
            // 각 문자 별 개수 체크
            if(W[i] < 'a') wArr[W[i] - 'A']++;
            else wArr[W[i] - 'a' + 26]++;
        }

        S = sc.next().toCharArray(); // 비교할 단어

        int ans = 0;
        int idx = 0;
        int len = 0;

        for(int i = 0 ; i < s ; i++) {
            len++;
            if(S[i] < 'a') sArr[S[i] - 'A']++;
            else sArr[S[i] - 'a' + 26]++;

            // 찾는 단어 길이와 같아진 경우
            if(len == g) {
                if(check()) ans++; // 찾는 단어 있는지 체크

                len--;
                if(S[idx] < 'a') sArr[S[idx] - 'A']--;
                else sArr[S[idx] - 'a' + 26]--;
                idx++;
            }
        }
        System.out.println(ans);
    }
    static boolean check() {
        for(int i = 0 ; i < 52 ; i++) {
            if(wArr[i] != sArr[i])
                return false;
        }
        return true;
    }
    * */
}
