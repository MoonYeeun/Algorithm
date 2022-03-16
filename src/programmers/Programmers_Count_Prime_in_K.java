package programmers;

// 2022 카카오 k진수에서 소수 개수 구하기
// long 범위 주의 !
public class Programmers_Count_Prime_in_K {
    public static void main(String[] args) {
        //int n = 437674;
        int n = 110011;
        //int n = 524287;
        //int k = 3;
        int k = 10;
        //int k = 2;
        System.out.println(solution(n, k));
    }

    static public int solution(int n, int k) {
        int answer = 0;
        // k 진수로 변환
        String result = changeToK(n, k);

        String[] nums = result.split("0");
        for (String num : nums) {
            if (num == null || num.equals("")) continue;
            if (isPrime(Long.parseLong(num))) answer++;
        }
        return answer;
    }

    static public String changeToK(int num, int k) {
        StringBuilder sb = new StringBuilder();
        int remain = num;

        while (remain > k) {
            sb.append(remain % k);
            remain = remain / k;
        }
        return sb.append(remain).reverse().toString();
    }

    static boolean isPrime(long target) {
        if (target <= 1) return false;

        int max = (int) Math.sqrt(target);
        for (int i = 2; i <= max; i++) {
            if (target % i == 0) return false;
        }
        return true;
    }
}
