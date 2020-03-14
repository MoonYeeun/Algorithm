package programmers;

import java.util.Arrays;

// 2018 카카오 3차 자동완성
public class Programmers_autocomplete {
    public static void main(String[] args) {
        String[] words = {"go","gone","guild"};
//        String[] words = {"abc","def","ghi","jklm"};
//        String[] words = {"word","war","warrior","world"};
        int answer = 0;
        Arrays.sort(words);

        for(int i = 0 ; i < words.length ; i++) {
            // 바로 다음 단어와 비교
            if(i == 0) {
                for(int j = 1 ; j < words[i].length() ; j++) {
                    if(words[i+1].indexOf(words[i].substring(0,j)) == 0) answer++;
                    else break;
                }
                answer++;
            } // 바로 전 단어와 비교
            else if(i == words.length -1) {
                for(int j = 1 ; j < words[i].length() ; j++) {
                    if(words[i-1].indexOf(words[i].substring(0,j)) == 0) answer++;
                    else break;
                }
                answer++;
            } // 바로 전, 바로 다음 단어와 비교
            else {
                for(int j = 1 ; j < words[i].length() ; j++) {
                    if(words[i+1].indexOf(words[i].substring(0,j)) == 0
                            || words[i-1].indexOf(words[i].substring(0,j)) == 0) answer++;
                    else break;
                }
                answer++;
            }
        }
        System.out.println(answer);
    }
}
