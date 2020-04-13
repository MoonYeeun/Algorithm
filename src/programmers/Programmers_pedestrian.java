package programmers;

// 카카오예선 보행자천국
//dp
public class Programmers_pedestrian {
    static int MOD = 20170805;

    public static void main(String[] args) {
        int m = 3;
        int n = 3;
//        int m = 3;
//        int n = 6;
        int[][] city_map = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        //int[][] city_map = {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};

        int[][] right = new int[m+1][n+1];
        int[][] down = new int[m+1][n+1];

        right[1][1] = 1;
        down[1][1] = 1;

        for(int i = 1 ; i <= m ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                if(i == 1 && j == 1) continue;

                if(city_map[i-1][j-1] == 0) {
                    right[i][j] = (right[i-1][j] + down[i][j-1]) % MOD;
                    down[i][j] = (right[i-1][j] + down[i][j-1]) % MOD;
                }
                if(city_map[i-1][j-1] == 2) {
                    right[i][j] = right[i-1][j] % MOD;
                    down[i][j] = down[i][j-1] % MOD;
                }
            }
        }
        System.out.println(right[m][n]);
    }
}
