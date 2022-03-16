package programmers;

import java.util.*;

// 2022 카카오 신고결과받기
public class Programmers_receiveReportResult {
    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        int k = 2;

        var result = solution(id_list, report, k);
        for (int i : result) {
            System.out.println(i);
        }
    }

    static public int[] solution(String[] id_list, String[] report, int k) {
        int size = id_list.length;
        int[] answer = new int[size];
        HashMap<String, Integer> idMap = makeIdIdx(id_list);
        HashMap<Integer, HashSet<Integer>> reportMap = new HashMap<>(); // 각 유저별 신고 기록

        for (String str : report) {
            String[] info = str.split(" ");
            int reportUser = idMap.get(info[0]);
            int blockUser = idMap.get(info[1]);

            HashSet<Integer> result = reportMap.getOrDefault(blockUser, new HashSet<>());
            result.add(reportUser);
            reportMap.put(blockUser, result);
        }

        Iterator<Integer> it = reportMap.keySet().iterator();
        while (it.hasNext()) {
            int user = it.next();
            if (reportMap.get(user).size() >= k) sendMail(reportMap.get(user), answer);
        }
        return answer;
    }

    static public HashMap<String, Integer> makeIdIdx(String[] id_list) {
        HashMap<String, Integer> map = new HashMap<>();
        int idx = 0;
        for (String str : id_list) {
            map.put(str, idx++);
        }
        return map;
    }

    static public void sendMail(HashSet<Integer> list, int[] ans) {
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            ans[it.next()]++;
        }
    }
}
