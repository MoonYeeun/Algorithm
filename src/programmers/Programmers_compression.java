package programmers;

import java.util.ArrayList;
import java.util.HashMap;

//2018 카카오 3차 압축
public class Programmers_compression {
    public static void main(String[] args) {
        String msg = "ABABABABABABABAB";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        HashMap<String ,Integer> dic = new HashMap<>(); // 사전
        ArrayList<Integer> list = new ArrayList<>(); // 색인 번호 담을 리스트
        for(int i = 1 ; i <= 26 ; i++){
            dic.put(Character.toString(alphabet.charAt(i-1)), i);
        }
        int i = 0;
        while (msg.length() != 0){
            String str = msg.substring(0,msg.length()-i); // 끝에서 하나씩 잘라가면서 사전에 등록되어 있나 비교
            if(dic.containsKey(str)){
                list.add(dic.get(str));
                if(msg.length()-i+1 < msg.length())
                    dic.put(msg.substring(0, msg.length()-i+1),dic.size()+1);
                msg = msg.replaceFirst(str, "");
                i = 0;
                continue;
            }
            i++;
        }
        int[] answer = new int[list.size()];
        int size = 0;
        for(Integer num : list){
            answer[size++] = num;
            System.out.println(num);
        }
    }
}
