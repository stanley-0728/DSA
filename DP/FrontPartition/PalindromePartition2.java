package DP.FrontPartition;

import java.util.Arrays;

public class PalindromePartition2 {
  public int minCut(String s) {
    int[] dp = new int[s.length()];
    Arrays.fill(dp, -1);
    return recursion(0, s.length(), s, dp) - 1;
  }

  int recursion(int index, int n, String s) {
    if (index == n)
      return 0;

    int mini = Integer.MAX_VALUE;
    for (int j = index; j < n; j++) {
      if (isPalindrome(index, j, s)) {
        int cost = 1 + recursion(j + 1, n, s);
        mini = Math.min(mini, cost);
      }
    }
    return mini;
  }

  int recursion(int index, int n, String s, int[] dp) {
    if (index == n)
      return 0;
    if (dp[index] != -1)
      return dp[index];
    int mini = Integer.MAX_VALUE;
    for (int j = index; j < n; j++) {
      if (isPalindrome(index, j, s)) {
        int cost = 1 + recursion(j + 1, n, s, dp);
        mini = Math.min(mini, cost);
      }
    }
    return dp[index] = mini;
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

  int tabulation(String s){
    int[] dp = new int[s.length()];
    for(int i=s.length()-1;i>=0;i--){
      int mini = Integer.MAX_VALUE;
      for (int j = i; j < s.length(); j++) {
        if (isPalindrome(i, j, s)) {
          int cost = 1 + dp[j + 1];
          mini = Math.min(mini, cost);
        }
      }
       dp[i] = mini;
    }
    return dp[0]-1;
  }
}