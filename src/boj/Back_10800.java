package boj;

import java.util.*;

// 백준 10800 컬러볼
// 공 크기 작은 것부터 정렬
// 잡을 수 있는 공 = (누적된 전체 값) - (현재 자신의 색깔과 같은 공 크기 누적 값)
// 같은 공 사이즈 일 때 처리
public class Back_10800 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ArrayList<ball> list = new ArrayList<>();
        int[] color = new int[n+1];

        for(int i = 0 ; i < n ; i++) {
            int c = sc.nextInt();
            int s = sc.nextInt();

            list.add(new ball(i, c, s));
        }
        Collections.sort(list, new Comparator<ball>() {
            @Override
            public int compare(ball ball, ball t1) {
                return ball.size - t1.size;
            }
        });

        int[] answer = new int[n];
        int total = 0;
        for(int i = 0, j = 0; i < n ; i++) {
            ball b1 = list.get(i);
            ball b2 = list.get(j);

            while (b2.size < b1.size) {
                total += b2.size;
                color[b2.color] += b2.size;

                b2 = list.get(++j);
            }
            answer[b1.idx] = total - color[b1.color];
        }

        for(int num : answer) {
            System.out.println(num);
        }
    }
    static class ball {
        int idx, color, size;

        ball (int idx, int color, int size) {
            this.idx = idx;
            this.color = color;
            this.size = size;
        }
    }
}
