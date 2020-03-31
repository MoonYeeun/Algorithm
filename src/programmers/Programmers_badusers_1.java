package programmers;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 2019 카카오 인턴 모의고사 불량 사용자
// 비트마스킹 이용
public class Programmers_badusers_1 {
    static HashSet<Integer> set = new HashSet<>();
    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        //String[] banned_id = {"fr*d*", "abc1**"};
        //String[] banned_id = {"*rodo", "*rodo", "******"};
        String[] banned_id = {"fr*d*", "*rodo", "******", "******"};

        for(int i = 0 ; i < banned_id.length ; i++) {
            banned_id[i] = banned_id[i].replace("*", ".");
        }

        find(banned_id, user_id, 0, 0);
        int answer = set.size();
        System.out.println(answer);
    }
    static void find(String[] banned_id, String[] user_id, int idx, int bit) {
        if(idx == banned_id.length) {
            set.add(bit);
            return;
        }

        Pattern idPattern = Pattern.compile(banned_id[idx]);

        for(int i = 0 ; i < user_id.length; i++) {
            Matcher matcher = idPattern.matcher(user_id[i]);
            // 이미 선택한 경우 or 패턴에 맞지 않는 경우
            if(((1<<i) & bit) == (1<<i) || !matcher.matches()) continue;

            find(banned_id, user_id, idx + 1, (1<<i) | bit);
        }
    }
}
