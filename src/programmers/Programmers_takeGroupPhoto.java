package programmers;

import java.util.HashMap;

// 프로그래머스 단체사진 찍기
// 완탐(순열 계산) + 조건 맞는지 확인
public class Programmers_takeGroupPhoto {
    static int[] arr;
    static HashMap<Character, Integer> map = new HashMap<>();
    static int answer = 0;
    public static void main(String[] args) {
        int n = 2;
        String[] data = {"N~F=0", "R~T>2"};
        //String[] data = {"M~C\\<2", "C~M>1"};

        // 각 프렌즈 알파벳 숫자로 치환하여 계산
        map.put('A', 0);
        map.put('C', 1);
        map.put('F', 2);
        map.put('J', 3);
        map.put('M', 4);
        map.put('N', 5);
        map.put('R', 6);
        map.put('T', 7);

        arr = new int[8];
        // 줄 세우는 순열 구하기
        per(0, data, 0);
        System.out.println(answer);
    }
    static void per(int loc, String[] data, int bit) {
        // 줄 다 세운 경우 : 조건에 맞는지 확인
        if(loc == 8) {
            for(int i = 0 ; i < data.length ; i++) {
                Character op = data[i].charAt(3);
                int num = data[i].charAt(4) - '0';
                int f1 = arr[map.get(data[i].charAt(0))];
                int f2 = arr[map.get(data[i].charAt(2))];

                if(op == '<') {
                    if(Math.abs(f1 - f2) -1 >= num) return;
                }
                if(op == '>') {
                    if(Math.abs(f1 - f2) -1 <= num) return;
                }
                if(op == '=')
                    if(Math.abs(f1 - f2) -1 != num) return;
            }
            answer++;
            return;
        }
        for(int i = 0 ; i < 8 ; i++) {
            if((bit & (1<<i)) == (1<<i)) continue;
            arr[loc] = i;
            per(loc+1, data, bit | (1<<i));
        }
    }
}
