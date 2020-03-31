package programmers;

import java.util.Stack;

// 2019 카카오 인턴 모의고사 크레인 인형뽑 게임
public class Programmers_PickDolls {
    static Stack<Integer> stack = new Stack<>();
    static int answer = 0;
    public static void main(String[] args) {
        int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
        int[] moves = {1,5,3,5,1,2,1,4};

        for(int idx : moves) {
            pick(board, idx - 1);
        }
        System.out.println(answer);
    }
    static void pick(int[][] board, int idx) {
        for(int i = 0 ; i < board.length ; i++) {
            if(board[i][idx] == 0) continue;

            if(!stack.isEmpty() && stack.peek() == board[i][idx]) {
                answer += 2;
                stack.pop();
                board[i][idx] = 0;
                break;
            }
            stack.push(board[i][idx]);
            board[i][idx] = 0;
            break;
        }
    }
}
