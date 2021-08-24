package programmers;

import java.util.*;

// 프로그래머스 위클리챌린지 4 직업군 추천하기
public class Programmers_recommendJob {
    public static void main(String[] args) {
        String[] table = {"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"};
        String[] languages = {"PYTHON", "C++", "SQL"};
        int[] preference = {7, 5, 5};

        System.out.println(solution(table, languages, preference));
    }

    public static String solution(String[] table, String[] languages, int[] preference) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<>();

        int idx = 0;
        for (String str : languages) {
            map.put(str, preference[idx++]);
        }

        int max = 0;
        for (String str : table) {
            String[] info = str.split(" ");
            int total = 0;
            int score = 5;

            for (int i = 1; i < info.length; i++) {
                if (map.containsKey(info[i])) {
                    total += map.get(info[i]) * score;
                }
                score--;
            }

            if (total > max || (total == max && answer.compareTo(info[0]) > 0)) {
                max = total;
                answer = info[0];
            }
        }
        return answer;
    }
}
