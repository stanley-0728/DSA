package DP.SubSquencePattern.Knapsack;

import java.util.Arrays;

public class OnesZeros {
  public int findMaxForm(String[] s, int m, int n) {
    int[][][] dp = new int[s.length ][m + 1][n + 1];
    for (int[][] s1 : dp) {
      for (int[] s2 : s1)
        Arrays.fill(s2, -1);
    }
    return recursion(s.length-1, m, n, s, dp);
  }

  int recursion(int i, int m, int n, String[] s, int[][][] dp) {
    if ((m == 0 && n == 0) || i < 0)
      return 0;
    if (dp[i][m][n] != -1)
      return dp[i][m][n];
    int a = 0, b = 0;
    for (int c = 0; c < s[i].length(); c++) {
      if (s[i].charAt(c) == '0')
        a++;
    }
    b = s[i].length() - a;
    int notPick = recursion(i - 1, m, n, s, dp);
    int pick = 0;
    if (m - a >= 0 && n - b >= 0)
      pick = 1 + recursion(i - 1, m - a, n - b, s, dp);
    return dp[i][m][n] = Math.max(notPick, pick);
  }
}
