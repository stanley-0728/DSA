
/*
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

An interleaving of two strings s and t is a configuration where s and t are divided into n and m substrings respectively, such that:

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b.
Example 1:
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Explanation: One way to obtain s3 is:
Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
Since s3 can be obtained by interleaving s1 and s2, we return true.
Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.
Example 3:

Input: s1 = "", s2 = "", s3 = ""
Output: true
 */
public class InterleavingStrings {
  public static void main(String[] args) {
    String s1 = "aabcc";
    String s2 = "dbbca";
    String s3 = "aadbbcbcac";
    System.out.println(isInterleave(s1, s2, s3));
  }

  public static boolean isInterleave(String s1, String s2, String s3) {
    if (s3.length() != s1.length() + s2.length())
      return false;

    return recursion(s1, s2, s3, 0, 0);
  }

  static boolean recursion(String s1, String s2, String s3, int i, int j) {
    if (i == s1.length() && j == s2.length() && i + j == s3.length())
      return true;
    if (i == s1.length() && j == s2.length() && i + j != s3.length())
      return false;
    if (i < s1.length() && j < s2.length() && s1.charAt(i) == s2.charAt(j) && s3.charAt(i + j) == s1.charAt(i)) {
      return recursion(s1, s2, s3, i + 1, j) || recursion(s1, s2, s3, i, j + 1);
    } else if (i < s1.length() && s1.charAt(i) == s3.charAt(i + j)) {
      return recursion(s1, s2, s3, i + 1, j);
    } else if (j < s2.length() && s2.charAt(j) == s3.charAt(i + j)) {
      return recursion(s1, s2, s3, i, j + 1);
    } else
      return false;
  }

  static boolean recursion(String s1, String s2, String s3, int i, int j, Boolean[][] dp) {
    if (i == s1.length() && j == s2.length())
      return true;

    if (dp[i][j] != null)
      return dp[i][j];

    boolean ans = false;
    int k = i + j;

    if (i < s1.length() && s1.charAt(i) == s3.charAt(k))
      ans |= recursion(s1, s2, s3, i + 1, j, dp);

    if (j < s2.length() && s2.charAt(j) == s3.charAt(k))
      ans |= recursion(s1, s2, s3, i, j + 1, dp);

    return dp[i][j] = ans;
  }

  public static boolean tabulation(String s1, String s2, String s3) {
    int m = s1.length(), n = s2.length();

    if (m + n != s3.length())
      return false;

    boolean[][] dp = new boolean[m + 1][n + 1];

    dp[m][n] = true;

    for (int i = m; i >= 0; i--) {
      for (int j = n; j >= 0; j--) {
        int k = i + j;

        if (i < m && s1.charAt(i) == s3.charAt(k))
          dp[i][j] |= dp[i + 1][j];

        if (j < n && s2.charAt(j) == s3.charAt(k))
          dp[i][j] |= dp[i][j + 1];
      }
    }

    return dp[0][0];
  }

  public static boolean spaceOptimized(String s1, String s2, String s3) {
    int m = s1.length(), n = s2.length();

    if (m + n != s3.length())
      return false;

    boolean[] prev = new boolean[n + 1]; // Previous row
    boolean[] curr = new boolean[n + 1]; // Current row

    // Base case: When both strings are empty, dp[m][n] = true
    prev[n] = true;

    // Fill last row (equivalent to dp[m][j] in 2D DP)
    for (int j = n - 1; j >= 0; j--) {
      prev[j] = s2.charAt(j) == s3.charAt(m + j) && prev[j + 1];
    }

    // Process rows from bottom to top
    for (int i = m - 1; i >= 0; i--) {
      curr[n] = s1.charAt(i) == s3.charAt(i + n);

      for (int j = n - 1; j >= 0; j--) {
        int k = i + j; // Index in s3

        curr[j] = (s1.charAt(i) == s3.charAt(k) && prev[j]) ||
            (s2.charAt(j) == s3.charAt(k) && curr[j + 1]);
      }

      // Copy curr to prev for the next iteration
      System.arraycopy(curr, 0, prev, 0, n + 1);
    }

    return prev[0]; // Final answer at prev[0]
  }

}
