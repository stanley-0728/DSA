package DP.FibonacciPattern;

import java.util.Arrays;

public class ClimbingStairs {
  public static void main(String[] args) {
    int n = 44;
    int[] dp = new int[n + 1];
    Arrays.fill(dp, -1);
    System.out.println(memo(n, dp));
  }

  // T.C expoential and S.C o(n)
  public int climbStairs(int n) {
    if (n == 0)
      return 1;
    int left = climbStairs(n - 1);
    int right = 0;
    if (n - 2 >= 0)
      right = climbStairs(n - 2);
    return left + right;
  }

  // T.C o(n) and S.C o(n) +o(n)
  static int memo(int n, int[] dp) {
    if (n == 0)
      return 1;
    if (dp[n] != -1)
      return dp[n];
    int left = memo(n - 1, dp);
    int right = 0;
    if (n - 2 >= 0)
      right = memo(n - 2, dp);
    return dp[n] = left + right;
  }

  // T.C o(n) and space o(n)
  static int tabulationStatirs(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 1;
    for (int i = 1; i <= n; i++) {
      dp[i] = dp[i - 1];
      if (i - 2 >= 0)
        dp[i] += dp[i - 2];
    }
    return dp[n];
  }

  // T.C o(n) and S.C o(1)
  static int spaceOptimizedStairs(int n) {
    int prev2 = 0;
    int prev1 = 1;
    for (int i = 1; i <= n; i++) {
      int curr = prev1 + prev2;
      prev2 = prev1;
      prev1 = curr;
    }
    return prev1;
  }
}
