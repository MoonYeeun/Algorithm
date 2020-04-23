package boj;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 백준 9996 한국이 그리울 땐 서버에 접속하지
public class Back_9996 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str = sc.next();
        str = str.replace("*", "[a-z]*");

        Pattern pattern = Pattern.compile(str);

        for(int i = 0 ; i < n ; i++) {
            Matcher matcher = pattern.matcher(sc.next());
            if(matcher.matches())
                System.out.println("DA");
            else
                System.out.println("NE");
        }
    }
}
