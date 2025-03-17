package DP.SubSquencePattern.CoinChange;

import java.util.Arrays;

public class CoinChange2 {
  public static long countWaysToMakeChange(int denominations[], int value) {
    int n = denominations.length;
    int[][] dp = new int[n][value + 1];
    for (int i = 0; i < n; i++)
      Arrays.fill(dp[i], -1);
    // return recursion(denominations.length - 1, value, denominations);
    return memo(n - 1, value, denominations, dp);

  }

  static int recursion(int index, int k, int[] nums) {
    if (k == 0)
      return 1;
    if (index == 0) {
      if (k % nums[0] == 0)
        return 1;
      else
        return 0;
    }
    int notPick = recursion(index - 1, k, nums);
    int pick = 0;
    if (nums[index] <= k)
      pick = recursion(index, k - nums[index], nums);
    return pick + notPick;
  }

  static int memo(int index, int k, int[] nums, int[][] dp) {
    if (k == 0)
      return 1;
    if (index == 0) {
      if (k % nums[0] == 0)
        return 1;
      else
        return 0;
    }
    if (dp[index][k] != -1)
      return dp[index][k];
    int notPick = memo(index - 1, k, nums, dp);
    int pick = 0;
    if (nums[index] <= k)
      pick = memo(index, k - nums[index], nums, dp);
    return dp[index][k] = pick + notPick;
  }

  static int tabulation(int[] nums, int k) {
    int n = nums.length;
    int[][] dp = new int[n][k + 1];
    for (int i = 0; i < n; i++)
      dp[i][0] = 1;
    for (int i = 0; i <= k; i++) {
      if (i % nums[0] == 0)
        dp[0][i] = 1;
      else
        dp[0][i] = 0;
    }
    for (int i = 1; i < n; i++) {
      for (int j = 1; j <= k; j++) {
        int notPick = dp[i - 1][j];
        int pick = 0;
        if (nums[i] <= j)
          pick = dp[i][j - nums[i]];
        dp[i][j] = pick + notPick;
      }
    }
    return dp[n - 1][k];
  }

  static int spaceOptimized(int[] nums, int k) {
    int n = nums.length;
    int[] prev = new int[k + 1];
    int[] curr = new int[k + 1];
    for (int i = 0; i <= k; i++) {
      if (i % nums[0] == 0)
        prev[i] = 1;
      else
        prev[i] = 0;
    }
    for (int i = 1; i < n; i++) {
      curr[0] = 1;
      for (int j = 1; j <= k; j++) {
        int notPick = prev[j];
        int pick = 0;
        if (nums[i] <= j)
          pick = curr[j - nums[i]];
        curr[j] = pick + notPick;
      }
      prev = Arrays.copyOf(curr, curr.length);
    }
    return prev[k];

  }

}
