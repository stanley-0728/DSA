package DP.LinearDp;

import java.util.Arrays;

public class DecodeWays {
  public int numDecodings(String s) {
    int[] dp = new int[s.length()];
    Arrays.fill(dp, -1);
    return recursions(0, s, dp);

  }

  int recursions(int i, String s) {
    if (i == s.length())
      return 1;
    if (s.charAt(i) == '0')
      return 0;
    int ways = 0;
    ways += recursions(i + 1, s);
    if (i + 1 <= s.length() - 1) {
      int num = Integer.parseInt(s.substring(i, i + 2));

      if (num >= 10 && num <= 26) {
        ways += recursions(i + 2, s);
      }
    }
    return ways;

  }

  int recursions(int i, String s, int[] dp) {
    if (i == s.length())
      return 1;
    if (s.charAt(i) == '0')
      return 0;
    if (dp[i] != -1)
      return dp[i];
    int ways = 0;
    ways += recursions(i + 1, s, dp);
    if (i + 1 <= s.length() - 1) {
      int num = Integer.parseInt(s.substring(i, i + 2));
      if (num >= 10 && num <= 26) {
        ways += recursions(i + 2, s, dp);
      }
    }
    return dp[i] = ways;

  }
}
