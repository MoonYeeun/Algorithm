package programmers;

import java.util.*;

// 카카오 보석쇼핑
public class Programmers_jewelShopping {
    public static void main(String[] args) {
//        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
//        String[] gems = {"AA", "AB", "AC", "AA", "AC"};
//        String[] gems = {"XYZ", "XYZ", "XYZ"};
        String[] gems = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};

        int[] answer = solution(gems);

        for(int i : answer) {
            System.out.print(i + " ");
        }
    }
    public static int[] solution(String[] gems) {
        int[] answer = new int[2];
        answer[0] = 1; answer[1] = gems.length;

        HashMap<String, Integer> map = new HashMap<>(); // 보석 종류 구별
        int idx = 0;

        for(String j : gems) {
            if(!map.containsKey(j)) map.put(j, idx++);
        }

        int kind = map.size();
        int[] count = new int[kind]; // 해당 구간의 보석 개수 기록

        int s = 0; int e = 0;
        int cnt = 0;

        while (e < gems.length) {
            if(cnt < kind) {
                int num = ++count[map.get(gems[e])];

                if(num == 1) cnt++;
                e++;
            }
            else {
                int num = --count[map.get(gems[s])];

                if(num == 0) cnt--;

                // 구간이 더 짧고 ( 구간 길이 같은 경우 시작 번호 더 짧은 것)
                if(answer[1] - answer[0] > e - (s+1)) {
                    answer[0] = s+1;
                    answer[1] = e;
                }
                s++;
            }
        }
        return answer;
    }
}
