
import java.util.Arrays;

/*
 * Given two strings s and t, return the number of distinct subsequences of s which equals t.
The test cases are generated so that the answer fits on a 32-bit signed integer.
Example 1:
Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabbbit
rabbbit
rabbbit
Example 2:

Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
babgbag
babgbag
babgbag
babgbag
babgbag
 
 */
public class DistinctSubSquence {
  public int numDistinct(String s, String t) {
    int[][] dp = new int[s.length() + 1][t.length() + 1];
    for (int i = 0; i < s.length() + 1; i++)
      Arrays.fill(dp[i], -1);
    return memo(s.length() - 1, t.length() - 1, s, t, dp); // for tabulation we need to follow 1 based indexing
  }

  static int recursion(int index1, int index2, String s, String t) {
    if (index2 == 0)
      return 1;
    if (index1 == 0)
      return 0;
    if (s.charAt(index1 - 1) == t.charAt(index2 - 1))
      return recursion(index1 - 1, index2 - 1, s, t) + recursion(index1 - 1, index2, s, t);
    else {
      return recursion(index1 - 1, index2, s, t);
    }
  }

  static int memo(int index1, int index2, String s, String t, int[][] dp) {
    if (index2 == 0)
      return 1;
    if (index1 == 0)
      return 0;
    if (dp[index1][index2] != -1)
      return dp[index1][index2];
    if (s.charAt(index1 - 1) == t.charAt(index2 - 1))
      return dp[index1][index2] = memo(index1 - 1, index2 - 1, s, t, dp) + memo(index1 - 1, index2, s, t, dp);
    else {
      return dp[index1][index2] = memo(index1 - 1, index2, s, t, dp);
    }
  }

  public static int tabulation(String s, String t) {
    int[][] dp = new int[s.length() + 1][t.length() + 1];
    for (int i = 0; i < s.length() + 1; i++)
      dp[i][0] = 1;
    for (int i = 1; i <= s.length(); i++) {
      for (int j = 1; j <= t.length(); j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1))
          dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
        else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }
    return dp[s.length()][t.length()];
  }

  public static int spaceOptimized(String s, String t) {
    int[] prev = new int[t.length() + 1];
    int[] curr = new int[t.length() + 1];

    prev[0] = 1;
    curr[0] = 1;
    for (int i = 1; i <= s.length(); i++) {
      for (int j = 1; j <= t.length(); j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1))
          curr[j] = prev[j - 1] + prev[j];
        else {
          curr[j] = prev[j];
        }
      }
      prev = Arrays.copyOf(curr, curr.length);
    }
    return prev[t.length()];
  }
}
