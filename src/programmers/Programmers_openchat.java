package programmers;

import java.util.ArrayList;
import java.util.HashMap;

//프로그래머스 2019카카오 블라인드 채용 / 오픈채팅방
public class Programmers_openchat {
    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi","Enter uid4567 Prodo",
        "Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        HashMap<String,String> user_map = new HashMap<>(); //userId , 닉네임
        ArrayList<String> result = new ArrayList<>(); // 최종 메세지 담을 리스트

        for(int i = 0 ; i < record.length ; i++){
            String[] user = record[i].split(" ");

            if(user[0].equals("Leave")){
                result.add(user[1]+"/님이 나갔습니다.");
                continue;
            }
            user_map.put(user[1],user[2]);
            if(user[0].equals("Enter")){
                result.add(user[1]+"/님이 들어왔습니다.");
            }
        }
        String[] answer = new String[result.size()];
        for(int i = 0 ; i < result.size() ; i++){
            String[] temp = result.get(i).split("/"); //UserId 와 입장 상태 구분
            temp[0] = user_map.get(temp[0]);
            result.set(i,temp[0]+temp[1]);
        }
        for(int i = 0; i < answer.length ; i++){
            answer[i] = result.get(i);
            System.out.println(result.get(i));
        }
    }
}

