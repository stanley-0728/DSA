package DP.SubSquencePattern.Knapsack;

import java.util.Arrays;

/*
 * You are given ‘n’ items with certain ‘profit’ and ‘weight’ and a knapsack with weight capacity ‘w’.

You need to fill the knapsack with the items in such a way that you get the maximum profit. You are allowed to take one item multiple times.

Example:
Input: 
'n' = 3, 'w' = 10, 
'profit' = [5, 11, 13]
'weight' = [2, 4, 6]

Output: 27

Explanation:
We can fill the knapsack as:

1 item of weight 6 and 1 item of weight 4.
1 item of weight 6 and 2 items of weight 2.
2 items of weight 4 and 1 item of weight 2.
5 items of weight 2.
 */
public class UnBoundedknapSack {
  public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
    // Write your code here.
    int[][] dp = new int[n][w + 1];
    for (int i = 0; i < n; i++)
      Arrays.fill(dp[i], -1);
    // return recursion(n - 1, w, weight, profit);
    return memo(n - 1, w, weight, profit, dp);
  }

  static int recursion(int index, int k, int[] nums, int[] profit) {
    if (k == 0)
      return 0;
    if (index == 0) {
      if (k % nums[0] == 0)
        return (k / nums[0]) * profit[0];
      else
        return 0;
    }
    int notPick = recursion(index - 1, k, nums, profit);
    int pick = 0;
    if (nums[index] <= k)
      pick = profit[index] + recursion(index, k - nums[index], nums, profit);
    return Math.max(pick, notPick);
  }

  static int memo(int index, int k, int[] nums, int[] profit, int[][] dp) {
    if (k == 0)
      return 0;
    if (index == 0) {
      if (k % nums[0] == 0)
        return (k / nums[0]) * profit[0];
      else
        return 0;
    }
    if (dp[index][k] != -1)
      return dp[index][k];
    int notPick = memo(index - 1, k, nums, profit, dp);
    int pick = 0;
    if (nums[index] <= k)
      pick = profit[index] + memo(index, k - nums[index], nums, profit, dp);
    return dp[index][k] = Math.max(pick, notPick);
  }

  static int tabulation(int[] nums, int k, int[] profit) {
    int n = nums.length;
    int[][] dp = new int[n][k + 1];

    for (int i = 0; i <= k; i++) {
      if (i % nums[0] == 0)
        dp[0][i] = (i / nums[0]) * profit[0];
      else
        dp[0][i] = 0;
    }
    for (int i = 1; i < n; i++) {
      for (int j = 1; j <= k; j++) {
        int notPick = dp[i - 1][j];
        int pick = 0;
        if (nums[i] <= j)
          pick = profit[i] + dp[i][j - nums[i]];
        dp[i][j] = Math.max(pick, notPick);
      }
    }
    return dp[n - 1][k];
  }

  static int memo(int[] nums, int k, int[] profit) {
    int n = nums.length;
    int[] prev = new int[k + 1];
    int[] curr = new int[k + 1];
    for (int i = 0; i <= k; i++) {
      if (i % nums[0] == 0)
        prev[i] = (i / nums[0]) * profit[0];
      else
        prev[i] = 0;
    }
    for (int i = 1; i < n; i++) {
      for (int j = 1; j <= k; j++) {
        int notPick = prev[j];
        int pick = 0;
        if (nums[i] <= j)
          pick = profit[i] + curr[j - nums[i]];
        curr[j] = Math.max(pick, notPick);
      }
      prev = curr.clone();
    }
    return prev[k];
  }
}
