package class02;

import java.util.HashMap;
import java.util.Map;

public class App_2 {

    public static void main(String[] args) {
            int N = 4;
            int start = 2;
            int aim = 4;
            int K = 4;
            int[][] dp = new int[N + 1][K + 1]; // dp[cur = 当前位置][rest = 剩余步数];
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[0].length; j++) {
                    dp[i][j] = -1;
                }
            }
            System.out.println(process(N,start,aim,K, dp));
    }

    static int process(int N, int start, int aim, int K, int[][] dp) {
           if(dp[start][K] != -1) return dp[start][K];
           if (K == 0) {
              if(start == aim) {
                  return 1;
              } else {
                  return 0;
              }
           }

           int r = 0;

           if (start == 1) {
              r = process(N, 2, aim, K - 1, dp);
           } else if (start == N) {
              r =  process(N, N - 1,aim, K - 1, dp);
           } else {
              r =process(N,start - 1, aim, K-1,dp) + process(N,start + 1,aim,K - 1,dp);
           }
           return dp[start][K] = r;
    }
}
