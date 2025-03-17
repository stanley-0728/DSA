
import java.util.Arrays;

/*
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
You have the following three operations permitted on a word:
Insert a character
Delete a character
Replace a character

Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
 */
public class EditDistance {
  public int minDistance(String word1, String word2) {
    int[][] dp = new int[word1.length() + 1][word2.length() + 1];
    for (int i = 0; i < word1.length() + 1; i++)
      Arrays.fill(dp[i], -1);
    // return recursion(word1.length() - 1, word2.length() - 1, word1, word2);
    return memo(word1.length(), word2.length(), word1, word2, dp); // for tabulation we need to convert into 1 based
                                                                   // indexing.

  }

  static int recursion(int index1, int index2, String s, String t) {
    if (index1 == 0)
      return index2;
    if (index2 == 0)
      return index1;
    if (s.charAt(index1 - 1) == t.charAt(index2 - 1))
      return recursion(index1 - 1, index2 - 1, s, t);
    else {
      return Math.min(1 + recursion(index1, index2 - 1, s, t),
          Math.min(1 + recursion(index1 - 1, index2, s, t), 1 + recursion(index1 - 1, index2 - 1, s, t)));
    }
  }

  static int memo(int index1, int index2, String s, String t, int[][] dp) {
    if (index1 == 0)
      return index2;
    if (index2 == 0)
      return index1;
    if (dp[index1][index2] != -1)
      return dp[index1][index2];
    if (s.charAt(index1 - 1) == t.charAt(index2 - 1))
      return dp[index1][index2] = memo(index1 - 1, index2 - 1, s, t, dp);
    else {
      return dp[index1][index2] = Math.min(1 + memo(index1, index2 - 1, s, t, dp),
          Math.min(1 + memo(index1 - 1, index2, s, t, dp), 1 + memo(index1 - 1, index2 - 1, s, t, dp)));
    }
  }

  public static int tabulation(String s, String t) {
    int[][] dp = new int[s.length() + 1][t.length() + 1];
    for (int i = 0; i < s.length() + 1; i++)
      dp[i][0] = i;
    for (int j = 0; j < t.length() + 1; j++)
      dp[0][j] = j;
    for (int i = 1; i <= s.length(); i++) {
      for (int j = 1; j <= t.length(); j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1))
          dp[i][j] = dp[i - 1][j - 1];
        else {
          dp[i][j] = Math.min(1 + dp[i][j - 1],
              Math.min(1 + dp[i - 1][j], 1 + dp[i - 1][j - 1]));
        }
      }
    }
    return dp[s.length()][t.length()];
  }

  public static int spaceOptimized(String s, String t) {
    int[] prev = new int[t.length() + 1];
    int[] curr = new int[t.length() + 1];
    for (int j = 0; j < t.length() + 1; j++)
      prev[j] = j;
    for (int i = 1; i <= s.length(); i++) {
      curr[0] = i;
      for (int j = 1; j <= t.length(); j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1))
          curr[j] = prev[j - 1];
        else {
          curr[j] = Math.min(1 + curr[j - 1],
              Math.min(1 + prev[j], 1 + prev[j - 1]));
        }
      }
      prev = curr.clone();
    }
    return prev[t.length()];
  }
}
