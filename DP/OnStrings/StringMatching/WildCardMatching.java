
import java.util.Arrays;

/*Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).
Example 1:
Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:
Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.

Example 3:
Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 
 * 
 */
public class WildCardMatching {
  public boolean isMatch(String s, String p) {
    // return recursion(p.length() - 1, s.length() - 1, p, s);
    int[][] dp = new int[p.length() + 1][s.length() + 1];
    for (int i = 0; i < p.length() + 1; i++)
      Arrays.fill(dp[i], -1);
    return memo(p.length(), s.length(), p, s, dp); // convert this to 1 based indexing for tabulation
  }

  static boolean recursion(int index1, int index2, String s, String t) {
    if (index1 == 0 && index2 == 0)
      return true;
    if (index1 == 0 && index2 > 0)
      return false;
    if (index1 > 0 && index2 == 0) {
      for (int i = index1; i > 0; i--) {
        if (s.charAt(i - 1) != '*')
          return false;
      }
      return true;
    }
    if (s.charAt(index1 - 1) == t.charAt(index2 - 1) || s.charAt(index1 - 1) == '?')
      return recursion(index1 - 1, index2 - 1, s, t);
    else if (s.charAt(index1 - 1) == '*')
      return recursion(index1 - 1, index2, s, t) || recursion(index1, index2 - 1, s, t);
    else
      return false;
  }

  static boolean memo(int index1, int index2, String s, String t, int[][] dp) {
    if (index1 == 0 && index2 == 0)
      return true;
    if (index1 == 0 && index2 > 0)
      return false;
    if (index1 > 0 && index2 == 0) {
      for (int i = index1; i > 0; i--) {
        if (s.charAt(i - 1) != '*')
          return false;
      }
      return true;
    }
    if (dp[index1][index2] != -1)
      return dp[index1][index2] == 1;
    boolean result;
    if (s.charAt(index1 - 1) == t.charAt(index2 - 1) || s.charAt(index1 - 1) == '?')
      result = memo(index1 - 1, index2 - 1, s, t, dp);
    else if (s.charAt(index1 - 1) == '*')
      result = memo(index1 - 1, index2, s, t, dp) || memo(index1, index2 - 1, s, t, dp);
    else
      result = false;

    dp[index1][index2] = result ? 1 : 0;
    return result;
  }

  public static boolean tabulation(String s, String t) {
    boolean[][] dp = new boolean[s.length() + 1][t.length() + 1];
    dp[0][0] = true;
    for (int j = 1; j < t.length() + 1; j++)
      dp[0][j] = false;
    for (int i = 1; i <= s.length(); i++) {
      boolean f = true;
      for (int ii = 1; ii <= i; ii++) {
        if (s.charAt(ii - 1) != '*') {
          f = false;
          break;
        }
      }
      dp[i][0] = f;
    }
    for (int i = 1; i <= s.length(); i++) {
      for (int j = 1; j <= t.length(); j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1) || s.charAt(i - 1) == '?')
          dp[i][j] = dp[i - 1][j - 1];
        else if (s.charAt(i - 1) == '*') {
          dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
        } else
          dp[i][j] = false;
      }
    }
    return dp[s.length()][t.length()];
  }

  public static boolean spaceOptimized(String s, String t) {
    boolean[] prev = new boolean[t.length() + 1];
    boolean[] curr = new boolean[t.length() + 1];

    prev[0] = true;
    for (int i = 1; i <= s.length(); i++) {
      boolean f = true;
      for (int ii = 1; ii <= i; ii++) {
        if (s.charAt(ii - 1) != '*') {
          f = false;
          break;
        }
      }
      curr[0] = f;
      for (int j = 1; j <= t.length(); j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1) || s.charAt(i - 1) == '?')
          curr[j] = prev[j - 1];
        else if (s.charAt(i - 1) == '*') {
          curr[j] = prev[j] || curr[j - 1];
        } else
          curr[j] = false;
      }
      prev = curr.clone();
    }
    return prev[t.length()];
  }
}