package not_solve_myself;

// 2017 카카오 예선 4단고음
// 재귀로 만들 수 있는 경우의 수 탐색
// 1. 끝은 무조건 ++ 이어야 함
// 2. n 부터 시작해서 숫자 줄여나가기
//  - n이 3으로 나누어 떨어지고 + 가 2개 이상이면 * 가능
public class Programmers_4highnote {
    public static void main(String[] args) {
//        int n = 15;
//        int n = 24;
//        int n = 41;
        int n = 2147483647;
        System.out.println(solution(n));
    }
    static int solution(int n) {
        // 3단 고음이기 위해서는 끝이 무조건 ++ ! -> n - 2 부터 시작
        return recur(n-2, 2);
    }
    static int recur(int n, int plus) {
        // n이 Math.pow(3, 현재 덧셈개수/2) 보다 커야 최소 3단고음 만들기 가능
        if(Math.pow(3, plus/2) > n) return 0;

        int cnt = 0;

        if(n == 3) {
            if(plus == 2) return 1;
            else return 0;
        }
        else {
            // + 개수가 2 이상이고 n이 3으로 나누어 떨어지는 경우 : * 가능
            if(plus >= 2 && n % 3 == 0) cnt += recur(n/3, plus - 2);

            cnt += recur(n-1, plus + 1);
        }

        return cnt;
    }
}
