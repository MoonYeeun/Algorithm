package programmers;

import java.util.ArrayList;

//프로그래머스 2018 카카오채용 n진수게임
public class Programmers_nnumgame {
    public static void main(String[] args) {
        //int n = 2;// 진법
        int n = 16;
        //int t = 4; // 미리 구할 숫자의 개수
        int t = 16;
        //int t = 16;
        int m = 2;// 게임에 참가하는 인원
        //int p = 1;// 튜브의 순서
        int p = 2;

        String answer ="";
        char[] number = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        ArrayList<String> list = new ArrayList<>();

        for(int i = 0 ; i <= m*1000; i++){
            int remainder = 0;
            int quo = i;
            String str = "";

            if(quo < n) {
                list.add(String.valueOf(number[quo%n]));
                continue;
            }
            StringBuilder temp = new StringBuilder();
            while(quo >= n){
                remainder = (quo % n);
                quo = quo/n;
                temp.append(number[remainder]);

                if(quo < n)
                    temp.append(number[quo]);
            }
            str = temp.toString();
            for(int j = str.length()-1; j >= 0 ; j--){
                list.add(String.valueOf(str.charAt(j)));
            }
        }

        StringBuilder sb = new StringBuilder();
        int idx = p-1;
        while(sb.length() < t){
            sb.append(list.get(idx));
            idx += m;
        }
        answer = sb.toString();
        System.out.println(answer);
    }
}

