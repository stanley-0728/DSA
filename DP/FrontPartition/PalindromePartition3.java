package DP.FrontPartition;

import java.util.Arrays;

/*
 * You are given a string s containing lowercase letters and an integer k. You need to :

First, change some characters of s to other lowercase English letters.
Then divide s into k non-empty disjoint substrings such that each substring is a palindrome.
Return the minimal number of characters that you need to change to divide the string.

Example 1:

Input: s = "abc", k = 2
Output: 1
Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.
Example 2:

Input: s = "aabbc", k = 3
Output: 0
Explanation: You can split the string into "aa", "bb" and "c", all of them are palindrome.
Example 3:

Input: s = "leetcode", k = 8
Output: 0
 
 */
public class PalindromePartition3 {
  public int palindromePartition(String s, int k) {
    int[][] dp = new int[s.length()][k + 1];
    for (int i = 0; i < s.length(); i++)
      Arrays.fill(dp[i], -1);
    // return recursion(0, k, s, dp);
    int n = s.length();
    int[][] cost = new int[n][n]; // Cost to make s[i:j] a palindrome
    precomputeCost(s, cost);
    return solve(0, k, s, cost, dp);
  }

  // o(n*k) * o(n) *o(n)
  private int recursion(int index, int k, String s, int[][] dp) {
    int n = s.length();
    if (k == 0)
      return (index == n) ? 0 : Integer.MAX_VALUE;
    if (index == n)
      return Integer.MAX_VALUE;
    if (dp[index][k] != -1)
      return dp[index][k];
    int minChanges = Integer.MAX_VALUE;
    for (int j = index; j < n; j++) {
      int changes = minChangesToPalindrome(index, j, s);
      int next = recursion(j + 1, k - 1, s, dp);
      if (next != Integer.MAX_VALUE) { // Avoid overflow
        minChanges = Math.min(minChanges, changes + next);
      }
    }
    return dp[index][k] = minChanges;
  }

  private int solve(int i, int k, String s, int[][] cost, int[][] dp) {
    if (k == 0)
      return (i == s.length()) ? 0 : Integer.MAX_VALUE; // No partitions left
    if (i == s.length())
      return Integer.MAX_VALUE; // Invalid partition
    if (dp[i][k] != -1)
      return dp[i][k];

    int minCost = Integer.MAX_VALUE;
    for (int j = i; j < s.length(); j++) {
      int currCost = cost[i][j];
      int next = solve(j + 1, k - 1, s, cost, dp);
      if (next != Integer.MAX_VALUE) {
        currCost += next;
        minCost = Math.min(minCost, currCost);
      }
    }
    return dp[i][k] = minCost;
  }

  int minChangesToPalindrome(int i, int j, String s) {
    int c = 0;
    while (i < j) {
      if (s.charAt(i) != s.charAt(j))
        c++;
      i++;
      j--;
    }
    return c;
  }

  public int tabulation(String s, int k) {
    int n = s.length();
    int[][] cost = new int[n][n];
    precomputeCost(s, cost);

    int[][] dp = new int[n + 1][k + 1];

    for (int i = 0; i < n; i++) {
      dp[i][0] = Integer.MAX_VALUE;
    }
    for (int part = 1; part <= k; part++) {
      dp[n][part] = Integer.MAX_VALUE;
    }

    for (int i = n - 1; i >= 0; i--) {
      for (int part = 1; part <= k; part++) {
        int minCost = Integer.MAX_VALUE;
        for (int j = i; j < n; j++) {
          int currCost = cost[i][j];
          if (dp[j + 1][part - 1] != Integer.MAX_VALUE) {
            minCost = Math.min(minCost, currCost + dp[j + 1][part - 1]);
          }
        }
        dp[i][part] = minCost;
      }
    }

    return dp[0][k];
  }

  private void precomputeCost(String s, int[][] cost) {
    int n = s.length();
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        cost[i][j] = minChangesToPalindrome(i, j, s);

      }
    }
  }
}
