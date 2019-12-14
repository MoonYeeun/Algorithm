package programmers;
//프로그래머스 큰 수 만들기
public class Programmers_makebiggernum {
    public static void main(String[] args) {
        //String number = "1924";
        String number = "1231234";
        //String number = "4177252841";
        //int k = 2;
        int k = 3;
        //int k = 4;
        String answer = "";
        int num = number.length() - k; // 고를 수 있는 자리수

        int cnt = 0;
        int start = 0;
        int end = k;
        StringBuilder sb = new StringBuilder();
        while (cnt < num){
            cnt++;
            int max = 0;
            for(int i = start ; i <= end ; i++){
                if(max < number.charAt(i)-'0'){
                    max = number.charAt(i) - '0';
                    start = i+1;
                }
            }
            end++;
            sb.append(max);
        }
        answer = sb.toString();
        System.out.println(answer);
    }
}
