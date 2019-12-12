package programmers;

import java.util.*;

//프로그래머스 2020카카오블라인드채용 문자열압축
public class Programmers_stringcompression {
    public static void main(String[] args) {
        //String s = "aabbaccc";
        //String s = "ababcdcdababcdcd";
        String s = "abcabcdede";
//        String s = "abcabcabcabcdededededede";
//        String s = "xababcdcdababcdcd";

        int answer = s.length(); // 압축 후 최소 문자열 길이
        ArrayList<pair> list = new ArrayList<>(); // 압축할 문자열 담긴
        //문자열 1개부터 s길이의 반까지 압축
        for(int i = 1 ; i <= s.length()/2; i++){
            String temp = s.substring(0,i); // 압축할 문자열
            String str = s; // 비교 문자열
            String recent_str = s; // 남은 문자열
            int idx = 0; // 압축 문자열 저장된 idx
            int j = 0;

            while(recent_str.length() != 0){
                if(recent_str.length() < i){
                    list.add(new pair(recent_str,1));
                    break;
                }
                str = recent_str.substring(0,i);
                System.out.println("현재 "+ str);
                if(temp.equals(str)){
                    if(j == 0){
                        list.add(new pair(temp,1));
                    }
                    else
                        list.set(idx,new pair(temp,list.get(idx).num+1));
                }
                else{
                    list.add(new pair(str,1));
                    idx++;
                    temp = str;
                }
                j += i;
                recent_str = s.substring(j);
            }
            StringBuilder sb = new StringBuilder();
            for(pair pair : list){
                if(pair.num == 1)
                    sb.append(pair.str);
                else{
                    sb.append(pair.num);
                    sb.append(pair.str);
                }
            }
            System.out.println(sb);
            answer = Math.min(answer,sb.length());
            list.clear();
        }
        System.out.println(answer);
    }
    static class pair{
        String str;
        int num;
        pair(String str, int num){
            this.str = str;
            this.num = num;
        }
    }
}
