package programmers;

// 프로그래머스 위클리 챌린지5 - 모음사전
public class Programmers_vowelDictionary {
    static String alpha = "AEIOU";
    static boolean flag;
    static int answer = 0;

    public static void main(String[] args) {
        //String word = "AAAAE";
        String word = "AAAE";
        System.out.println(solution(word));
    }

    public static int solution(String word) {
        makeWord(word, "");
        return answer;
    }

    public static void makeWord(String target, String word) {
        if (word.equals(target)) {
            flag = true;
            return;
        }
        if (word.length() == 5) return;

        for (int i = 0; i < 5; i++) {
            answer++;
            makeWord(target, word + alpha.charAt(i));
            if (flag) return;
        }
    }
}
