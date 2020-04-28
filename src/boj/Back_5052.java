package boj;

import java.util.*;

// 백준 5052 전화번호 목록
// 정렬
public class Back_5052 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            boolean flag = true;

            String[] arr = new String[n];
            for(int i = 0 ; i < n ; i++) {
                arr[i] = sc.next();
            }
            // 사전 순 정렬
            Arrays.sort(arr, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            for(int i = 1 ; i < n ; i++) {
                if(arr[i].startsWith(arr[i-1])) {
                    flag = false;
                    break;
                }
            }
            if(flag) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
