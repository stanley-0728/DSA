
/*
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
Example 1:

Input: s = "abc", t = "ahbgdc"
Output: true
Example 2:

Input: s = "axc", t = "ahbgdc"
Output: false
 */

import java.util.Arrays;

public class Subsquence {
  public boolean isSubsequence(String s, String t) {
    Boolean[][] dp = new Boolean[t.length() + 1][s.length() + 1];
    return recursion(t.length(), s.length(), t, s, dp);
    // return tabulation(t, s);
  }

  boolean recursion(int i, int j, String s, String t, Boolean[][] dp) {
    if (j == 0)
      return true;
    if (i == 0)
      return false;
    if (dp[i][j] != null)
      return dp[i][j];
    boolean notPick = recursion(i - 1, j, s, t, dp);
    boolean pick = false;
    if (s.charAt(i - 1) == t.charAt(j - 1)) {
      pick = recursion(i - 1, j - 1, s, t, dp);
    }
    return dp[i][j] = pick | notPick;
  }

  boolean tabulation(String s, String t) {
    boolean[][] dp = new boolean[s.length() + 1][t.length() + 1];
    for (int i = 0; i < s.length() + 1; i++)
      dp[i][0] = true;
    for (int i = 1; i < s.length() + 1; i++) {
      for (int j = 1; j < t.length() + 1; j++) {
        boolean notPick = dp[i - 1][j];
        boolean pick = false;
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          pick = dp[i - 1][j - 1];
        }
        dp[i][j] = pick | notPick;
      }
    }
    return dp[s.length()][t.length()];
  }

  boolean spaceOptimized(String s, String t) {
    boolean[] prev = new boolean[t.length() + 1];
    boolean[] curr = new boolean[t.length() + 1];
    prev[0] = true;
    for (int i = 1; i < s.length() + 1; i++) {
      curr[0] = true;
      for (int j = 1; j < t.length() + 1; j++) {
        boolean notPick = prev[j];
        boolean pick = false;
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          pick = prev[j - 1];
        }
        prev[j] = pick | notPick;
      }
      prev = Arrays.copyOf(curr, curr.length);
    }
    return prev[t.length()];
  }
}
