package programmers;

import java.util.Arrays;

public class Programmers_HIndex {
    public static void main(String[] args) {
        //int[] citations = {3, 0, 6, 1, 5};
        int[] citations = {22,42};
        Arrays.sort(citations);
        int idx = citations[citations.length/2];
        int answer = 0;
        int size = citations.length;

        while (true) {
            int num  = size - citations.length/2; //인용된 논문 갯수
            for(int i = 0 ; i < citations.length/2 ; i++){
                if(idx <= citations[i])
                    num++;
            }
            if(num >= idx && size - num <= idx){
                answer = idx;
                break;
            }
            idx--;
        }
        System.out.println(answer);
    }
}
