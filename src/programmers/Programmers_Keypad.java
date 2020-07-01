package programmers;

// 카카오 키패드 누르기
public class Programmers_Keypad {
    public static void main(String[] args) {
        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
//        int[] numbers = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
//        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        String hand = "right";
//        String hand = "left";
//        String hand = "right";

        System.out.println(solution(numbers, hand));
    }
    public static String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();

        // 각 손의 초기 위치
        pair leftH = cal("*");
        pair rightH = cal("#");

        for(int i : numbers) {
            // 왼손
            if(i == 1 || i == 4 || i == 7) {
                sb.append("L");
                leftH = cal(String.valueOf(i));
            }
            // 오른손
            else if(i == 3 || i == 6 || i == 9) {
                sb.append("R");
                rightH = cal(String.valueOf(i));
            }
            else {
                pair num = cal(String.valueOf(i));
                int rightDis = Math.abs(num.x - rightH.x) + Math.abs(num.y - rightH.y);
                int leftDis = Math.abs(num.x - leftH.x) + Math.abs(num.y - leftH.y);

                if(rightDis < leftDis || (leftDis == rightDis && hand.equals("right"))) {
                    sb.append("R");
                    rightH = num;
                }
                else if(leftDis < rightDis || (leftDis == rightDis && hand.equals("left"))) {
                    sb.append("L");
                    leftH = num;
                }
            }
        }
        return sb.toString();
    }
    static pair cal(String num) {
        if(num.equals("0")) return new pair(3, 1);
        else if(num.equals("1")) return new pair(0, 0);
        else if(num.equals("2")) return new pair(0, 1);
        else if(num.equals("3")) return new pair(0, 2);
        else if(num.equals("4")) return new pair(1, 0);
        else if(num.equals("5")) return new pair(1, 1);
        else if(num.equals("6")) return new pair(1, 2);
        else if(num.equals("7")) return new pair(2, 0);
        else if(num.equals("8")) return new pair(2, 1);
        else if(num.equals("9")) return new pair(2, 2);
        else if(num.equals("*")) return new pair(3, 0);
        else return new pair(3,2);
    }
    static class pair {
        int x, y;

        pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
