package LeetCode;

// 394. Decode String
// 재귀 혹은 스택
public class LC394_Decode_String {
    static int cur = 0;

    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        System.out.println(decodeString(s));
    }

    public static String decodeString(String s) {
        return cal(0, s);
    }

    public static String cal(int idx, String s) {
        int k = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = idx; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '[') {
                String result = cal(i + 1, s).repeat(k);
                sb.append(result);
                i = cur;
                k = 0;
            } else if (c == ']') {
                cur = i;
                return sb.toString();
            } else if (Character.isDigit(c)) k = (k * 10) + (c - '0');
            else sb.append(c);
        }
        return sb.toString();
    }
}
