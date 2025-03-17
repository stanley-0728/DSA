package DP.FrontPartition;

import java.util.Arrays;

/*
 * Given a string s, return true if it is possible to split the string s into three non-empty palindromic substrings. Otherwise, return false.​​​​​

A string is said to be palindrome if it the same string when reversed.
Example 1:

Input: s = "abcbdd"
Output: true
Explanation: "abcbdd" = "a" + "bcb" + "dd", and all three substrings are palindromes.
Example 2:

Input: s = "bcbddxy"
Output: false
Explanation: s cannot be split into 3 palindromes.
 
 */
public class PalindromPartition4 {
  public boolean checkPartitioning(String s) {
    // int[][] dp = new int[s.length()][4];
    // for (int i = 0; i < s.length(); i++)
    // Arrays.fill(dp[i], -1);
    // return recursion(0, 0, s.length(), s, dp);
    int n = s.length();
    boolean[][] dp = tabulation(s);

    for (int k1 = 0; k1 < n - 2; k1++) {
      if (dp[0][k1]) {
        for (int k2 = k1 + 1; k2 < n - 1; k2++) {
          if (dp[k1 + 1][k2] && dp[k2 + 1][n - 1]) {
            return true;
          }
        }
      }
    }

    return false;
  }

  boolean recursion(int index, int count, int n, String s, int[][] dp) {
    if (index == n)
      return count == 3 ? true : false;
    if (count > 3)
      return false;
    if (dp[index][count] != -1)
      return dp[index][count] == 1;
    for (int j = index; j < n; j++) {
      if (isPalindrome(index, j, s)) {
        if (recursion(j + 1, count + 1, n, s, dp)) {
          dp[index][count] = 1;
          return true;
        }
      }
    }
    dp[index][count] = 0;
    return false;
  }

  static boolean[][] tabulation(String s) {
    boolean[][] dp = new boolean[s.length()][s.length()];
    for (int i = 0; i < s.length(); i++)
      dp[i][i] = true;
    for (int i = s.length() - 1; i >= 0; i--) {
      for (int j = i + 1; j < s.length(); j++) {
        if (j - i == 1) {
          dp[i][j] = s.charAt(i) == s.charAt(j);
        } else {
          dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i + 1][j - 1];
        }
      }
    }
    return dp;
  }

  boolean isPalindrome(int i, int j, String s) {
    while (i < j) {
      if (s.charAt(i) != s.charAt(j))
        return false;
      i++;
      j--;
    }
    return true;
  }
}
