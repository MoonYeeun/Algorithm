package not_solve_myself;

import java.util.HashSet;
//2019 카카오 후보키
/* 비트 마스킹 개념 참조 */
public class Programmers_candidateKey {
    public static void main(String[] args) {
        String[][] relation = {
                {"100","ryan","music","2"},
                {"200","apeach","math","2"},
                {"300","tube","computer","3"},
                {"400","con","computer","4"},
                {"500","muzi","music","3"},
                {"600","apeach","music","2"}
        };
        int answer = 0;
        int col = relation[0].length; //튜플 개수
        int row = relation.length; //row 개수
        HashSet<String> set = new HashSet<>();
        HashSet<Integer> candidate = new HashSet<>();

        // 비트마스킹 이용하여 후보키가 나올 수 있는 부분집합 만들기 ( 00000001 ~ 11111111)
        for(int bit = 1 ; bit < (1<<col) ; bit++){
            for(int i = 0 ; i < row ; i++) {
                StringBuilder sb = new StringBuilder();
                String str = "";
                for (int j = 0; j < col; j++) {
                    // bit 켜진 부분들만 데이터 가져오기
                    if((bit & (1<<j)) != 0) {
                        sb.append(relation[i][j]);
                    }
                }
                set.add(sb.toString());
            }
            //중복된 값 없을 경우
            if (set.size() == row){
                minimailty_Check(candidate,bit);
            }
            set.clear();
        }
        answer = candidate.size();
        System.out.println(answer);
    }
    static void minimailty_Check(HashSet<Integer> candidiate , int bit){
        for(int key : candidiate){
            if((key & bit) == key) return;
        }
        candidiate.add(bit);
    }
}
