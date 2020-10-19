package LeetCode;

import java.util.*;

public class LC135_candy {
    public static void main(String[] args) {
        int[] ratings = {1, 0, 2};
        int result = candy(ratings);
        System.out.println(result);
    }

    static public int candy(int[] ratings) {
        int[] candy = new int[ratings.length];
        Arrays.fill(candy, 1);

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i - 1] < ratings[i] && candy[i] <= candy[i - 1]) candy[i] = candy[i - 1] + 1;
        }

        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && candy[i] <= candy[i + 1]) candy[i] = candy[i + 1] + 1;
        }

        int ans = 0;
        for (int i = 0; i < ratings.length; i++) {
            ans += candy[i];
        }

        return ans;
    }
}
