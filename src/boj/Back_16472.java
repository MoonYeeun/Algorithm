package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// 백준 16472 고냥이
// 투포인터
public class Back_16472 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        String str = st.nextToken();

        HashSet<Character> set = new HashSet<>();
        int[] alpabet = new int[26]; // 각 알파벳 몇 개 냐왔는지 체크
        int s = 0 ; int e = 0;
        int ans = 0;

        while (e < str.length()) {
            // 이미 있는 경우
            if(set.contains(str.charAt(e))) alpabet[str.charAt(e) - 'a']++;
            // 없는 경우
            else {
                // 이미 인식할 수 있는 문자열 초과한 경우 -> 인식 가능할 때까지 이동
                while (set.size() >= n) {
                    char target = str.charAt(s);
                    alpabet[target - 'a']--;

                    if(alpabet[target - 'a'] == 0) set.remove(target);
                    s++;
                }
                set.add(str.charAt(e));
                alpabet[str.charAt(e) - 'a']++;
            }
            e++;
            if(ans < (e - s)) ans = e - s;
        }
        System.out.println(ans);
    }
}
