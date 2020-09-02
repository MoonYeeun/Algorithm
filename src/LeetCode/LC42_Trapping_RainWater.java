package LeetCode;

import java.util.*;

// 스택
// 현재보다 작은 값 -> 스택 넣기
// 현재 값이 스택의 최상위 값보다 클 때 -> 자신보다 큰 값 나올 때까지 스택 값 빼면서 고인물 계산
public class LC42_Trapping_RainWater {
    public static void main(String[] args) {
        int[] t = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int result = trap(t);
        System.out.println(result);
    }

    static public int trap(int[] height) {
        Stack<Integer> st = new Stack<>();
        int cur = 0;
        int answer = 0;

        while (cur < height.length) {
            while (!st.isEmpty() && height[cur] > height[st.peek()]) {
                int top = st.pop();
                if (st.isEmpty()) break; // 물 고일 수 X

                int dis = cur - st.peek() - 1;
                int h = Math.min(height[cur], height[st.peek()]) - height[top];
                answer += dis * h;
            }
            st.push(cur++);
        }
        return answer;
    }
}
