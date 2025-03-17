
import java.util.Arrays;

public class LCS {
  public static int lcs(String s, String t) {
    // Your code goes here

    int[][] dp = new int[s.length() + 1][t.length() + 1];
    for (int i = 0; i < s.length() + 1; i++)
      Arrays.fill(dp[i], -1);
    return memo(s.length(), t.length(), s, t, dp);
  }

  static int recursion(int index1, int index2, String s, String t) {
    if (index1 == 0 || index2 == 0) // we need to start from f(i,j) because we cannot store -1 in dp array
      return 0;

    int ans = 0;
    if (s.charAt(index1 - 1) == t.charAt(index2 - 1))
      ans = 1 + recursion(index1 - 1, index2 - 1, s, t);
    else {
      ans = Math.max(recursion(index1 - 1, index2, s, t), recursion(index1, index2 - 1, s, t));
    }
    return ans;
  }

  static int memo(int index1, int index2, String s, String t, int[][] dp) {
    if (index1 == 0 || index2 == 0)
      return 0;
    if (dp[index1][index2] != -1)
      return dp[index1][index2];
    int ans = 0;
    if (s.charAt(index1 - 1) == t.charAt(index2 - 1))
      ans = 1 + memo(index1 - 1, index2 - 1, s, t, dp);
    else {
      ans = Math.max(memo(index1 - 1, index2, s, t, dp), memo(index1, index2 - 1, s, t, dp));
    }
    return dp[index1][index2] = ans;
  }

  public static int[][] tabulation(String s, String t) {
    int[][] dp = new int[s.length() + 1][t.length() + 1];

    for (int i = 1; i <= s.length(); i++) {
      for (int j = 1; j <= t.length(); j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1))
          dp[i][j] = 1 + dp[i - 1][j - 1];
        else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp;
  }

  static int spaceOptimized(String s, String t) {
    int[] prev = new int[t.length() + 1];
    int[] curr = new int[t.length() + 1];

    for (int i = 1; i <= s.length(); i++) {
      for (int j = 1; j <= t.length(); j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1))
          curr[j] = 1 + prev[j - 1];
        else {
          curr[j] = Math.max(prev[j], curr[j - 1]);
        }
      }
      prev = curr.clone();
    }
    return prev[t.length()];
  }

  public static String findLCS(int n, int m, String s, String t) {
    // Write your code here.
    int[][] dp = tabulation(s, t);
    int i = n, j = m;
    StringBuffer sb = new StringBuffer();
    while (i > 0 && j > 0) { // Correct condition
      if (s.charAt(i - 1) == t.charAt(j - 1)) {
        sb.append(s.charAt(i - 1));
        i--;
        j--;
      } else if (dp[i - 1][j] > dp[i][j - 1]) {
        i--;
      } else {
        j--;
      }
    }
    return sb.reverse().toString();

  }

}