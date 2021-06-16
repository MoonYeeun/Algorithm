package programmers;

// 2021 카카오 블라인드 채용 - 신규아이디 추천
// 정규식을 잘 활용하기
public class Programmers_recommend_newId {
    public static void main(String[] args) {
        String newId = "...!@BaT#*..y.abcdefghijklm";
        System.out.println(solution(newId));
    }

    public static String solution(String new_id) {
        // 1, 2, 3 단계
        StringBuilder sb = new StringBuilder();
        sb.append(new_id.toLowerCase()
                .replaceAll("[^-_.a-z0-9]", "")
                .replaceAll("[.]{2,}", ".")
                .replaceAll("^[.][.]$", ""));

        // 4단계
        if (sb.length() != 0 && sb.charAt(0) == '.') sb.deleteCharAt(0);
        if (sb.length() != 0 && sb.charAt(sb.length() - 1) == '.') sb.deleteCharAt(sb.length() - 1);
        // 5단계
        if (sb.length() == 0) sb.append("a");
        // 6단계
        if (sb.length() >= 16) {
            sb.delete(15, sb.length());
            if (sb.charAt(14) == '.') sb.deleteCharAt(14);
        }
        // 7단계
        if (sb.length() <= 2) {
            while (sb.length() < 3) {
                sb.append(sb.charAt(sb.length() - 1));
            }
        }
        return sb.toString();
    }
}
