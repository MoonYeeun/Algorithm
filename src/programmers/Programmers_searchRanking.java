package programmers;

import java.util.*;

// ⭐️ 2021 카카오 1차 순위 검색
// 1. 경우의 수에 따라 조건별 사용자 분류 (경우의 수 만들고, 정렬)
// 2. query 문 돌면서 해당 점수 만족하는 사람 찾기 (이분탐색)
public class Programmers_searchRanking {
    static HashMap<String, ArrayList<Integer>> map;

    public static void main(String[] args) {
        String[] info = {
                "java backend junior pizza 150",
                "python frontend senior chicken 210",
                "python frontend senior chicken 150",
                "cpp backend senior pizza 260",
                "java backend junior chicken 80",
                "python backend senior chicken 50"
        };

        String[] query = {
                "java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150"
        };

        int[] result = solution(info, query);

        for (int i : result) {
            System.out.println(i);
        }
    }

    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        map = new HashMap<>();

        makeGroup(info);

        int idx = 0;
        for (String q : query) {
            String[] temp = q.split(" ");

            int score = Integer.parseInt(temp[temp.length - 1]); // 기준점수
            q = q.replace("and", "").replace(" ", "");
            q = q.replace(temp[temp.length - 1], ""); // 조건

            if (map.get(q) == null) {
                answer[idx++] = 0;
                continue;
            }
            answer[idx++] = find(map.get(q), score);
        }
        return answer;
    }

    public static void makeGroup(String[] info) {
        for (String i : info) {
            String[] person = i.split(" ");
            makeCase(person, 0, Integer.parseInt(person[4]), "");
        }
        // 각 리스트 오름차순 정렬
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            Collections.sort(map.get(it.next()));
        }
    }

    public static void makeCase(String[] info, int idx, int score, String cur) {
        if (idx == 4) {
            if (!map.containsKey(cur)) map.put(cur, new ArrayList<>());
            map.get(cur).add(score);
            return;
        }
        makeCase(info, idx + 1, score, cur + "-");
        makeCase(info, idx + 1, score, cur + info[idx]);
    }

    public static int find(ArrayList<Integer> list, int score) {
        int ans = list.size();
        int idx = list.size();
        int s = 0;
        int e = list.size() - 1;

        while (s <= e) {
            int mid = (s + e) / 2;

            if (list.get(mid) < score) s = mid + 1;
            else {
                e = mid - 1;
                idx = mid;
            }
        }
        return ans - idx;
    }
}
