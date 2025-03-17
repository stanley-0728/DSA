package DP.FibonacciPattern;

import java.util.Arrays;

public class Fibonacci {
  public static void main(String[] args) {
    // Give me the fibo number of 4
    // System.out.println(findFibo(4)); T.C exponential and auxiallry stack space
    ;
    // So if u draw tree diagram you can f(2) is been calaculated again whenever
    // there is overlapping subproblems due dp
    // int[] dp = new int[4 + 1];
    // Arrays.fill(dp, -1);
    // System.out.println(findFiboMemo(4, dp)); // T.C o(n) S.C o(n)+o(n)

    // System.out.println(tabulationFibo(4)); // T.C o(n) and S.C o(n)

    System.out.println(spaceOptimizedfibo(4)); // T.C o(n) and S.C o(1)
  }

  static int findFibo(int n) {
    if (n <= 1)
      return n;
    return findFibo(n - 1) + findFibo(n - 2);
  }

  static int findFiboMemo(int n, int[] dp) {
    if (n <= 1)
      return n;
    if (dp[n] != -1)
      return dp[n];
    return dp[n] = findFibo(n - 1) + findFibo(n - 2);
  }

  static int tabulationFibo(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 0;
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
  }

  static int spaceOptimizedfibo(int n) {
    int prev1 = 1;
    int prev2 = 0;
    int curr = 0;
    for (int i = 2; i <= n; i++) {
      curr = prev1 + prev2;
      prev2 = prev1;
      prev1 = curr;

    }
    return prev1;
  }
}
