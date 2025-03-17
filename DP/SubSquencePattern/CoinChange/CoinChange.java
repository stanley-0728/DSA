package DP.SubSquencePattern.CoinChange;

import java.util.Arrays;

public class CoinChange {
  public static int minimumElements(int num[], int x) {
    // Write your code here.
    int n = num.length;
    int[][] dp = new int[n][x + 1];
    for (int i = 0; i < n; i++)
      Arrays.fill(dp[i], -1);
    // int ans = recursion(num.length - 1, x, num);
    int ans = memo(num.length - 1, x, num, dp);

    if (ans >= Integer.MAX_VALUE - 2)
      return -1;
    return ans;
  }

  static int recursion(int index, int k, int[] nums) {
    if (k == 0)
      return 0;
    if (index == 0) {
      if (k % nums[0] == 0)
        return k / nums[0];
      else
        return Integer.MAX_VALUE - 1;
    }
    int notPick = recursion(index - 1, k, nums);
    int pick = Integer.MAX_VALUE - 1;
    if (nums[index] <= k)
      pick = 1 + recursion(index, k - nums[index], nums);
    return Math.min(pick, notPick);
  }

  static int memo(int index, int k, int[] nums, int[][] dp) {
    if (k == 0)
      return 0;
    if (index == 0) {
      if (k % nums[0] == 0)
        return k / nums[0];
      else
        return Integer.MAX_VALUE - 1;
    }
    if (dp[index][k] != -1)
      return dp[index][k];
    int notPick = memo(index - 1, k, nums, dp);
    int pick = Integer.MAX_VALUE - 1;
    if (nums[index] <= k)
      pick = 1 + memo(index, k - nums[index], nums, dp);
    return dp[index][k] = Math.min(pick, notPick);
  }

  static int tabulation(int[] nums, int k) {
    int n = nums.length;
    int[][] dp = new int[n][k + 1];

    for (int i = 0; i <= k; i++) {
      if (i % nums[0] == 0)
        dp[0][i] = i / nums[0];
      else
        dp[0][i] = Integer.MAX_VALUE - 1;
    }
    for (int i = 1; i < n; i++) {
      for (int j = 0; j <= k; j++) {
        int notPick = dp[i - 1][j];
        int pick = Integer.MAX_VALUE - 1;
        if (nums[i] <= j)
          pick = 1 + dp[i][j - nums[i]];
        dp[i][j] = Math.min(pick, notPick);
      }
    }
    return (dp[n - 1][k] >= Integer.MAX_VALUE - 2) ? -1 : dp[n - 1][k];
  }

  static int spaceOptimized(int[] nums, int k) {
    int[] prev = new int[k + 1];
    for (int i = 0; i <= k; i++) {
      if (i % nums[0] == 0)
        prev[i] = i / nums[0];
      else
        prev[i] = Integer.MAX_VALUE - 1;
    }

    for (int i = 1; i < nums.length; i++) {
      int[] curr = new int[k + 1];
      for (int j = 0; j <= k; j++) {
        int notPick = prev[j];
        int pick = Integer.MAX_VALUE - 1;
        if (nums[i] <= j)
          pick = 1 + curr[j - nums[i]];

        curr[j] = Math.min(pick, notPick);
      }
      prev = curr;
    }

    return (prev[k] >= Integer.MAX_VALUE - 2) ? -1 : prev[k];
  }

}
