package LeetCode.LC1011;

// 1011. Capacity To Ship Packages Within D Days
// 이분탐색
public class Capacity_To_Ship_Packages_Within_D_Days {
    public static void main(String[] args) {
        //int[] weights = {1,2,3,4,5,6,7,8,9,10};
        int[] weights = {3, 2, 2, 4, 1, 4};
        //int D = 5;
        int D = 3;

        System.out.println(shipWithinDays(weights, D));
    }

    public static int shipWithinDays(int[] weights, int D) {
        int ans = 0;
        int total = 0;

        for (int w : weights) {
            total += w;
        }

        int s = 0;
        int e = total;

        while (s <= e) {
            int mid = (s + e) / 2;

            if (isValid(weights, D, mid)) {
                e = mid - 1;
                ans = mid;
            } else s = mid + 1;
        }
        return ans;
    }

    public static boolean isValid(int[] weights, int d, int max) {
        int sum = 0;
        int cnt = 1;

        for (int w : weights) {
            sum += w;

            if (sum > max) {
                sum = w;
                cnt++;

                if (sum > max) return false;
            }
            if (cnt > d) return false;
        }
        return true;
    }
}
