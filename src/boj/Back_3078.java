package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// ⭐ 백준 3078 좋은친구
// 이름이 2 ~ 20 이므로 각 이름의 길이에 해당하는 큐에 학생 인덱스 넣고 판단 !
public class Back_3078 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Integer> len[] = new Queue[21]; // 각 이름의 길이에 해당하는 학생 담을 큐
        for(int i = 2 ; i < 21 ; i++) {
            len[i] = new LinkedList<>();
        }

        long ans = 0;
        for(int i = 0 ; i < n ; i++) {
            String student = br.readLine();
            int name = student.length();

            if(!len[name].isEmpty()) {
                // 등수 차이 K 이상인 학생 제거
                while (!len[name].isEmpty() && i - len[name].peek() > k) {
                    len[name].poll();
                }
                ans += len[name].size();
            }
            len[name].add(i);
        }
        System.out.println(ans);
    }
}
