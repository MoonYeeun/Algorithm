package programmers;

import java.util.HashSet;

// 프로그래머스 영어 끝말잇기
public class Programmers_EnglishWordChain {
    public static void main(String[] args) {
        //int n = 3;
        //int n = 5;
        int n = 2;
//        String[] words = {"tank", "kick", "know", "wheel", "land", "dream",
//                "mother", "robot", "tank"};
//        String[] words = {"hello", "observe", "effect", "take", "either", "recognize",
//                    "encourage", "ensure", "establish", "hang", "gather",
//                    "refer", "reference", "estimate", "executive"};
        //String[] words = {"hello", "one", "even", "never", "now", "world", "draw"};
        String[] words = {"qwe", "eqwe", "eqwe"};

        HashSet<String> set = new HashSet<>();
        int[] answer = {0, 0};

        for(int i = 0 ; i < words.length ; i++) {

            if(i == 0) {
                set.add(words[i]);
                continue;
            }
            // 이미 존재하는 단어 말한 경우 || 이미 존재하는 단어 말한 경우
            if(set.contains(words[i]) ||
                    !words[i-1].endsWith(Character.toString(words[i].charAt(0)))) {
                answer[0] = (i % n) + 1;
                answer[1] = i / n + 1;
                break;
            }
            set.add(words[i]);
        }

        for(int num : answer) {
            System.out.println(num);
        }
    }
}
