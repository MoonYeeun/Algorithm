package LeetCode;

// 투포인터
public class LC838_Push_Dominoes {
    public static void main(String[] args) {
        //String dominoes = ".L.R...LR..L..";
        String dominoes = "RR.L";
        System.out.println(pushDominoes(dominoes));
    }

    public static String pushDominoes(String dominoes) {
        char[] result = dominoes.toCharArray();
        int len = dominoes.length();
        int left = 0;
        int right = 0;

        while (left < len) {
            while (right < len && result[right] == '.') {
                right++;
            }

            if (right == len) {
                if (result[left] == 'R') {
                    for (int i = left; i < len; i++) {
                        result[i] = 'R';
                    }
                }
                break;
            }

            if ((result[left] == '.' && result[right] == 'L') || result[left] == result[right]) {
                for (int i = left; i < right; i++) {
                    result[i] = result[right];
                }
            } else if (result[left] == 'R' && result[right] == 'L') {
                int i = left;
                int j = right;

                while (i < j) {
                    result[i++] = 'R';
                    result[j--] = 'L';
                }
            }

            left = right;
            right = left + 1;
        }
        return new String(result);
    }
}
